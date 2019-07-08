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
        Button btn = (Button)findViewById(R.id.btnDoMagic);
        Button newSign =(Button)findViewById(R.id.newSignUp);
        final EditText et = (EditText)findViewById(R.id.phoneNumber);
        final EditText ps = (EditText)findViewById(R.id.loginPassword);
        final Intent i = new Intent(getBaseContext(),SignupActivity.class);
        final Intent kk = new Intent(getBaseContext(),BalanceActivity.class);
        final AlertDialog.Builder loginFailed = new AlertDialog.Builder(this);
        loginFailed.setMessage("Kullanıcı adı veya kullanıcı şifresi hatalı");
        loginFailed.setTitle("Giriş Başarısız");
        loginFailed.setPositiveButton("TAMAM", null);
        loginFailed.setCancelable(true);
        loginFailed.setPositiveButton("TAMAM",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        newSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                final ArrayList<String> urls=new ArrayList<String>();
                final TextView t;
                String response = "";
                try {
                    String myURL ="http://68.183.75.84:8080/i2iCellService/services/Services/" +
                            "login?inputPhoneNumber="+et.getText()+"&inputPassword="+ps.getText();
                    URL url = new URL(myURL);
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        Log.i("MyTag",str);
                        //urls.add(str);
                        response += str;
                    }

                    result= ""+response.charAt(75);
                    myRes = result;
                    Log.i("resultTag",result);

                    in.close();


                } catch (Exception e) {
                    Log.d("MyTag",e.toString());
                }
             TextView tv = (TextView)findViewById(R.id.textView11);
                char first = response.charAt(75);

                char q = '1';
                tv.setText(String.valueOf(q));
               if (q==first){ Toast.makeText(getApplicationContext(), "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                   startActivity(kk);
                   return;}
                else { Toast.makeText(getApplicationContext(), "Giriş Hatalı!", Toast.LENGTH_SHORT).show();
                   loginFailed.create().show();
                   return;
                }
            }
   /* private boolean isPhoneValid(String phoneNumber) {
        if (String.valueOf(phoneNumber).startsWith("5")&& String.valueOf(phoneNumber).length()==10 ){
            return true;
        }
        else return false;
    }
    private boolean isPasswordValid(String loginPassword){
                if ((String.valueOf(loginPassword).length()>=8)&&(String.valueOf(loginPassword).length()<=12)){
                    return true;}
                else return false;
    }
    private boolean passwordOtherValid(String loginPassword){
                int passLength = (String.valueOf(loginPassword).length());
                int [] passChar = new int[passLength];

                for (int a = 0; a < passLength; a++){

                    if(loginPassword.charAt(a) == Character.toUpperCase( loginPassword.charAt(a))){
                        upperCount+=1;
                    }

                    if(loginPassword.charAt(a)==Character.toLowerCase( loginPassword.charAt(a))) {
                        lowerCount += 1;
                    }
                    if(Character.isDigit( loginPassword.charAt(a))){
                        digitCount+=1;
                    }

                }
                if ((upperCount>0)&&(lowerCount>0)&&(digitCount>0)){
                    return true;
                }

                else return false;
    }
*/




});}




}
