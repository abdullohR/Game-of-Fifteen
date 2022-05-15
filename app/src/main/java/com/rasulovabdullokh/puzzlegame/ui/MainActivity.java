package com.rasulovabdullokh.puzzlegame.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rasulovabdullokh.puzzlegame.R;
import com.rasulovabdullokh.puzzlegame.core.cache.MemoryHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    private LinearLayout layout;
    private ArrayList<Integer> numbers = new ArrayList<>();
    private int stepCount=0;
    private int timeCount= 0;
    private TextView stepView;
    private TextView timeView;
    private Button[][] buttons= new Button[4][4];
    private RelativeLayout buttonGroup;
    private int emptyI=3;
    private int emptyJ=3;
    private Timer timer;
    private Button finishButton, btn_rest,pause_btn;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=findViewById(R.id.content);
        loadViews();
        mediaPlayer = MediaPlayer.create(this,R.raw.swipe);
        loadNumbers();
        setDataToView();

        createTimer();
        setListener();
    }
    private void setListener() {
        finishButton.setOnClickListener(view -> {
            showAlert();});
        btn_rest.setOnClickListener(view -> restartGame());
    }
    private void createTimer() {
        if(timer != null){
            timer.cancel();
            timer = null;
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeCount++;
                        setTime(timeCount);
                    }
                });
            }
        }, 1000, 1000);
    }
    private void setTime(int time){
        int hour = time/3600;
        int minute = time%3600/60;
        int second = time % 60;
        timeView.setText(String.format("%02d:%02d.%02d",hour,minute,second));

    }
    private void loadViews() {

        pause_btn=findViewById(R.id.pause_btn);
        buttonGroup= findViewById(R.id.button_group);
        stepView = findViewById(R.id.step_view);
        timeView = findViewById(R.id.timer_view);
        finishButton= findViewById(R.id.finish_btn);
        btn_rest=findViewById(R.id.restart_btn);
        int a=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = (Button) buttonGroup.getChildAt(a++);
            }
        }
    }
    private void loadNumbers() {
        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }
    private void setDataToView() {
        for (int i = 0; i < 15; i++) {
            Button button = buttons[i/4][i%4];
            button.setText(String.valueOf(numbers.get(i)));
            button.setBackgroundResource(R.drawable.fill_button);
        }
        buttons[emptyI][emptyJ].setBackgroundResource(R.drawable.empty_button);
        buttons[emptyI][emptyJ].setText("");
    }
    public void buttonClick(View view) {
        Button button = (Button) view;

        String tag = button.getTag().toString();

        String indexes[] = tag.split(":");

        int i=Integer.parseInt(indexes[0]);

        int j=Integer.parseInt(indexes[1]);

        int deltaI= Math.abs(i - emptyI);

        int deltaJ= Math.abs(j - emptyJ);

        if((deltaI==1 && deltaJ==0) || (deltaI==0 && deltaJ==1)){

            buttons[emptyI][emptyJ].setText(button.getText());

            buttons[emptyI][emptyJ].setBackground(button.getBackground());

            button.setText("");
            button.setBackgroundResource(R.drawable.empty_button);

            emptyI=i;

            emptyJ=j;

            if(MemoryHelper.getHelper().getSoundState()){
                mediaPlayer.start();
            }else{
                mediaPlayer.stop();
            }

            stepCount++;
            setStep();

            if(emptyI==3 && emptyJ==3){
                checkWin();
            }
        }else{

        }
    }
    private void setStep() {
        stepView.setText(String.format("Step : "+stepCount));
    }
    public void checkWin(){

        boolean isWin = true;
        for (int i = 0; i < 15; i++) {
            Button button = (Button) buttonGroup.getChildAt(i);
            if(!button.getText().toString().equals(i+1+"")){
                isWin= false;
                break;
            }
        }
        if(isWin){
            Intent intent = new Intent(this,ResultSaveActivity.class);
            intent.putExtra("step",stepCount);
            intent.putExtra("time",timeCount);
            startActivity(intent);
            finish();
        }else{

        }
    }
    private void showAlert() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        timer.cancel();
        builder.setTitle("A you sure?");

        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            finish();

        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
            createTimer();
        });
        builder.create().show();
    }
    private void restartGame() {
        timer.cancel();

        timeCount = 0;
        stepCount = 0;

        emptyI = 3;
        emptyJ = 3;
        setTime(timeCount);

        setStep();

        createTimer();

        loadNumbers();

        setDataToView();
    }
    private boolean isPaused = false;
    public void onPause(View view) {
        if(!isPaused){
            finishButton.setEnabled(false);
            btn_rest.setEnabled(false);
            pause_btn.setBackgroundResource(R.drawable.img_10);
            for (int i = 0; i < 16; i++) {
                buttons[i/4][i%4].setEnabled(false);
            }
            timer.cancel();

        }else{
            finishButton.setEnabled(true);
            btn_rest.setEnabled(true);
            pause_btn.setBackgroundResource(R.drawable.img_6);
            for (int i = 0; i < 16; i++) {
                buttons[i/4][i%4].setEnabled(true);
            }
            createTimer();
        }
        isPaused = !isPaused;
    }
    private void startMedia(){

    }
    @Override
    protected void onStop() {
        super.onStop();

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