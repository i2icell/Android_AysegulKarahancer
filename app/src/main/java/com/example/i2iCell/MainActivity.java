package com.example.i2iCell;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int upperCount;
    int lowerCount;
    int digitCount;
    String result;
    String myRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button enterButton = (Button)findViewById(R.id.btnEnter);
        Button newSignButton =(Button)findViewById(R.id.newSignUp);
        final EditText phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        final EditText loginPassword = (EditText)findViewById(R.id.loginPassword);
        final Intent toSignUpIntent = new Intent(getBaseContext(),SignupActivity.class);
        final Intent toBalanceActivityIntent = new Intent(getBaseContext(),BalanceActivity.class);
        final AlertDialog.Builder loginFailed = new AlertDialog.Builder(this);
        loginFailed.setMessage("Kullanıcı Adı Veya Kullanıcı Şifresi Hatalı");
        loginFailed.setTitle("Giriş Hatalı");
        loginFailed.setPositiveButton("TAMAM", null);
        loginFailed.setCancelable(true);
        loginFailed.setPositiveButton("TAMAM",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        newSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toSignUpIntent);
            }
        });
        enterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                final ArrayList<String> urls=new ArrayList<String>();
                final TextView t;
                String response = "";
                try {
                    String myURL ="http://68.183.75.84:8080/i2iCellService/services/Services/" +
                            "login?inputPhoneNumber="+phoneNumber.getText()+"&inputPassword="+loginPassword.getText();
                    URL url = new URL(myURL);
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        Log.i("getXMLTag",str);
                        //urls.add(str);
                        response += str;
                    }
                    result= ""+response.charAt(75);
                    myRes = result;
                    Log.i("resultFromXMLTag",result);

                    in.close();
                } catch (Exception e) {
                    Log.d("MyTag",e.toString());
                }
             TextView tv = (TextView)findViewById(R.id.textView11);
                char first = response.charAt(75);

                char q = '1';
                tv.setText(String.valueOf(q));
               if (q==first){ Toast.makeText(getApplicationContext(), "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                startActivity(toBalanceActivityIntent);
                return;}
                else { Toast.makeText(getApplicationContext(), "Giriş Hatalı", Toast.LENGTH_SHORT).show();
                   loginFailed.create().show();
                   return;
                }
            }


});}




}
