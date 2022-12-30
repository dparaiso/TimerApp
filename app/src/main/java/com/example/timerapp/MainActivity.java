package com.example.timerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private QuickStartPage quickFrag;
    private AddActivityPage addFrag;
    private Presets presets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (extras != null && extras.containsKey("Presets")) {
            fragmentManager.beginTransaction()
                    .replace(R.id.view_pager, AddActivityPage.class, null)
                    .commit();
        }
        quickFrag = new QuickStartPage();
        addFrag = new AddActivityPage();


        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences("appData",Context.MODE_PRIVATE);
        String json = sharedPref.getString("presetData", null);
        Presets checkPresets = gson.fromJson(json, Presets.class);
        if (checkPresets == null){
            presets = new Presets();
            json = gson.toJson(presets);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("presetData", json);
            editor.apply();
        }


        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Quick Start");
                }
                if (position == 1) {
                    tab.setText("Presets");
                }

            }
        }).attach();
    }
}