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


        setContentView(R.layout.activity_balance);

        super.onCreate(savedInstanceState);
   }
}
