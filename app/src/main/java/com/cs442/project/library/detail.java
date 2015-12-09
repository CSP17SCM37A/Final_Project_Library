package com.cs442.project.library;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by Jason on 11/19/2015.
 */
public class detail extends AppCompatActivity {
    ImageView home;
    ImageView search;
    String bibid = "";
    private TextView title;
    private TextView author;
    private TextView call;
    private TextView location;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        title = (TextView)findViewById(R.id.MyText);

        author = (TextView)findViewById(R.id.textView4);

        call = (TextView)findViewById(R.id.textView5);

        location = (TextView)findViewById(R.id.textView6);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("com.cs442.project.library.bibid");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("com.cs442.project.library.bibid");
        }
        Log.w("Hi",newString);
        bibid = newString;
        executeSearch();

        TextView book;
        ImageView home;
        ImageView search;
        ImageView maps;
        ImageView profile;
        ImageView notification;
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            home=(ImageView)findViewById(R.id.homeImage);
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homeScreen();
                }
            });
            search = (ImageView)findViewById(R.id.search);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchScreen();

                }
            });
            maps = (ImageView)findViewById(R.id.imageView5);
            maps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapsScreen();

                }
            });

            profile = (ImageView)findViewById(R.id.imageView6);
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    profileScreen();

                }
            });

            notification = (ImageView)findViewById(R.id.imageView4);
            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificationScreen();

                }
            });
        }



    public void homeScreen(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void searchScreen(){
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
        finish();
    }

    public void mapsScreen(){
        Intent intent = new Intent(this, maps.class);
        startActivity(intent);
        finish();
    }

    public void profileScreen(){
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
        finish();
    }

    public void notificationScreen(){
        Intent intent = new Intent(this, notification.class);
        startActivity(intent);
        finish();
    }

    public void executeSearch(){
        Log.w("hi","hi");
        new LongRunningGetIO().execute();
    }

    public void setList(String content){
        ArrayList<String> list = new ArrayList<String>();
        String[] content1 = content.split("\n");
        Log.w("Test",content1[0]);
        title.setText(content1[0]);
        author.setText(content1[1]);
        call.setText(content1[3]);
        location.setText(content1[4]);

    }



    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
            InputStream in = entity.getContent();
            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);
                if (n > 0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("http://216.47.136.132:8080/getinfo?keyword=" + bibid);
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text;
        }

        protected void onPostExecute(String results) {
            if (results != null) {


                JSONObject jObj;
                try {
                    JSONObject obj = new JSONObject(results);
                    String content = (String) obj.get("content");
                    setList(content);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
