package com.example.i2iCell;

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

                        final ArrayList<String> urls=new ArrayList<String>();
                        final TextView t;
                        try {
                            String myURL ="http://68.183.75.84:8080/i2iCellService/services/Services/" +
                                    "login?inputPhoneNumber=PNUMBER&inputPassword=PPASSWORD";
                            URL url = new URL(myURL);

                            myURL = myURL.replace("PNUMBER", "5511919680");
                            myURL = myURL.replace("PPASSWORD", "aysegul123");

                            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                            conn.setConnectTimeout(60000); // timing out in a minute

                            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            String str;
                            while ((str = in.readLine()) != null) {
                                Log.i("MyTag",str);
                                urls.add(str);
                                String result=urls.get(78);
                                here.setText(str);
                            }
                            in.close();

                        } catch (Exception e) {
                            Log.d("MyTag",e.toString());
                        }

            }
        });
    }

}
