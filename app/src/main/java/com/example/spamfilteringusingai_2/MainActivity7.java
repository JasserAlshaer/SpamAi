package com.example.spamfilteringusingai_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity7 extends AppCompatActivity {
    DrawerLayout drawerMenuForScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        drawerMenuForScreen=(DrawerLayout)findViewById(R.id.mainApplicationDrawer);
    }
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
        Intent home=new Intent(getApplicationContext(),MainActivity6.class);
        startActivity(home);
    }
    public void onSendEmailClick(View view){
        Intent send=new Intent(getApplicationContext(),MainActivity4.class);
        startActivity(send);
    }
    public void onTrushClicked(View view){
        Intent trush=new Intent(getApplicationContext(),MainActivity5.class);
        startActivity(trush);
    }
    public void onLogoutclicked(View view){
        signOut();
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