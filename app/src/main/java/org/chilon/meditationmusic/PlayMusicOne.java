package org.chilon.meditationmusic;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class PlayMusicOne extends Activity  {

    private Button stopButton;
    private Button timerButton;
    private MediaPlayer mdx;
    private TextView textView;
    TextView viewLeftTime;
    private static final int POP_SETUP_WINDOW_CODE = 111;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = 0; //Setup this value by click OK in popup timer


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play1);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        viewLeftTime = (TextView) findViewById(R.id.timer_left_id);
        viewLeftTime.setVisibility(View.INVISIBLE);

        MainActivity mainActivity = new MainActivity();
        textView = (TextView) findViewById(R.id.play_music1_main_text_id);
        textView.setText(mainActivity.getMusicTypeItem(0));

        timerButton = (Button) findViewById(R.id.timerid);

        stopButton = (Button) findViewById(R.id.stopid);
        stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);


        mdx= MediaPlayer.create(PlayMusicOne.this,R.raw.intro);
        mdx.start();
        mdx.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mdx = MediaPlayer.create(PlayMusicOne.this, R.raw.pani_lansienka);
                mdx.start();
                mdx.setLooping(true);
            }
        });


        //Button Play/Pause
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mdx.isPlaying()) {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_play);
                    mdx.pause();
                } else {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
                    mdx.start();
                }
            }
        });

        //Button Timer
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),PopSetupWindow.class);
                startActivityForResult(intent,POP_SETUP_WINDOW_CODE);

            }
        });
    }

    //Response from PopSetupWindow
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(POP_SETUP_WINDOW_CODE==requestCode){
            int popSetupResponse = data.getIntExtra(PopSetupWindow.RESPONSE,-1);
            insertResponse(popSetupResponse);
        }
    }

    private void insertResponse(int response){
        if(response!=0) {
            timeLeftInMillis = response*60*1000;
            startTimer();
            mdx.start();
            //String res = String.valueOf(response);
            //viewLeftTime.setText(res);
        }
    }

    //Stop MediaPlayer by changing activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mdx.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mdx.stop();
    }

    //Timer
    private void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountDowntText();
                timerButton.setBackgroundResource(android.R.drawable.toast_frame);
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                mdx.stop();
                timerButton.setBackgroundResource(android.R.drawable.ic_menu_recent_history);
                timerButton.setText("");

            }
        }.start();
        timerRunning = true;
        //timerButton.setText();
    }

    private void updateCountDowntText(){
        int minutes = (int) (timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
        timerButton.setText(timeLeftFormatted);
        //timerButton.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
    }
}
