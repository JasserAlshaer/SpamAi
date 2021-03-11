package com.example.spamfilteringusingai_2;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {
TextView tab1,tab2,tab3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        tab1=(TextView)findViewById(R.id.tab1);
        tab2=(TextView)findViewById(R.id.tab2);
        tab3=(TextView)findViewById(R.id.tab3);


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