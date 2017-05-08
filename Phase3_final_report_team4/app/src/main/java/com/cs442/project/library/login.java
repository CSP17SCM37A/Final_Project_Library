package com.cs442.project.library;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Chiru on 11/10/2015.
 */
public class login extends AppCompatActivity {
    Button submit;
    private String anumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        submit = (Button)findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainPage();
            }
        });


    }

    public void goToMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        EditText number = (EditText) findViewById(R.id.textView11);
        anumber = number.getText().toString();
        Globals g = Globals.getInstance();
        g.setData(anumber);
    }

}
