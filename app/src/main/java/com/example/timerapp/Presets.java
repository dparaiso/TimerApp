package com.example.timerapp;

import java.util.ArrayList;

public class Presets {
    private int presetCount = 0;
    private ArrayList<Workout> presets;

    public Presets(){

    }

    public int getPresetCount(){ return presetCount; }
    public ArrayList<Workout> getPresets (){ return presets; }
    public Workout getWorkout (int i){ return presets.get(i); }


    public void addPreset (Workout newWorkout){
        this.presets.add(newWorkout);
        this.presetCount++;
    }

    public void removePreset (int i){
        if (i < this.presetCount)
            return;
        this.presets.remove(i);
        this.presetCount--;
    }
}
