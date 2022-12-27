package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnStart , btnQuickStart;
    private EditText secondInput;
    private EditText minuteInput;
    boolean secondsCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart =  (Button) findViewById(R.id.start);
        btnQuickStart =  (Button) findViewById(R.id.quickStart);

        secondInput = (EditText) findViewById(R.id.seconds);
        minuteInput = (EditText) findViewById(R.id.minutes);

        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrepareActivity.class);
                int mins = Integer.parseInt(minuteInput.getText().toString());
                int secs = Integer.parseInt(secondInput.getText().toString());
                intent.putExtra("minutes", mins);
                intent.putExtra("seconds", secs);
                startActivity(intent);
            }
        });

        btnQuickStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                secondsCheck = CheckSeconds();
                if (secondsCheck){
                    Intent intent = new Intent(MainActivity.this, PrepareActivity.class);
                    int mins = Integer.parseInt(minuteInput.getText().toString());
                    int secs = Integer.parseInt(secondInput.getText().toString());
                    intent.putExtra("minutes", mins);
                    intent.putExtra("seconds", secs);
                    startActivity(intent);
                }
            }
        });

    }
    private boolean CheckSeconds(){
        if (Integer.parseInt(secondInput.getText().toString()) >= 60){
            secondInput.setError("Invalid seconds, set to under 60!");
            return false;
        }
        return true;
    }

}