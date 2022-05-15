package com.rasulovabdullokh.puzzlegame.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rasulovabdullokh.puzzlegame.R;
import com.rasulovabdullokh.puzzlegame.core.cache.MemoryHelper;
import com.rasulovabdullokh.puzzlegame.core.cache.UserData;

import java.util.ArrayList;

public class ResultActivity extends BaseActivity {

    private LinearLayout rootGroup;
    private TextView resultName,resultStep,resultTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//        resultName =findViewById(R.id.result_name);
//        resultStep=findViewById(R.id.result_step);
//        resultTime=findViewById(R.id.result_time);
        rootGroup = findViewById(R.id.content);
        ArrayList<UserData> list = MemoryHelper.getHelper().getLastResults();


        for (UserData data:list) {
//            resultName.setText("Name:"+data.getName());
//            resultTime.setText("Time: "+getTime(data.getTime()));
//            resultStep.setText("Steps: "+data.getStep());
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(

                    LinearLayout.LayoutParams.MATCH_PARENT,

                    LinearLayout.LayoutParams.WRAP_CONTENT);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<b>\tName:</b> ");
            stringBuilder.append(data.getName());
            stringBuilder.append("<br>");
            stringBuilder.append("<b> \t⌛️Time:</b>\t");
            stringBuilder.append(getTime(data.getTime()));
            stringBuilder.append("\t<b>\tStep:</b>\t");
            stringBuilder.append(data.getStep());
            textView.setText(HtmlCompat.fromHtml(stringBuilder.toString(),HtmlCompat.FROM_HTML_MODE_LEGACY));
            textView.setPadding(5,5,5,5);
            textView.setLayoutParams(params);
            textView.setBackgroundResource(R.drawable.save_back1);

            int size=getResources().getDimensionPixelSize(R.dimen.textSize);
            textView.setTextSize(size);
            textView.setTextColor(Color.parseColor("#FFFFFF"));

            rootGroup.addView(textView);
        }

        }
//        for (int i = 1; i < 30; i++) {
//            TextView textView = new TextView(this);
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//            textView.setText("Button "+i);
//
//            textView.setLayoutParams(params);
//
//            rootGroup.addView(textView);
//        }


    private String getTime(int n){
        int hour = n/3600;
        int minute = n%3600/60;
        int second = n % 60;
        return String.format("%02d:%02d.%02d", hour, minute, second);

    }

    @Override
    public boolean shouldStopMedia() {
        return false;
    }

    @Override
    public void setThemeData() {
        rootGroup.setBackgroundResource(MemoryHelper.getHelper().getThemeState());
    }
}