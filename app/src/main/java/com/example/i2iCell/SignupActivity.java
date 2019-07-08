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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Statement;

public class SignupActivity extends AppCompatActivity {
String response;
String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final AlertDialog.Builder signupFailed = new AlertDialog.Builder(this);
        signupFailed.setMessage("Girdiğiniz Bilgiler Hatalı");
        signupFailed.setTitle("Giriş Hatalı");
        signupFailed.setPositiveButton("TAMAM", null);
        signupFailed.setCancelable(true);
        signupFailed.setPositiveButton("TAMAM",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final Intent login = new Intent(getBaseContext(), MainActivity.class);
        Button successSign = (Button) findViewById(R.id.successSign);

        final EditText tcNo = (EditText)findViewById(R.id.tcNo);
        final EditText firstName = (EditText)findViewById(R.id.firstName);
        final EditText lastName = (EditText)findViewById(R.id.lastName);
        final CalendarView birthDate = (CalendarView) findViewById(R.id.birthDate);
        final EditText phoneNum = (EditText)findViewById(R.id.phoneNum);
        final EditText signupPass = (EditText)findViewById(R.id.signupPassword);
        final EditText inputEmail = (EditText)findViewById(R.id.inputEmail);

        successSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy2 = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy2);

                try {
                    String myURL = "http://68.183.75.84:8080/i2iCellService/services/Services/" +
                            "createAccount?inputFirstName=" + firstName.getText() + "&inputLastName=" + lastName.getText()
                            + "&inputPhoneNumber=" + phoneNum.getText()
                            + "&inputEmail=" + inputEmail.getText()
                            + "&inputPassword=" + signupPass.getText()
                            + "&inputBirthDate=" + birthDate.getDate()
                            + "&inputTcNumber=" + tcNo.getText();
                    //http://68.183.75.84:8080/i2iCellService/services/Services/createAccount?inputFirstName=aysegull&inputLastName=karahancer&inputPhoneNumber=5418422284&inputEmail=aysegul@mail.com&inputPassword=Aysek837&inputBirthDate=25-09-2005&inputTcNumber=57076105382

                    URL url = new URL(myURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        Log.i("getXMLTag", str);
                        response += str;
                    }
                    result = "" + response.charAt(71);
                    String myRes = result;
                    Log.i("resultFromXMLTag", result);
                    in.close();
                } catch (Exception e) {
                    Log.d("MyTag", e.toString());
                }

                if (response.charAt(71)=='1'){ Toast.makeText(getApplicationContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                    startActivity(login);
                    return;}
                else { Toast.makeText(getApplicationContext(), "Kayıt Hatalı"+response.charAt(71), Toast.LENGTH_SHORT).show();
                    signupFailed.create().show();
                    return;}


                }

        });
    }
}