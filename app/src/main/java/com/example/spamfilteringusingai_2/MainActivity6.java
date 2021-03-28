package com.example.spamfilteringusingai_2;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity6 extends AppCompatActivity {
TextView tab1,tab2,tab3;
  ListView applist;
    ArrayList <String> emails;
    ArrayAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        tab1=(TextView)findViewById(R.id.tab1);
        tab2=(TextView)findViewById(R.id.tab2);
        tab3=(TextView)findViewById(R.id.tab3);
        tab1.setTextColor(Color.RED);
        applist=(ListView)findViewById(R.id.emailsList);
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab2.setTextColor(Color.BLACK);
                tab3.setTextColor(Color.BLACK);
                emails.clear();
                emails.add("whiteemail1@gmail.com");
                emails.add("whiteemail2@gmail.com");
                emails.add("whiteemail3@gmail.com");
                myadapter.notifyDataSetChanged();
                tab1.setTextColor(Color.RED);
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //all emails tab
                tab1.setTextColor(Color.BLACK);
                tab3.setTextColor(Color.BLACK);
                emails.clear();
                emails.add("All1");
                emails.add("All2");
                emails.add("All3");
                myadapter.notifyDataSetChanged();
                tab2.setTextColor(Color.RED);
            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab2.setTextColor(Color.BLACK);
                tab1.setTextColor(Color.BLACK);
                emails.clear();
                emails.add("black1");
                emails.add("black2");
                emails.add("black3");
                myadapter.notifyDataSetChanged();
                tab3.setTextColor(Color.RED);
            }
        });
       emails=new ArrayList<>();
        emails.add("whiteemail1@gmail.com");
        emails.add("whiteemail2@gmail.com");
        emails.add("whiteemail3@gmail.com");
        myadapter=new ArrayAdapter(this,
                R.layout.email_list_design,R.id.mailname,emails
        ){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                CircleImageView maimage=(CircleImageView)view.findViewById(R.id.mailpic);
                TextView last=(TextView)view.findViewById(R.id.maillastmassage);
                //Fill Screen Content from Gmail Api
                return view;
            }
        };
        applist.setAdapter(myadapter);
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