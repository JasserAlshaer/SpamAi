package com.example.spamfilteringusingai_2;


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

import com.google.android.gms.common.ConnectionResult;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Base64;
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
    private ConnectionResult mConnectionResult;
    static String[] SCOPES = {
            GmailScopes.MAIL_GOOGLE_COM
    };
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
        //Use Api First Time
        gmailTask task1=new gmailTask();
        task1.execute();
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab2.setTextColor(Color.BLACK);
                tab3.setTextColor(Color.BLACK);
                emails.clear();
                //Add Data For Email
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
                myadapter.notifyDataSetChanged();
                tab3.setTextColor(Color.RED);
            }
        });
       emails=new ArrayList<>();

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
    class gmailTask extends AsyncTask<String, Void, String > {
        int unreadNumber,message;
        @Override
        protected String doInBackground(String... strings) {
            transport = AndroidHttp.newCompatibleTransport();
            mService = new com.google.api.services.gmail.Gmail.Builder(
                            AndroidHttp.newCompatibleTransport(),
                            new GsonFactory(),
                            MainActivity.mCredential)
                            .setApplicationName("Spam Filtering")
                            .build();
            try {
                List<String> messageList2 = new ArrayList();
                String query = "in:inbox";
                ListMessagesResponse listMessagesResponse = mService.users().messages().list(MainActivity.accountemail).setQ(query)
                        .setMaxResults(Long.valueOf(110)).execute();
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
                    //Get Body
                    byte[] bodyBytes = Base64.decodeBase64(messageText.getPayload().getParts().get(0).getBody().getData().trim());
                    String body = new String(bodyBytes, "UTF-8");
                    messageList2.add(body);
                    Log.d("Body", body);
                }
            }catch (IOException exception){
                Log.e("BUG", "SheetUpdate IOException"+exception.getMessage());
                exception.printStackTrace();
            }
            return null;
        }
    }
}

