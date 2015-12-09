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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Jason on 11/6/2015.
 */
public class search extends AppCompatActivity {
    ImageView home;
    ImageView search;
    ImageView maps;
    ImageView profile;
    ImageView notification;
    ImageView img;
    String stuff;
    ListView txtId;
    Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        img = (ImageView) findViewById(R.id.homeImage);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScreen();
            }
        });
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

    public void executeSearch(View view){
        Log.w("hi","hi");
        EditText userName1 = (EditText) findViewById(R.id.enteredText);
        String newString= (String)userName1.getText().toString();
        stuff = newString;
        new LongRunningGetIO().execute();
    }

    public void setList(String content){
        ArrayList<String> list = new ArrayList<String>();
        String[] content1 = content.split("\n");
        for(int i = 0;i < content1.length - 1;i++){
            String[] content2 = content1[i].split(Pattern.quote("$"));
            list.add(content2[1]);
            map.put(content2[1],content2[0]);
        }
        Object[] objectList = list.toArray();
        String[] names = Arrays.copyOf(objectList, objectList.length, String[].class);
        ArrayAdapter<String> ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        ListView results = (ListView)findViewById(R.id.results);
        results.setAdapter(ArrayAdapter);
        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = (String) parent.getItemAtPosition(position);
                String bid = map.get(title);
                Log.w("Hi",title);
                Intent intent = new Intent(view.getContext(), detail.class);
                intent.putExtra("com.cs442.project.library.bibid",bid);
                startActivity(intent);
            }
        });
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
            HttpGet httpGet = new HttpGet("http://216.47.136.132:8080/getnames?keyword=" + stuff);
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
