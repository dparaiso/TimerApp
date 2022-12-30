package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
//import android.support.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;
import android.widget.Toast;

public class PrepareActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS_DEFAULT = 5000;

    private TextView mTextViewCountDown;
    private TextView mTextTitle;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button mButtonMainMenu;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    private boolean mResting;
    private int mNumSets, setsSoFar;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS_DEFAULT;

    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
        mResting = false;
        mIntent = getIntent();
        mTimeLeftInMillis = 1000 * (60 * (long) mIntent.getIntExtra("minutes", 0) + mIntent.getIntExtra("seconds", 0) + 1);
        mNumSets = mIntent.getIntExtra("numSets", 1);
        setsSoFar = 1;

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mTextTitle = findViewById(R.id.text_title);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonMainMenu = findViewById(R.id.button_back_to_main_menu);

        mButtonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrepareActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        startTimer();
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                }
                else  {
                    startTimer();
                }
            }
        });
        updateCountDownText();
    }
    private void startTimer() {
        if (mResting){
            mTextTitle.setText("Rest!");
        }
        else{
            mTextTitle.setText("Work!");
        }

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                if (!mResting){
                    if (setsSoFar != mNumSets){
                        mTimeLeftInMillis = 1000 * (60 * (long) mIntent.getIntExtra("minutes2", 0) + mIntent.getIntExtra("seconds2", 0) + 1);
                        mResting = true;
                        startTimer();
                    }
                    else{
                        mTextTitle.setText("Done!");
                        return;
                    }
                }
                else{ // Finished Rest
                    mTimeLeftInMillis = 1000 * (60 * (long) mIntent.getIntExtra("minutes", 0) + mIntent.getIntExtra("seconds", 0) + 1);
                    mResting = false;
                    if (setsSoFar < mNumSets){ // if true, do again
                        setsSoFar++;
                        startTimer();
                    }
                    else{ // done
                        mTextTitle.setText("Done!");
                        return;
                    }
                }
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
