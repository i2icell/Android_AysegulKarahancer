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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ChangePasswordActivity extends AppCompatActivity {
String result;
    String response = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //http://68.183.75.84:8080/i2iCellService/services/Services/changePassword?inputPhoneNumber=
        // 5315509999
        // &inputTcNumber=
        // 57076105382
        // &inputNewPassword=
        // Abcdef123
        final Button submitChangePassword = (Button) findViewById(R.id.changeButton);
        final EditText phoneNumberFromChangePassword = (EditText) findViewById(R.id.phoneChange);
        final EditText tcNumber = (EditText) findViewById(R.id.tcChange);
        final EditText newPasswordFromChange = (EditText) findViewById(R.id.newPassword);

        final TextView what = (TextView) findViewById(R.id.textView20);
        final Intent goBackToMain = new Intent(getBaseContext(),MainActivity.class);

        final ImageView goBackMain = (ImageView)findViewById(R.id.goBack4);


        final AlertDialog.Builder changingsFail = new AlertDialog.Builder(this);
        goBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(goBackToMain);
            }
        });
        changingsFail.setMessage("Kullanıcı Bilgileri Hatalı");
        changingsFail.setTitle("Parolan Değiştirilemedi");
        changingsFail.setPositiveButton("TAMAM", null);
        changingsFail.setCancelable(true);
        changingsFail.setPositiveButton("TAMAM",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        submitChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("yeter",response);




                try {
                    String myURL = "http://68.183.75.84:8080/i2iCellService/services/Services/" +
                            "changePassword?inputPhoneNumber="
                            +phoneNumberFromChangePassword.getText()
                            +"&inputTcNumber="
                            +tcNumber.getText()
                            +"&inputNewPassword="
                            +newPasswordFromChange.getText();
                    URL url = new URL(myURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        Log.i("", str);
                        response += str;
                    }



                    Log.i("yeter",response);
                    in.close();
                } catch (Exception e) {
                    Log.d("MyTag", e.toString());
                }
                what.setText(String.valueOf(response));
            }



        });

    }
}
