package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class PopSetupWindow extends Activity {
    RadioGroup rgMinutes;
    RadioGroup rgMinTenth;
    RadioButton rbMunutes;
    RadioButton rbMinTenth;
    TextView timerText;
    int timerTime1;
    int timerTime2;
    String rb_1_result;
    String rb_2_result;
    Button timerOk;
    Button timerCancel;
    public int sumOfRadioButtons;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = 0; //Setup this value by click OK in popup timer

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popsetup_timer);
        rgMinutes=(RadioGroup) findViewById(R.id.radioGroup);
        rgMinTenth=(RadioGroup) findViewById(R.id.radioGroup2);
        timerOk=(Button) findViewById(R.id.button_timer_ok);
        timerCancel=(Button) findViewById(R.id.button_timer_cancel);
        //*************************
        //Set screen in %: (int)(width*.9) = 90%

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.9));

        //*************************

        timerOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sumOfRadioButtons!=0){
                    timeLeftInMillis=sumOfRadioButtons;
                    //startTimer();

                    Intent intent = new Intent(PopSetupWindow.this,PlayMusicOne.class);
                    intent.putExtra("timer time",sumOfRadioButtons);
                }
                //finish();
            }
        });
        timerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //RadioButton methods
    public int rbClick1(View view){
        int radioButtonId_1 = rgMinutes.getCheckedRadioButtonId();
        rbMunutes = (RadioButton) findViewById(radioButtonId_1);
        rb_1_result = rbMunutes.getText().toString();
        timerText = (TextView) findViewById(R.id.popup_text_time);

            //extract all digits from String
            String rb1_only_digits = rb_1_result.replaceAll("\\D+", "");
            timerTime1 = Integer.parseInt(rb1_only_digits);

        sumOfRadioButtons = timerTime1+timerTime2;
        timerText.setText(sumOfRadioButtons+" min");


        return timerTime1;
    }

    public int rbClick2(View view){
        int radioButtonId_2 = rgMinTenth.getCheckedRadioButtonId();
        rbMinTenth = (RadioButton) findViewById(radioButtonId_2);
        rb_2_result = rbMinTenth.getText().toString();
        timerText = (TextView) findViewById(R.id.popup_text_time);

            //extract all digits from String
            String rb2_only_digits = rb_2_result.replaceAll("\\D+", "");
            timerTime2 = Integer.parseInt(rb2_only_digits);

        sumOfRadioButtons = timerTime1+timerTime2;
        timerText.setText(sumOfRadioButtons+" min");

        return timerTime2;
    }


    //Timer


    private void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountDowntText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;

            }
        }.start();
        timerRunning = true;
    }

    private void updateCountDowntText(){
        int minutes = (int) (timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);

    }
}
