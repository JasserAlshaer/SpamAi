package com.example.spamfilteringusingai_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class  MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ArrayList <String> names=new ArrayList();
        names.add("Jasser");
        names.add("Heba");
        names.add("Dalia");
        names.add("Saad");
        ListView trushList = (ListView) findViewById(R.id.trushEmailList);
        ArrayAdapter myadapter=new ArrayAdapter(this,
                R.layout.email_list_design,R.id.mailname,names
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
        trushList.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
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