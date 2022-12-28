package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private EditText setNum;
    private EditText minutes;
    private EditText seconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStart = findViewById(R.id.start);
        setNum = findViewById(R.id.setNum);
        minutes = findViewById(R.id.timerMinutes);
        seconds = findViewById(R.id.timerSeconds);

        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrepareActivity.class);
                startActivity(intent);
            }
        });

        seconds.setInputType(InputType.TYPE_CLASS_DATETIME);
        minutes.setInputType(InputType.TYPE_CLASS_DATETIME);
        seconds.addTextChangedListener(new TextWatcher() {
            int prevLen = 0;
            int index = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                prevLen = seconds.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.toString().length();
                if(length == 0){
                    return;
                }
            }
        });

        seconds.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(seconds.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + seconds.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        seconds.setText(edible);
                    }
                }
            }
        });

    }

}