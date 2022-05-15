package com.rasulovabdullokh.puzzlegame.core.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.rasulovabdullokh.puzzlegame.R;

import java.util.ArrayList;

public class MemoryHelper {


    private static  MemoryHelper helper;

    public static MemoryHelper getHelper() {
        return helper;
    }

    private SharedPreferences preferences;

    private MemoryHelper(Context context){
        preferences=context.getSharedPreferences("puzzle15",Context.MODE_PRIVATE);
    }

    public static void init(Context context){
        if(helper==null){
            helper= new MemoryHelper(context);
        }
    }
    public void saveData(UserData userData){
        preferences.edit().putString("name_"+getLastIndex(),userData.getName()).apply();

        preferences.edit().putInt("time_"+getLastIndex(),userData.getTime()).apply();

        preferences.edit().putInt("step_"+getLastIndex(),userData.getStep()).apply();

        saveLastIndex();
    }

    private  void saveLastIndex(){
        preferences.edit().putInt("index",getLastIndex()+1).apply();
    }

    private int getLastIndex(){
        return preferences.getInt("index", 0);
    }


    public UserData getData(int index){

        UserData userData = new UserData(
                preferences.getString("name_"+index,""),
                preferences.getInt("time_"+index, 0),
                preferences.getInt("step_"+index, 0)
        );
        return userData;
    }
    public ArrayList<UserData> getLastResults(){

        ArrayList<UserData> list = new ArrayList<>();

        int n =getLastIndex();

        for (int i = 0; i < n; i++) {
            list.add(getData(i));
        }
        return list;
    }
    public void clearData(){
        preferences.edit().clear().apply();
    }

    public void setSoundState(boolean b) {
        preferences.edit().putBoolean("sound_state",b).apply();
    }
    public boolean getSoundState(){
        return preferences.getBoolean("sound_state",true);
    }

    public void setMusicState(boolean b) {
        preferences.edit().putBoolean("music_state",b).apply();
    }
    public boolean getMusicState(){
        return preferences.getBoolean("music_state",true);
    }
    public  void setTheme(int theme){
        preferences.edit().putInt("theme_order",theme).apply();
    }
    public int getThemeState(){
        return preferences.getInt("theme_order", R.drawable.img_2);
    }
}
