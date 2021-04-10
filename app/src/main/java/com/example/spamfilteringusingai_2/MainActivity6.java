package com.example.spamfilteringusingai_2;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity6 extends AppCompatActivity {
    Gmail mService;
    HttpTransport transport;
    DrawerLayout drawerMenuForScreen;
    private ConnectionResult mConnectionResult;
    static String[] SCOPES = {
            GmailScopes.MAIL_GOOGLE_COM
    };
    TextView tab1,tab2,tab3;
    ListView applist;
    ArrayList <String> emails,qoutation;
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
        drawerMenuForScreen=(DrawerLayout)findViewById(R.id.mainApplicationDrawer);
        //Use Api First Time
        gmailTask task1=new gmailTask();
        task1.execute("in:inbox");

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab2.setTextColor(Color.BLACK);
                tab3.setTextColor(Color.BLACK);
                //Add Data For Email
                tab1.setTextColor(Color.RED);
                gmailTask task1=new gmailTask();
                task1.execute("in:inbox");
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //all emails tab
                tab1.setTextColor(Color.BLACK);
                tab3.setTextColor(Color.BLACK);
                tab2.setTextColor(Color.RED);
                gmailTask task1=new gmailTask();
                task1.execute("in:spam");
            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab2.setTextColor(Color.BLACK);
                tab1.setTextColor(Color.BLACK);
                tab3.setTextColor(Color.RED);
                gmailTask task1=new gmailTask();
                task1.execute("in:trash");
                //in:trash
            }
        });
        emails=new ArrayList<>();
        qoutation=new ArrayList<>();
        myadapter=new ArrayAdapter(this,
                R.layout.email_list_design,R.id.mailname,emails
        ){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                CircleImageView maimage=(CircleImageView)view.findViewById(R.id.mailpic);
                TextView last=(TextView)view.findViewById(R.id.maillastmassage);
                last.setText(qoutation.get(position)+"");
                //Fill Screen Content from Gmail Api
                return view;
            }
        };
        applist.setAdapter(myadapter);
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

    class gmailTask extends AsyncTask<String, Void, String > {
        int unreadNumber,message;
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity6.this);
            pd.setMessage("Loading");
            pd.show();
        }
        @Override
        protected String doInBackground(String... querys) {
            transport = AndroidHttp.newCompatibleTransport();
            mService = new com.google.api.services.gmail.Gmail.Builder(
                            AndroidHttp.newCompatibleTransport(),
                            new GsonFactory(),
                            MainActivity.mCredential)
                            .setApplicationName("Spam Filtering")
                            .build();
            try {
                List<String> stringList = new ArrayList();
                ListMessagesResponse listMessagesResponse = mService.users().messages().list(MainActivity.accountemail)
                        .setQ(querys[0])
                        .setMaxResults(Long.valueOf(6))
                        .setIncludeSpamTrash(true)
                        .execute();
                List<Message> messageList = listMessagesResponse.getMessages();

                for (Message message : messageList) {
                    Message messageText = mService.users().messages().get(MainActivity.accountemail, message.getId()).setFormat("full").execute();
                        //Get Headers
                        List<MessagePartHeader> headers = messageText.getPayload().getHeaders();
                        String from="";
                        for (MessagePartHeader header:headers){
                            if(header.getName().equals("From")){
                                from=header.getValue();
                                break;
                            }
                        }

                        Log.i("Reciver",MainActivity.accountemail);
                        Log.i("From",from);
                        Log.i("Label",messageText.getLabelIds().get(0));
                        Log.i("Snippeset",messageText.getSnippet());
                        emails.add(from);
                        qoutation.add(messageText.getSnippet());
                        Log.i("hr","\n\n *******************************************************");

                }
            }catch (IOException exception){
                Log.e("BUG", "SheetUpdate IOException"+exception.getMessage());
                exception.printStackTrace();
            }
            return "Gmail";
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd != null)
            {
                myadapter.notifyDataSetChanged();
                pd.dismiss();
            }
        }
    }
}

