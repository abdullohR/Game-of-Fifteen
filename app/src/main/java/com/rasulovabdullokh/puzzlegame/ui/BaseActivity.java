package com.rasulovabdullokh.puzzlegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.rasulovabdullokh.puzzlegame.core.app.App;

public abstract class BaseActivity extends AppCompatActivity  {
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onStop() {
//        if(shouldStopMedia()){
//            App.stopMedia();
//        }
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setThemeData();
    }

    public abstract boolean shouldStopMedia();
    public abstract void setThemeData();
}
