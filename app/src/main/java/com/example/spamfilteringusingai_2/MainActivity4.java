package com.example.spamfilteringusingai_2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {
    EditText text1;
    EditText text2;
    EditText text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //define mirror object
         text1=(EditText)findViewById(R.id.mailreciverfield);
         text2=(EditText)findViewById(R.id.mailsubjectfield);
         text3=(EditText)findViewById(R.id.mailtextfield);

    }

    public void onImageiconClick(View view) {

    }

    public void onFileIconClick(View view){

    }
    public void      onContactIconClick    (View view){


    }
    public void onCalenderIconClick (View view){

    }

    public void onSendClick(View view) {
    }

        public void onCancelClick(View view){

        }
        //Drawer Menu Method
        public void onDrawerMenuClick(View view){

        }
    public void onHomeClicked (View view){

    }
    public void onSendEmailClick(View view){

    }
    public void onTrushClicked(View view){

    }
    public void onLogoutclicked(View view){

    }




}