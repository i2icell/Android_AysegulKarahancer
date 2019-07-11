package com.example.i2iCell;

import androidx.appcompat.app.AppCompatActivity;

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

public class passwordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        final EditText phone = (EditText) findViewById(R.id.phoneChange);
        final EditText tc = (EditText) findViewById(R.id.tcChange);
        final EditText pass = (EditText) findViewById(R.id.newPassword);
        final Button changebutton = (Button) findViewById(R.id.changeButton);

        final TextView t = (TextView) findViewById(R.id.textView20);
        final String v_phone = String.valueOf(phone.getText());
        final String v_tc = String.valueOf(tc.getText());
        final String v_pass = String.valueOf(pass.getText());

        changebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String response = "";
                //t.setText("uyuuyuyuyuyuyuyu");
                Log.d("girdi mi",response);
                String lalala="";
                try {
                    String myURL ="http://68.183.75.84:8080/i2iCellService/services/Services/changePassword?" +
                            "inputPhoneNumber=" +
                            phone.getText() +
                            "&inputTcNumber=" +
                            tc.getText()+
                            "&inputNewPassword=" +
                            pass.getText() ;

                    URL url = new URL(myURL);
                    lalala = myURL;
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(60000);
                    Log.d("lolo1",response);
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    Log.d("lolo2",response);
                    String str;
                    while ((str = in.readLine()) != null) {
                        Log.i("",str);
                        response += str;
                        Log.i("MySTR",str);
                    }

                    int l = response.length();
                t.setText(l);
                Log.i("resultFromXMLTag",response);
                in.close();
            } catch (Exception e) {
                Log.d("MyTag",e.toString());
            }

                if(String.valueOf(response.charAt(72)).equals("1")){ Toast.makeText(getApplicationContext(),"Parolanız Başarıyla Değiştirildi", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(String.valueOf(response.charAt(72)).equals("0")){ Toast.makeText(getApplicationContext(), "Parolanız Değiştirilemedi", Toast.LENGTH_SHORT).show();
                    t.setText(lalala);

                    return;
                }
                else
                    finish();
                    startActivity(getIntent());
                    return;

            }

        });

    }
}
