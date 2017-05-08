package com.cs442.project.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Rahi on 11/6/2015.
 */
public class Computer_Science_Books extends AppCompatActivity {
    ImageView img;
    ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.computer_science_books);

        img = (ImageView)findViewById(R.id.homeImage);
        img.setOnClickListener(new View.OnClickListener() {
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
    }

    public void searchScreen(){
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
    }
}
