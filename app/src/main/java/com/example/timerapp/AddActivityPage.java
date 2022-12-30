package com.example.timerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddActivityPage extends Fragment{

    private LinearLayout layoutList;
    Button addPreset;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_activity_page, container, false);
        addPreset = view.findViewById(R.id.addPreset);
        addPreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        // Display the list of workouts
        SharedPreferences sharedPref = getActivity().getBaseContext().getSharedPreferences("app_data", MODE_PRIVATE);
        Gson gson = new Gson();
        

        return view;
    }

    private void addView(String name){
        final View exerciseView = getLayoutInflater().inflate(R.layout.view_workout, null, false);
        EditText text_exerciseName = (EditText) exerciseView.findViewById(R.id.edit_exercise_name);
        ImageView play_workout = (ImageView) exerciseView.findViewById(R.id.workout_play);

        play_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                playWorkout(name);
            }
        });

        text_exerciseName.setText(name);
    }

    private void playWorkout(String name){
        return;
    }
}