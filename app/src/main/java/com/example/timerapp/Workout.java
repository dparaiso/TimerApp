package com.example.timerapp;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Workout implements Serializable {
    private ArrayList<Exercise> exercises;
    private String workoutName;

    public Workout(){
        this.exercises = new ArrayList<>();
    }

    // Get functions

    public String getWorkoutName(){ return workoutName; }

    public ArrayList<Exercise> getExercises(){ return exercises; }

    public int getNumExercises(){ return this.exercises.size(); }

    public int getTotalNumSets(){
        int numSets = 0;
        for (int i = 0; i < this.exercises.size(); i++){
            numSets += exercises.get(i).getNumSets();
        }
        return numSets;
    }

    // Set functions

    public void setWorkoutName(String workoutName){ this.workoutName = workoutName; }

    // Other

    public void addExercise(Exercise exercise){ this.exercises.add(exercise); }

    // Removes the last element
    public void removeExercise(){ this.exercises.remove(this.exercises.size() - 1); }

    public void clearList(){ exercises.clear(); }
}