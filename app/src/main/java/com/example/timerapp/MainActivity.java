package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private EditText minTimerText;
    private EditText secTimerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart =  (Button) findViewById(R.id.start);
        minTimerText = (EditText) findViewById(R.id.minTimer);
        secTimerText = (EditText) findViewById(R.id.secTimer);

        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrepareActivity.class);
                int secs = Integer.parseInt(secTimerText.getText().toString());
                int mins = Integer.parseInt(minTimerText.getText().toString());
                intent.putExtra("seconds", secs);
                intent.putExtra("minutes", mins);
                startActivity(intent);
            }
        });
    }

}