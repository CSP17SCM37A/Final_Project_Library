package com.cs442.project.library;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
ImageView img;
    TextView book;
    ImageView home;
    ImageView search;
    ImageView maps;
    ImageView profile;
    ImageView notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book = (TextView)findViewById(R.id.button_book);
        book.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openBookMenu();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.add_id:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openBookMenu(){
        Log.d("Hi", "Rahi");
        Intent intent = new Intent(this, Books.class);
        startActivity(intent);
        finish();

    }

    public void openArticleMenu(){
        Log.d("Hi", "Rahi");
        Intent intent = new Intent(this, Books.class);
        startActivity(intent);
        finish();

    }

    public void openNewsPaperMenu(){
        Log.d("Hi", "Rahi");
        Intent intent = new Intent(this, Books.class);
        startActivity(intent);
        finish();

    }

    public void openJournalMenu(){
        Log.d("Hi", "Rahi");
        Intent intent = new Intent(this, Books.class);
        startActivity(intent);
        finish();

    }

    public void openResearchMenu(){
        Log.d("Hi", "Rahi");
        Intent intent = new Intent(this, Books.class);
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
