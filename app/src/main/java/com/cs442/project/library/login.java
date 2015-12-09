package com.cs442.project.library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chiru on 11/10/2015.
 */
public class login extends AppCompatActivity {
    //Button submit;
    TextView submit;
    TextView support;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //View view = new View(this);
        //view.setBackgroundResource(R.mipmap.);

        //submit = (Button)findViewById(R.id.buttonSubmit);
        submit = (TextView)findViewById(R.id.buttonSubmit);
        support = (TextView)findViewById(R.id.support);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainPage();
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(login.this);

                // set title
                alertDialogBuilder.setTitle("Contact US!");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Phone Number: +1(312)-(654)7821 \n Email:Library@hawk.iit.edu")
                        .setCancelable(false)

                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
               AlertDialog alertDialog = alertDialogBuilder.create();
                 alertDialog.show();
              //  AlertDialog.Builder builder = new AlertDialog.Builder(login.this);

            }
        });
    }

    public void goToMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
