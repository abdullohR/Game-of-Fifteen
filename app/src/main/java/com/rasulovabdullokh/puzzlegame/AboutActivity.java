package com.rasulovabdullokh.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rasulovabdullokh.puzzlegame.core.cache.MemoryHelper;
import com.rasulovabdullokh.puzzlegame.ui.BaseActivity;
import com.rasulovabdullokh.puzzlegame.ui.MainActivity;
import com.rasulovabdullokh.puzzlegame.ui.ResultActivity;
import com.rasulovabdullokh.puzzlegame.ui.SettingActivity;
import com.rasulovabdullokh.puzzlegame.ui.StartActivity;

public class AboutActivity extends BaseActivity implements View.OnClickListener{
    private ImageView view;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        view = findViewById(R.id.image_back);
        view.setOnClickListener(this);
        layout=findViewById(R.id.content);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back: {
                Intent intent = new Intent(AboutActivity.this, StartActivity.class);
                startActivity(intent);
                break;
            }
        }
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