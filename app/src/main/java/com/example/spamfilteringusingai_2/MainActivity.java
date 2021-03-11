package com.example.spamfilteringusingai_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void onLoginButtonPressed(View view){
        SharedPreferences sp=getSharedPreferences("spamFillteringapp", Context.MODE_PRIVATE);

        String email=sp.getString("useremail","noemail");
        if(email.equals("noemail")) {
            //move to screen number 2
            Intent move = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(move);

        }


       else {
            Intent move2 = new Intent(MainActivity.this, MainActivity3.class);
            move2.putExtra("email",email);
            startActivity(move2);
        }



    }
}