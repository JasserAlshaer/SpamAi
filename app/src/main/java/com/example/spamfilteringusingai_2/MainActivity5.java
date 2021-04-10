package com.example.spamfilteringusingai_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class  MainActivity5 extends AppCompatActivity {
    DrawerLayout drawerMenuForScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ArrayList <String> names=new ArrayList();
        names.add("Jasser");
        names.add("Heba");
        names.add("Dalia");
        names.add("Saad");
        drawerMenuForScreen=(DrawerLayout)findViewById(R.id.mainApplicationDrawer);
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
    //This method do the opening drawer operation

    //Drawer Menu Method
    public void onDrawerMenuClick(View view){
        openDrawer(drawerMenuForScreen);
    }
    private static void openDrawer(DrawerLayout draw) {
        draw.openDrawer(GravityCompat.START);
    }
    //This method do the closing drawer operation
    private static void closeDrawer(DrawerLayout draw) {
        if(draw.isDrawerOpen(GravityCompat.START)){
            draw.closeDrawer(GravityCompat.START);
        }
    }
        public void onHomeClicked (View view){

        }
        public void onSendEmailClick(View view){

        }
        public void onTrushClicked(View view){

        }
        public void onLogoutclicked(View view){

        }
    private void signOut() {
        MainActivity.mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent backtohome=new Intent
                                (getApplicationContext(),MainActivity.class);
                        startActivity(backtohome);
                    }
                });
    }


}