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
import android.widget.Toast;

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
        layoutList = view.findViewById(R.id.layout_list);

        // Display the list of workouts
        SharedPreferences sharedPref = getActivity().getBaseContext().getSharedPreferences("app_data", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("presetData", null);
        Presets presets = gson.fromJson(json, Presets.class);
        if (presets != null){
            for (int i = 0; i < presets.getPresetCount(); i++){
                Workout w = presets.getWorkout(i);
                String name = w.getWorkoutName();
                addView(name);
            }
        }
        return view;
    }

    private void addView(String name){
        View workoutView = getLayoutInflater().inflate(R.layout.view_workout, null, false);
        EditText text_workoutName = (EditText) workoutView.findViewById(R.id.edit_workout_name);
        ImageView play_workout = (ImageView) workoutView.findViewById(R.id.workout_play);

        play_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                playWorkout(name);
            }
        });

        text_workoutName.setText(name);
        layoutList.addView(workoutView);
       return;
    }

    private void playWorkout(String name){
        return;
    }
}