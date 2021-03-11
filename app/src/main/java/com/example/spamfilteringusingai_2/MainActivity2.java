package com.example.spamfilteringusingai_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //mirror object
        EditText email = (EditText) findViewById(R.id.emailfield);
        EditText password = (EditText) findViewById(R.id.passwordfield);
        Button login = (Button) findViewById(R.id.loginForFirsttime);
        //add event listener
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                //code for login operation
                //1.get data from user
                String emailtxt, passstxt;
                emailtxt = email.getText().toString();
                passstxt = password.getText().toString();
                if (emailtxt.contains("@") && emailtxt.contains(".com")) {
                } else {
                    Toast.makeText(MainActivity2.this, "Please Enter correct email ", Toast.LENGTH_SHORT).show();
                }
                //2.send data Gmail Api


                //3. save the email for future used

                SharedPreferences sp=getSharedPreferences("spamFillteringapp",Context.MODE_PRIVATE);
               //add data
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("useremail",emailtxt);
                editor.commit();


            }

        });
    }
}


