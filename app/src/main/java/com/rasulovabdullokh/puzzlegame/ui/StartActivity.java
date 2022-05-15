package com.rasulovabdullokh.puzzlegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rasulovabdullokh.puzzlegame.AboutActivity;
import com.rasulovabdullokh.puzzlegame.R;
import com.rasulovabdullokh.puzzlegame.core.cache.MemoryHelper;

public class StartActivity extends BaseActivity implements View.OnClickListener {

    private TextView startButton, settingButton, exitButton, resultButton;
    private ImageView aboutView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        loadView();

        exitButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        resultButton.setOnClickListener(this);
        aboutView.setOnClickListener(this);


    }

    private void loadView() {
        aboutView=findViewById(R.id.info_view);
        frameLayout = findViewById(R.id.content_start);

        settingButton = findViewById(R.id.btn_settings);
        startButton = findViewById(R.id.btn_start);
        exitButton = findViewById(R.id.btn_exit);
        resultButton = findViewById(R.id.btn_result);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start: {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_settings: {
                Intent intent = new Intent(StartActivity.this, SettingActivity.class);
                startActivity(intent);
                break;

            }
            case R.id.btn_result: {
                Intent intent = new Intent(StartActivity.this, ResultActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_exit: {
                finish();
                break;
            }
            case R.id.info_view: {
                Intent intent = new Intent(StartActivity.this, AboutActivity.class);
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
        frameLayout.setBackgroundResource(MemoryHelper.getHelper().getThemeState());
    }
}