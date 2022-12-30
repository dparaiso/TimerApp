package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import java.util.ArrayList;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout layoutList;
    private Button buttonAdd;
    private Button buttonSubmitWorkout;
    private Workout workout = new Workout();
//    private Presets presets;

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
                    SharedPreferences sharePref = getSharedPreferences("appData", MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = sharePref.getString("presetData", null);
                    Presets presets = gson.fromJson(json, Presets.class);
                    presets.addPreset(workout);

                    json = gson.toJson(presets);
                    SharedPreferences.Editor editor = sharePref.edit();
                    editor.putString("presetData", json);
                    editor.apply();

                    Intent intent = new Intent(AddActivity.this, QuickStartPage.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", workout);
                    intent.putExtras(bundle);
                    intent.putExtra("Presets", true);
                    finish();
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

            if (!checkValidTimes(workSeconds, restSeconds)){
                Toast.makeText(this, "Times entered are not valid!", Toast.LENGTH_SHORT).show();
                return false;
            }

            Exercise newExercise = new Exercise(
                    exerciseName.getText().toString(),
                    Integer.parseInt(workMinutes.getText().toString()),
                    Integer.parseInt(workSeconds.getText().toString()),
                    Integer.parseInt(restMinutes.getText().toString()),
                    Integer.parseInt(restSeconds.getText().toString()),
                    Integer.parseInt(numSets.getText().toString())
            );

            workout.addExercise(newExercise);
        }

        if (workout.getNumExercises() == 0){
            Toast.makeText(this, "Add Exercises First!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkValidTimes(EditText workSeconds, EditText restSeconds){
        if ( workSeconds.getText().toString().equals("") || workSeconds.getText().toString().equals("0") || workSeconds.getText().toString().equals("00") ){
            return false;
        }
        if ( restSeconds.getText().toString().equals("") || restSeconds.getText().toString().equals("0") || restSeconds.getText().toString().equals("00") ){
            return false;
        }
        return true;
    }

    private void addView(){
        final View exerciseView = getLayoutInflater().inflate(R.layout.add_exercise, null, false); // add_exercise.xml

        EditText text_exerciseName = (EditText) exerciseView.findViewById(R.id.edit_exercise_name);
        EditText text_workMinutes = (EditText) exerciseView.findViewById(R.id.work_minutes);
        EditText text_workSeconds = (EditText) exerciseView.findViewById(R.id.work_seconds);
        EditText text_restMinutes = (EditText) exerciseView.findViewById(R.id.rest_minutes);
        EditText text_restSeconds = (EditText) exerciseView.findViewById(R.id.rest_seconds);
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

        layoutList.addView(exerciseView);
    }

    private void removeView(View view){
        layoutList.removeView(view);
    }
}