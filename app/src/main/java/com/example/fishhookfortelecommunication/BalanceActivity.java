package com.example.fishhookfortelecommunication;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        final TextView here = (TextView) findViewById(R.id.hereIs);

        Button getdata = (Button)findViewById(R.id.data);
        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                        final ArrayList<String> urls=new ArrayList<String>(); //to read each line
                        //TextView t; //to show the result, please declare and find it inside onCreate()
                        final TextView t;


                        try {
                            // Create a URL for the desired page
                            String myURL ="http://68.183.75.84:8080/Calculator/services/DBConnection/" +
                                    "callLoginProcedure?inputPhoneNumber=PNUMBER&inputPassword=PPASSWORD";
                            URL url = new URL(myURL); //My text file location
                            //First open the connection

                            myURL = myURL.replace("PNUMBER", "5511919680");
                            myURL = myURL.replace("PPASSWORD", "aysegul123");

                            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                            conn.setConnectTimeout(60000); // timing out in a minute

                            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                            //t=(TextView)findViewById(R.id.TextView1); // ideally do this in onCreate()
                            String str;
                            while ((str = in.readLine()) != null) {
                                Log.i("MyTag",str);
                                urls.add(str);
                                List<String> head = urls.subList(0, 77);

                                String result=urls.get(78);
                                List<String> tail = urls.subList(79,124);

                                here.setText(str);

                            }
                            in.close();

                        } catch (Exception e) {
                            Log.d("MyTag",e.toString());
                        }
                        //since we are in background thread, to post results we have to go back to ui thread. do the following for that
            }
        });
    }

}
