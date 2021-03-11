package com.example.spamfilteringusingai_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ListView trushList = (ListView) findViewById(R.id.trushEmailList);
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