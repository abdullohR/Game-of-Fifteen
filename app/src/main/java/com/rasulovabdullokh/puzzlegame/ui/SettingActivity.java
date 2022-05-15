package com.rasulovabdullokh.puzzlegame.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.rasulovabdullokh.puzzlegame.R;
import com.rasulovabdullokh.puzzlegame.core.app.App;
import com.rasulovabdullokh.puzzlegame.core.cache.MemoryHelper;

public class SettingActivity extends BaseActivity {
    private Switch sondSwitch, musicSwitch;
    private Button clearData;
    private RadioButton theme1,theme2;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sondSwitch=findViewById(R.id.sound_switch);
        musicSwitch =findViewById(R.id.music_switch);
        layout=findViewById(R.id.content);
        clearData=findViewById(R.id.clear_data);
        theme1=findViewById(R.id.theme_1);
        theme2=findViewById(R.id.theme_2);
        setLastData();
        setListeners();

    }

    private void setListeners() {
        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
        sondSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MemoryHelper.getHelper().setSoundState(b);
            }
        });
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MemoryHelper.getHelper().setMusicState(b);
                if(!b){
                    App.stopMedia();
                }else {
                    App.startMedia();
                }
                setLastData();
            }
        });
        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              MemoryHelper.getHelper().setTheme(R.drawable.img_2);
              setThemeData();
            }
        });
        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setTheme(R.drawable.img_4);
                setThemeData();
            }
        });
    }

    private void setLastData() {
        boolean isSoundChecked = MemoryHelper.getHelper().getSoundState();
        sondSwitch.setChecked(isSoundChecked);
        boolean isMusic = MemoryHelper.getHelper().getMusicState();
        musicSwitch.setChecked(isMusic);
//        1:39
    }
    private void showAlert() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("This button will be delete all data,a you sure?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            MemoryHelper.getHelper().clearData();
            Toast.makeText(this, "The data has been deleted." , Toast.LENGTH_SHORT).show();

        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        builder.create().show();
    }

    @Override
    public boolean shouldStopMedia() {
        return false;
    }

    @Override
    public void setThemeData() {
        layout.setBackgroundResource(MemoryHelper.getHelper().getThemeState());
    }
}