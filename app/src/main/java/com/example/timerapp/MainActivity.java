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
    private EditText second2Input;
    private EditText minute2Input;
    boolean secondsCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart =  (Button) findViewById(R.id.start);
        btnQuickStart =  (Button) findViewById(R.id.quickStart);

        secondInput = (EditText) findViewById(R.id.seconds);
        minuteInput = (EditText) findViewById(R.id.minutes);
        second2Input = (EditText) findViewById(R.id.seconds2);
        minute2Input = (EditText) findViewById(R.id.minutes2);

        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrepareActivity.class);
                int mins = Integer.parseInt(minuteInput.getText().toString());
                int secs = Integer.parseInt(secondInput.getText().toString());
                int mins2 = Integer.parseInt(minute2Input.getText().toString());
                int secs2 = Integer.parseInt(second2Input.getText().toString());
                intent.putExtra("minutes", mins);
                intent.putExtra("seconds", secs);
                intent.putExtra("minutes2", mins2);
                intent.putExtra("seconds2", secs2);
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
                    int mins2 = Integer.parseInt(minute2Input.getText().toString());
                    int secs2 = Integer.parseInt(second2Input.getText().toString());
                    intent.putExtra("minutes", mins);
                    intent.putExtra("seconds", secs);
                    intent.putExtra("minutes2", mins2);
                    intent.putExtra("seconds2", secs2);
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