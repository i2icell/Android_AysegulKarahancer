package com.example.i2iCell;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.i2iCell.R.id.phoneNum;

public class BalanceActivity extends AppCompatActivity {


            protected void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_balance);
                //http://68.183.75.84:8080/i2iCellService/services/Services/getBalancesResponse?inputPhoneNumber=5327981750
                TextView gb = (TextView) findViewById(R.id.gigabyte);
                TextView dk = (TextView) findViewById(R.id.DK);
                TextView sms = (TextView) findViewById(R.id.SMS);
                Bundle bundle =getIntent().getExtras();
                String data = bundle.getString("datam");

                final Intent goBackToMain = new Intent(getBaseContext(),MainActivity.class);
                final ImageView goBackMain = (ImageView)findViewById(R.id.goBack3);
                final ArrayList<String> urls = new ArrayList<String>();
                final TextView t;
                goBackMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        startActivity(goBackToMain);
                    }
                });
                String response = "";
                try {
                    String myURL = "http://68.183.75.84:8080/i2iCellService/services/Services/getBalancesResponse?inputPhoneNumber="
                            + data;
                    URL url = new URL(myURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        Log.i("", str);
                        response += str;
                    }
                    String[] xmls = response.split("<ns:return>");
                    String withoutReturnXML = xmls[1] + xmls[2] + xmls[3];
                    String[] lastXMLS = withoutReturnXML.split("</ns:return>");
                    String LasResponse = lastXMLS[0] + " " + lastXMLS[1] + " " + lastXMLS[2];
                    Toast.makeText(getApplicationContext(), LasResponse, Toast.LENGTH_SHORT).show();
                    String result = "" + response.charAt(62);
                    gb.setText(lastXMLS[0]);
                    dk.setText(lastXMLS[1]);
                    sms.setText(lastXMLS[2]);

                    in.close();
                } catch (Exception e) {
                    Log.d("MyTag", e.toString());
                }

            }
        }
