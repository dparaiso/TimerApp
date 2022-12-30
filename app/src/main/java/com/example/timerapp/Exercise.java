package com.example.timerapp;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String workoutName;
    private int workMinutes, workSeconds, restMinutes, restSeconds, numSets;

    public Exercise(){

    }

    public Exercise (String workoutName, int workMinutes, int workSeconds, int restMinutes, int restSeconds, int numSets){
        this.workoutName = workoutName;
        this.workMinutes = workMinutes;
        this.workSeconds = workSeconds;
        this.restMinutes = restMinutes;
        this.restSeconds = restSeconds;
        this.numSets = numSets;
    }

    // Get functions
    public String getWorkoutName(){ return workoutName; }
    public int getWorkMinutes(){ return workMinutes; }
    public int getWorkSeconds(){ return workSeconds; }
    public int getRestMinutes(){ return restMinutes; }
    public int getRestSeconds(){ return restSeconds; }
    public int getNumSets(){ return numSets; }

    // Set functions
    public void setWorkoutName(String workoutName){ this.workoutName = workoutName; }
    public void setWorkMinutes(int workMinutes){ this.workMinutes = workMinutes; }
    public void setWorkSeconds(int workSeconds){ this.workSeconds = workSeconds; }
    public void setRestMinutes(int restMinutes){ this.restMinutes = restMinutes; }
    public void setRestSeconds(int restSeconds){ this.restSeconds = restSeconds; }
    public void setNumSets(int numSets){ this.numSets = numSets; }

}