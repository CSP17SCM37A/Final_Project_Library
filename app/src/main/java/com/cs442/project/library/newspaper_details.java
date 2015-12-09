package com.cs442.project.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rahi on 12/8/2015.
 */
public class newspaper_details extends AppCompatActivity{
    ImageView home;
    ImageView search;
    TextView b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books);
        home=(ImageView)findViewById(R.id.homeImage);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScreen();
            }
        });
        b = (TextView)findViewById(R.id.computerScienceButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computerScienceActivity();
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

    public void computerScienceActivity(){
        Intent intent = new Intent(this, Computer_Science_Books.class);
        startActivity(intent);
        finish();
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
}
