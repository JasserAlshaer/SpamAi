   package com.example.spamfilteringusingai_2;

   import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListDraftsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;

import java.io.IOException;
import java.util.Arrays;

   public class MainActivity extends AppCompatActivity {

       //private  static final int RC_SIGN_IN=9001;
       //private static final int PROFILE_PIC_SIZE = 400;

       private static final int RC_SIGN_IN = 9001;
       private  static final int REQUEST_CODE_TOKEN_AUTH=1337;
       GoogleSignInClient mGoogleSignInClient;
       Gmail mService;
       HttpTransport transport;
       JsonFactory jsonFactory;
       GoogleAccountCredential mCredential;
       private ConnectionResult mConnectionResult;
       static String[] SCOPES = {
                GmailScopes.MAIL_GOOGLE_COM
       };
       GoogleSignInAccount account;
       GoogleAuthUtil mAuth;
       Context mContext = this;
       String accountemail;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                   .requestScopes(
                   new Scope(GmailScopes.MAIL_GOOGLE_COM)
                   )
                   .requestIdToken("884385265121-b0gtdrn1s0dkn2u1vk8m6qlp98mg5s5j.apps.googleusercontent.com")
                   .requestEmail()
                   .build();
           mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    public void onLoginButtonPressed(View view){
        signIn();
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            Log.w("info", "Done" +account.getIdToken());
            Log.w("info", "Done" +account.getId());
            Log.w("info", "Done" +account.getEmail());
            Log.w("info", "Done" +account.getDisplayName());
            Log.w("info", "Done" +account.getAccount());
            accountemail=account.getEmail();
            mCredential = GoogleAccountCredential.usingOAuth2(
                    getApplicationContext(), Arrays.asList(SCOPES))
                    .setBackOff(new ExponentialBackOff());
            mCredential.setSelectedAccount(account.getAccount());
            GmailTast task = new GmailTast();
            task.execute();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("info", "signInResult:failed code=" + e.getStatusCode());

        }
    }
       class GmailTast extends AsyncTask<String, Void, String> {
           int unreadNumber,message;
           @Override
           protected String doInBackground(String... urls) {
                transport = AndroidHttp.newCompatibleTransport();
                jsonFactory = JacksonFactory.getDefaultInstance();
                mService =
                       new com.google.api.services.gmail.Gmail.Builder(
                               AndroidHttp.newCompatibleTransport(),
                               new GsonFactory(),
                               mCredential)
                               .setApplicationName("api")
                               .build();
               try {
                   ListMessagesResponse listResponse2;
                   listResponse2 = mService.users().messages().list(accountemail).execute();
                   Label listResponse;
                   listResponse = mService.users().labels().get(accountemail,"INBOX").execute();
                   unreadNumber = listResponse.getMessagesUnread();
                   message=listResponse.getMessagesTotal();
                   ListDraftsResponse draftResponse=mService.users().drafts().list(accountemail).execute();
                   Log.i("gmail1-Unread",unreadNumber+"");
                   Log.i("gmail2-MessageTotal",message+"");
                   Log.i("gmail3",listResponse2.size()+"");
                   Log.i("gmail4",listResponse2.getMessages().get(0).getId()+"");
                   Log.i("gmail5",draftResponse.getDrafts().size()+"");
               } catch(IOException e) {
                   Log.e("BUG", "SheetUpdate: IOException"+e.getMessage());
                   e.printStackTrace();
               }
               return message+"  "+unreadNumber;
           }
       };
}
