package com.rasulovabdullokh.puzzlegame.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rasulovabdullokh.puzzlegame.R;
import com.rasulovabdullokh.puzzlegame.core.cache.MemoryHelper;
import com.rasulovabdullokh.puzzlegame.core.cache.UserData;

public class ResultSaveActivity extends BaseActivity {
    int time,step;
    private EditText editText;
    String name;
    private TextView timeView;

    private TextView stepView;
    private ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_save);
        layout=findViewById(R.id.content);

        Intent intent = getIntent();
        int step = intent.getIntExtra("time",0);
        int  time = intent.getIntExtra("step",0);

        editText=findViewById(R.id.name_input);


        timeView=findViewById(R.id.timer_view);

        stepView=findViewById(R.id.step_view);

//        UserData userData = MemoryHelper.getHelper().getData(0);
//
//        timeView.setText("Time: "+userData.getTime());
//        stepView.setText("Step: "+userData.getStep());
//        editText.setText(userData.getName());

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=editText.getText().toString();
                if(name.length()==0){
                    Toast.makeText(ResultSaveActivity.this, "Name is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    MemoryHelper.getHelper().saveData(new UserData(
                            name,
                            step,
                            time
//                        1:00
                    ));
                    Toast.makeText(ResultSaveActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }

                
            }
        });
        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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