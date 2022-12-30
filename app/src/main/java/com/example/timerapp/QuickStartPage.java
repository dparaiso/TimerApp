package com.example.timerapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuickStartPage extends Fragment {
    Button btnAdd , btnQuickStart, btnMinus, btnPlus;
    TextView mNumSets;
    EditText secondInput;
    EditText minuteInput;
    EditText second2Input;
    EditText minute2Input;
    boolean secondsCheck = false;
    boolean isSecondsCheck = false;
    boolean isSecondsCheck2 = false;
    int numSets;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_start_page, container, false);
        btnAdd = view.findViewById(R.id.add);
        btnQuickStart = view.findViewById(R.id.quickStart);
        btnMinus = view.findViewById(R.id.minus);
        btnPlus = view.findViewById(R.id.plus);
        mNumSets = view.findViewById(R.id.text_numSets);

        secondInput = view.findViewById(R.id.seconds);
        minuteInput = view.findViewById(R.id.minutes);
        second2Input = view.findViewById(R.id.seconds2);
        minute2Input = view.findViewById(R.id.minutes2);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getBaseContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        btnQuickStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(minuteInput.getText().toString().matches("")&&secondInput.getText().toString().matches("")){
                    return;
                }else if(minuteInput.getText().toString().matches("00")&&secondInput.getText().toString().matches("00")){
                    return;
                }else if(minuteInput.getText().toString().matches("")&&secondInput.getText().toString().matches("00")){
                    return;
                }
                secondsCheck = CheckSeconds();

                if (secondsCheck){
                    Intent intent = new Intent(getActivity().getBaseContext(), PrepareActivity.class);
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

        secondInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(secondInput.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + secondInput.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        secondInput.setText(edible);
                    }

                    if(secondInput.getText().toString().length() > 1){
                        isSecondsCheck = CheckSeconds();
                        if(!isSecondsCheck){
                            secondInput.setError("Invalid seconds, set to under 60!");

                        }
                    }

                }
            }
        });

        minuteInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(minuteInput.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + minuteInput.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        minuteInput.setText(edible);
                    }
                }
            }
        });

        second2Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(second2Input.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + second2Input.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        second2Input.setText(edible);
                    }
                    if(second2Input.getText().toString().length() > 1){
                        isSecondsCheck2 = CheckSeconds2();
                        if(!isSecondsCheck2){
                            second2Input.setError("Invalid seconds, set to under 60!");

                        }
                    }

                }
            }
        });

        minute2Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(minute2Input.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + minute2Input.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        minute2Input.setText(edible);
                    }
                }
            }
        });
        return view;
    }

    private boolean CheckSeconds(){
        if (Integer.parseInt(secondInput.getText().toString()) >= 60){
            secondInput.setError("Invalid seconds, set to under 60!");
            return false;
        }
        return true;
    }

    private boolean CheckSeconds2(){
        if (Integer.parseInt(second2Input.getText().toString()) >= 60){
            second2Input.setError("Invalid seconds, set to under 60!");
            return false;
        }
        return true;
    }
}