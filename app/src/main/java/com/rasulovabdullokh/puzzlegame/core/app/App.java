package com.rasulovabdullokh.puzzlegame.core.app;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import com.rasulovabdullokh.puzzlegame.R;
import com.rasulovabdullokh.puzzlegame.core.cache.MemoryHelper;

public class App extends Application {
    private static Context appContext;
    private static MediaPlayer mediaPlayer;
    public static void startMedia(){
        mediaPlayer= MediaPlayer.create(appContext, R.raw.pirat);
        mediaPlayer.setLooping(true);
        if (mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
    public static void stopMedia(){
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
        MemoryHelper.init(this);
        if(MemoryHelper.getHelper().getMusicState()){
            startMedia();
        }
    }

    @Override
    public void onTerminate() {
        stopMedia();
        mediaPlayer=null;
        super.onTerminate();
    }

}
