package com.example.timerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private QuickStartPage quickFrag;
    private AddActivityPage addFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quickFrag = new QuickStartPage();
        addFrag = new AddActivityPage();
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.view_pager, quickFrag)
//                .commit();
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Quick Start");
                }
                if (position == 1){
                    tab.setText("Presets");
                }

            }
        }).attach();

//        numSets = 1;
//
//        btnAdd =  (Button) findViewById(R.id.add);
//        btnQuickStart =  (Button) findViewById(R.id.quickStart);
//        btnMinus = (Button) findViewById(R.id.minus);
//        btnPlus = (Button) findViewById(R.id.plus);
//        mNumSets = (TextView) findViewById(R.id.text_numSets);
//
//        secondInput = (EditText) findViewById(R.id.seconds);
//        minuteInput = (EditText) findViewById(R.id.minutes);
//        second2Input = (EditText) findViewById(R.id.seconds2);
//        minute2Input = (EditText) findViewById(R.id.minutes2);
//
//        btnAdd.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnQuickStart.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//
//                if(minuteInput.getText().toString().matches("")&&secondInput.getText().toString().matches("")){
//                    return;
//                }else if(minuteInput.getText().toString().matches("00")&&secondInput.getText().toString().matches("00")){
//                    return;
//                }else if(minuteInput.getText().toString().matches("")&&secondInput.getText().toString().matches("00")){
//                    return;
//                }
//                secondsCheck = CheckSeconds();
//
//                if (secondsCheck){
//                    Intent intent = new Intent(MainActivity.this, PrepareActivity.class);
//                    if (!minuteInput.getText().toString().matches("")){
//                        int mins = Integer.parseInt(minuteInput.getText().toString());
//                        intent.putExtra("minutes", mins);
//                    }
//                    if (!secondInput.getText().toString().matches("")){
//                        int secs = Integer.parseInt(secondInput.getText().toString());
//                        intent.putExtra("seconds", secs);
//                    }
//                    if (!minute2Input.getText().toString().matches("")){
//                        int mins2 = Integer.parseInt(minute2Input.getText().toString());
//                        intent.putExtra("minutes2", mins2);
//
//                    }
//                    if (!second2Input.getText().toString().matches("")){
//                        int secs2 = Integer.parseInt(second2Input.getText().toString());
//                        intent.putExtra("seconds2", secs2);
//                    }
//                    intent.putExtra("numSets", numSets);
//                    startActivity(intent);
//                }
//            }
//        });
//
//        btnMinus.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                if(numSets > 1){
//                    numSets--;
//                }
//                mNumSets.setText(String.format("%d", numSets));
//            }
//        });
//
//        btnPlus.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                mNumSets.setText(String.format("%d", ++numSets));
//            }
//        });
//
//        secondInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus){
//                    if(secondInput.getText().toString().length() == 1){
//                        String tmp = "0";
//                        tmp = tmp + secondInput.getText().toString();
//                        Editable edible = new SpannableStringBuilder(tmp);
//                        secondInput.setText(edible);
//                    }
//
//                    if(secondInput.getText().toString().length() > 1){
//                        isSecondsCheck = CheckSeconds();
//                        if(!isSecondsCheck){
//                            secondInput.setError("Invalid seconds, set to under 60!");
//
//                        }
//                    }
//
//                }
//            }
//        });
//
//        minuteInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus){
//                    if(minuteInput.getText().toString().length() == 1){
//                        String tmp = "0";
//                        tmp = tmp + minuteInput.getText().toString();
//                        Editable edible = new SpannableStringBuilder(tmp);
//                        minuteInput.setText(edible);
//                    }
//                }
//            }
//        });
//
//        second2Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus){
//                    if(second2Input.getText().toString().length() == 1){
//                        String tmp = "0";
//                        tmp = tmp + second2Input.getText().toString();
//                        Editable edible = new SpannableStringBuilder(tmp);
//                        second2Input.setText(edible);
//                    }
//                    if(second2Input.getText().toString().length() > 1){
//                        isSecondsCheck2 = CheckSeconds2();
//                        if(!isSecondsCheck2){
//                            second2Input.setError("Invalid seconds, set to under 60!");
//
//                        }
//                    }
//
//                }
//            }
//        });
//
//        minute2Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus){
//                    if(minute2Input.getText().toString().length() == 1){
//                        String tmp = "0";
//                        tmp = tmp + minute2Input.getText().toString();
//                        Editable edible = new SpannableStringBuilder(tmp);
//                        minute2Input.setText(edible);
//                    }
//                }
//            }
//        });

    }
//    private boolean CheckSeconds(){
//        if (Integer.parseInt(secondInput.getText().toString()) >= 60){
//            secondInput.setError("Invalid seconds, set to under 60!");
//            return false;
//        }
//        return true;
//    }
//
//    private boolean CheckSeconds2(){
//        if (Integer.parseInt(second2Input.getText().toString()) >= 60){
//            second2Input.setError("Invalid seconds, set to under 60!");
//            return false;
//        }
//        return true;
//    }

}