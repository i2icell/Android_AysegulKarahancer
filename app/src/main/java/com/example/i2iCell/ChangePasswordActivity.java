package com.example.i2iCell;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //http://68.183.75.84:8080/i2iCellService/services/Services/changePassword?inputPhoneNumber=5315509999&inputTcNumber=57076105382&inputNewPassword=Abcdef123
        Button submitChangePassword = (Button) findViewById(R.id.changeButton);
        final EditText phoneNumber = (EditText) findViewById(R.id.phoneChange);
        final EditText tcNumber = (EditText) findViewById(R.id.tcChange);
        final EditText newPassword = (EditText) findViewById(R.id.newPassword);

        final Intent toLogin = new Intent(getBaseContext(),MainActivity.class);
        final AlertDialog.Builder loginFailed = new AlertDialog.Builder(this);
        loginFailed.setMessage("Kullanıcı Bilgileri Hatalı");
        loginFailed.setTitle("Parolan Değiştirilemedi");
        loginFailed.setPositiveButton("TAMAM", null);
        loginFailed.setCancelable(true);
        loginFailed.setPositiveButton("TAMAM",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        submitChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String response = "";

                try {
                    String myURL ="http://68.183.75.84:8080/i2iCellService/services/Services/" +
                            "changePassword?inputPhoneNumber="+phoneNumber.getText()+"&inputTcNumber="+tcNumber.getText()
                            +"inputNewPassword="+newPassword.getText();
                    URL url = new URL(myURL);
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        Log.i("",str);
                        response += str;
                    }
                    Log.i("resultFromXMLTag",response);
                    in.close();
                } catch (Exception e) {
                    Log.d("MyTag",e.toString());
                }
                if(response.charAt(73)=='1'){ Toast.makeText(getApplicationContext(),"Parolanız Başarıyla Değiştirildi", Toast.LENGTH_SHORT).show();

                    startActivity(toLogin);

                    return;
                }
                else{ Toast.makeText(getApplicationContext(), "Parolanız Değiştirilemedi", Toast.LENGTH_SHORT).show();
                    loginFailed.create().show();
                    return;
                }

            }




        });

    }
}
