package com.example.spamfilteringusingai_2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity4 extends AppCompatActivity {
    EditText text1;
    EditText text2;
    EditText text3;
    DrawerLayout drawerMenuForScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        drawerMenuForScreen=(DrawerLayout)findViewById(R.id.mainApplicationDrawer);
        //define mirror object
         text1=(EditText)findViewById(R.id.mailreciverfield);
         text2=(EditText)findViewById(R.id.mailsubjectfield);
         text3=(EditText)findViewById(R.id.mailtextfield);

    }

    public void onImageiconClick(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }
    public void onFileIconClick(View view){
    Intent choose=new Intent(Intent.ACTION_GET_CONTENT);
    choose.setType("*/*");
    choose=Intent.createChooser(choose,"Choose a file");
    startActivityForResult(choose,1);
    }
    public void onContactIconClick(View view){
     Intent con=new Intent(Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
     startActivityForResult(con,1);
    }

    public void onSendClick(View view) {
    String to,subject,massage;
    to=text1.getText().toString();
    subject=text2.getText().toString();
    massage=text3.getText().toString();
    }

    public void onCancelClick(View view){
     finish();
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