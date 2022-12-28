package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd , btnQuickStart, btnMinus, btnPlus;
    private TextView mNumSets;
    private EditText secondInput;
    private EditText minuteInput;
    private EditText second2Input;
    private EditText minute2Input;
    boolean secondsCheck = false;
    private int numSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numSets = 1;

        btnAdd =  (Button) findViewById(R.id.add);
        btnQuickStart =  (Button) findViewById(R.id.quickStart);
        btnMinus = (Button) findViewById(R.id.minus);
        btnPlus = (Button) findViewById(R.id.plus);
        mNumSets = (TextView) findViewById(R.id.text_numSets);

        secondInput = (EditText) findViewById(R.id.seconds);
        minuteInput = (EditText) findViewById(R.id.minutes);
        second2Input = (EditText) findViewById(R.id.seconds2);
        minute2Input = (EditText) findViewById(R.id.minutes2);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        btnQuickStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                secondsCheck = CheckSeconds();
                if (secondsCheck){
                    Intent intent = new Intent(MainActivity.this, PrepareActivity.class);
                    if (!minuteInput.getText().toString().matches("")){
                        int mins = Integer.parseInt(minuteInput.getText().toString());
                        intent.putExtra("minutes", mins);
                    }
                    if (!secondInput.getText().toString().matches("")){
                        int secs = Integer.parseInt(secondInput.getText().toString());
                        intent.putExtra("seconds", secs);
                    }
                    if (!minute2Input.getText().toString().matches("")){
                        int mins2 = Integer.parseInt(minute2Input.getText().toString());
                        intent.putExtra("minutes2", mins2);

                    }
                    if (!second2Input.getText().toString().matches("")){
                        int secs2 = Integer.parseInt(second2Input.getText().toString());
                        intent.putExtra("seconds2", secs2);
                    }
                    intent.putExtra("numSets", numSets);
                    startActivity(intent);
                }
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(numSets > 1){
                    numSets--;
                }
                mNumSets.setText(String.format("%d", numSets));
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mNumSets.setText(String.format("%d", ++numSets));
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