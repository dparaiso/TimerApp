package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import java.util.ArrayList;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSubmitWorkout;
    Workout workout = new Workout();
//    ArrayList<Exercise> workoutList = new ArrayList<>();

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

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(AddActivity.this, ActivityCricketers.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",cricketersList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }
    }

    private boolean checkValidityAndUpdate() {
        workout.clearList();
        boolean valid = true;

        for (int i = 0; i < layoutList.getChildCount(); i++) {

            View exerciseView = layoutList.getChildAt(i);

            // Get information from the layout: name, sets, work and rest time
            EditText exerciseName = (EditText) exerciseView.findViewById(R.id.edit_exercise_name);
            EditText workMinutes = (EditText) findViewById(R.id.text_work_minutes);
            EditText workSeconds = (EditText) findViewById(R.id.text_work_seconds);
            EditText restMinutes = (EditText) findViewById(R.id.text_rest_minutes);
            EditText restSeconds = (EditText) findViewById(R.id.text_rest_seconds);
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

        // Fill in later when have xml file !!!!
        EditText editText = (EditText) exerciseView.findViewById(R.id.edit_exercise_name);
        ImageView imageClose = (ImageView) exerciseView.findViewById(R.id.image_remove);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                removeView(exerciseView);
            }
        });
    }

    private void removeView(View view){
        layoutList.removeView(view);
    }
}