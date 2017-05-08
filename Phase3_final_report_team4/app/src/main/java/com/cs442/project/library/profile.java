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
 * Created by Rahi on 11/19/2015.
 */
public class profile extends AppCompatActivity {
    ImageView home;
    ImageView search;
    String anumber;
    String address;
    int flag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Globals g = Globals.getInstance();
        anumber = g.getData();
        address = "http://216.47.136.132:8080/getpatron?keyword=";
        executeSearch(0);


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

    public void executeSearch(int num){
        Log.w("hi", "hi");
        flag = num;
        new LongRunningGetIO().execute();
    }


    public void setNameGetTrans(String content){
        String[]content1 = content.split("\n");
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText(content1[1] + " " + content1[0]);
        address = "http://216.47.136.132:8080/gettransactions?keyword=";
        anumber = content1[2];
        executeSearch(1);
    }

    public void setList(String content){
        ArrayList<String> list = new ArrayList<String>();
        String[] content1 = content.split(Pattern.quote("$"));
        for(int i = 0;i < content1.length - 1;i++){
            list.add(content1[0]);
        }
        Object[] objectList = list.toArray();
        String[] names = Arrays.copyOf(objectList, objectList.length, String[].class);
        ArrayAdapter<String> ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        ListView results = (ListView)findViewById(R.id.listView);
        results.setAdapter(ArrayAdapter);

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
            HttpGet httpGet = new HttpGet(address + anumber);
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
                    if(flag == 0) {
                        setNameGetTrans(content);
                    }
                    else if(flag == 1) {
                        Log.w("Text","Hi");
                        setList(content);
                        Log.w("Text",content);
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
