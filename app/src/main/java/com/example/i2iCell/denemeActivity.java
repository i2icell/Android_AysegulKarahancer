package com.example.i2iCell;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class denemeActivity extends AppCompatActivity {

    View background;

    SeekBar redSeekBar;
    SeekBar greenSeekBar;
    SeekBar blueSeekBar;

    int redValue;
    int greenValue;
    int blueValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme);


        background = findViewById(R.id.background);

        redSeekBar = (SeekBar) findViewById(R.id.seekBarRed);
        greenSeekBar = (SeekBar) findViewById(R.id.seekBarGreen);
        blueSeekBar = (SeekBar) findViewById(R.id.seekBarBlue);

        redValue = redSeekBar.getProgress();
        greenValue = greenSeekBar.getProgress();
        blueValue = blueSeekBar.getProgress();

        //SeekBar'ların max değerlerini 255 olarak set ediyoruz.
        redSeekBar.setMax(255);
        greenSeekBar.setMax(255);
        blueSeekBar.setMax(255);

        //Arkaplanın başlangıç rengini set ediyoruz.
        View background = this.background;
        background.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue));


        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                //Arkaplanı yeni değere göre set ediyoruz.
                redValue = progress;
                denemeActivity.this.background.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue));
            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                //Arkaplanı yeni değere göre set ediyoruz.
                greenValue = progress;
                denemeActivity.this.background.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue));
            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                //Arkaplanı yeni değere göre set ediyoruz.
                blueValue = progress;
                denemeActivity.this.background.setBackgroundColor(Color.rgb(redValue, greenValue, blueValue));
            }
        });

    }

}
