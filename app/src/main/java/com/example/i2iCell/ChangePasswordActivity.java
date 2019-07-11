package com.example.i2iCell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //http://68.183.75.84:8080/i2iCellService/services/Services/changePassword?inputPhoneNumber=5315509999&inputTcNumber=57076105382&inputNewPassword=Abcdef123

    }
}
