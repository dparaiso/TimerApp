package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import java.util.ArrayList;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout layoutList;
    private Button buttonAdd;
    private Button buttonSubmitWorkout;
    private Workout workout = new Workout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmitWorkout = findViewById(R.id.button_submit_list);

        buttonAdd.setOnClickListener(this);
        buttonSubmitWorkout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button_add:
                addView();
                break;

            case R.id.button_submit_list:

                if(checkValidityAndUpdate()){
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", workout);
                    intent.putExtras(bundle);
                    intent.putExtra("Presets", true);
                    startActivity(intent);
                }

                break;
        }
    }

    private boolean checkValidityAndUpdate() {
        workout.clearList();

        for (int i = 0; i < layoutList.getChildCount(); i++) {

            View exerciseView = layoutList.getChildAt(i);

            // Get information from the layout: name, sets, work and rest time
            EditText exerciseName = (EditText) exerciseView.findViewById(R.id.edit_exercise_name);
            EditText workMinutes = (EditText) findViewById(R.id.work_minutes);
            EditText workSeconds = (EditText) findViewById(R.id.work_seconds);
            EditText restMinutes = (EditText) findViewById(R.id.rest_minutes);
            EditText restSeconds = (EditText) findViewById(R.id.rest_seconds);
            TextView numSets = (TextView) exerciseView.findViewById(R.id.text_numSets);

            if (!checkValidTimes(workSeconds, workMinutes)){
                Toast.makeText(this, "Must Enter Work Time!", Toast.LENGTH_SHORT).show();
                return false;
            }

            Exercise newExercise = new Exercise();

            //add to exercise and error check
            if(!workMinutes.getText().toString().matches("")){
                newExercise.setWorkMinutes(Integer.parseInt(workMinutes.getText().toString()));
            }else{
                newExercise.setWorkMinutes(0);
            }

            if(!workSeconds.getText().toString().matches("")){
                newExercise.setWorkSeconds(Integer.parseInt(workSeconds.getText().toString()));
            }else{
                newExercise.setWorkSeconds(0);
            }

            if(!restMinutes.getText().toString().matches("")){
                newExercise.setRestMinutes(Integer.parseInt(restMinutes.getText().toString()));
            }else{
                newExercise.setRestMinutes(0);
            }

            if(!restSeconds.getText().toString().matches("")){
                newExercise.setRestSeconds(Integer.parseInt(restSeconds.getText().toString()));
            }else{
                newExercise.setRestSeconds(0);
            }

            workout.addExercise(newExercise);
        }

        if (workout.getNumExercises() == 0){
            Toast.makeText(this, "Add Exercises First!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkValidTimes(EditText workSeconds, EditText workMinutes){
        if ( workSeconds.getText().toString().matches("") || workSeconds.getText().toString().matches("00")
            && workMinutes.getText().toString().matches("") || workMinutes.getText().toString().matches("00")){
            return false;
        }
        return true;
    }

    private void addView(){
        final View exerciseView = getLayoutInflater().inflate(R.layout.add_exercise, null, false); // add_exercise.xml

        EditText text_exerciseName = (EditText) exerciseView.findViewById(R.id.edit_exercise_name);
        final EditText text_workMinutes = (EditText) exerciseView.findViewById(R.id.work_minutes);
        final EditText text_workSeconds = (EditText) exerciseView.findViewById(R.id.work_seconds);
        final EditText text_restMinutes = (EditText) exerciseView.findViewById(R.id.rest_minutes);
        final EditText text_restSeconds = (EditText) exerciseView.findViewById(R.id.rest_seconds);
        final TextView text_numSets = (TextView) exerciseView.findViewById(R.id.text_numSets);
        Button button_setsPlus = (Button) exerciseView.findViewById(R.id.plus);
        Button button_setsMinus = (Button) exerciseView.findViewById(R.id.minus);

        ImageView imageClose = (ImageView) exerciseView.findViewById(R.id.exercise_remove);

        button_setsMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int numSets = Integer.parseInt(text_numSets.getText().toString());
                if(numSets > 1){
                    numSets--;
                }
                text_numSets.setText(String.format("%d", numSets)); // why is this underlined
            }
        });

        button_setsPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int numSets = Integer.parseInt(text_numSets.getText().toString());
                text_numSets.setText(String.format("%d", ++numSets)); // why is this underlined
            }
        });

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                removeView(exerciseView);
            }
        });

        text_workMinutes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(text_workMinutes.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + text_workMinutes.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        text_workMinutes.setText(edible);
                    }
                }
            }
        });

        text_workSeconds.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(text_workSeconds.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + text_workSeconds.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        text_workSeconds.setText(edible);
                    }

                    if(text_workSeconds.getText().toString().length() > 1 && Integer.parseInt(text_workSeconds.getText().toString()) >= 60){
                        text_workSeconds.setError("Invalid seconds, set to under 60!");
                        return;
                    }
                }
            }
        });

        text_restMinutes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(text_workMinutes.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + text_workMinutes.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        text_workMinutes.setText(edible);
                    }
                }
            }
        });

        text_restSeconds.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(text_restSeconds.getText().toString().length() == 1){
                        String tmp = "0";
                        tmp = tmp + text_restSeconds.getText().toString();
                        Editable edible = new SpannableStringBuilder(tmp);
                        text_restSeconds.setText(edible);
                    }

                    if(text_restSeconds.getText().toString().length() > 1 && Integer.parseInt(text_restSeconds.getText().toString()) >= 60){
                        text_restSeconds.setError("Invalid seconds, set to under 60!");
                        return;
                    }
                }
            }
        });

        layoutList.addView(exerciseView);
    }

    private void removeView(View view){
        layoutList.removeView(view);
    }
}