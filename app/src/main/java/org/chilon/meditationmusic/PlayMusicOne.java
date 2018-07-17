package org.chilon.meditationmusic;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class PlayMusicOne extends Activity  {

    private Button stopButton;
    private Button timerButton;
    private MediaPlayer mdx;
    private TextView mainTitle;
    TextView viewLeftTime;
    private static final int POP_SETUP_WINDOW_CODE = 1;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = 0; //Setup this value by click OK in popup timer
    ConstraintLayout background;
    int backgroudImage;
    int musicFileIntro;
    int musicFileMain;
    String mainTitleText;
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play1);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //Response from MainActivity
        int extras = getIntent().getIntExtra(MainActivity.MAIN_RESPONSE,-1);
        if(extras==0){
            resources = getResources();
            backgroudImage = resources.getIdentifier("background_play","drawable",this.getPackageName());
            musicFileIntro = resources.getIdentifier("water","raw",this.getPackageName());
            musicFileMain = resources.getIdentifier("water","raw",this.getPackageName());

            MainActivity mainActivity = new MainActivity();
            mainTitleText = mainActivity.getMusicTypeItem(extras);
        }

        if(extras==1){
            resources = getResources();
            backgroudImage = resources.getIdentifier("monk_background","drawable",this.getPackageName());
            musicFileIntro = resources.getIdentifier("intro","raw",this.getPackageName());
            musicFileMain = resources.getIdentifier("pani_lansienka","raw",this.getPackageName());

            MainActivity mainActivity = new MainActivity();
            mainTitleText = mainActivity.getMusicTypeItem(extras);
        }

        if(extras==2){
            resources = getResources();
            backgroudImage = resources.getIdentifier("monk_background","drawable",this.getPackageName());
            musicFileIntro = resources.getIdentifier("intro","raw",this.getPackageName());
            musicFileMain = resources.getIdentifier("pani_lansienka","raw",this.getPackageName());

            MainActivity mainActivity = new MainActivity();
            mainTitleText = mainActivity.getMusicTypeItem(extras);
        }

        if(extras==3){
            resources = getResources();
            backgroudImage = resources.getIdentifier("mountain_background","drawable",this.getPackageName());
            musicFileIntro = resources.getIdentifier("water","raw",this.getPackageName());
            musicFileMain = resources.getIdentifier("pani_lansienka","raw",this.getPackageName());

            MainActivity mainActivity = new MainActivity();
            mainTitleText = mainActivity.getMusicTypeItem(extras);
        }
        if(extras==4){
            resources = getResources();
            backgroudImage = resources.getIdentifier("mountain_background","drawable",this.getPackageName());
            musicFileIntro = resources.getIdentifier("water","raw",this.getPackageName());
            musicFileMain = resources.getIdentifier("pani_lansienka","raw",this.getPackageName());

            MainActivity mainActivity = new MainActivity();
            mainTitleText = mainActivity.getMusicTypeItem(extras);
        }
        if(extras==5){
            resources = getResources();
            backgroudImage = resources.getIdentifier("mountain_background","drawable",this.getPackageName());
            musicFileIntro = resources.getIdentifier("water","raw",this.getPackageName());
            musicFileMain = resources.getIdentifier("pani_lansienka","raw",this.getPackageName());

            MainActivity mainActivity = new MainActivity();
            mainTitleText = mainActivity.getMusicTypeItem(extras);
        }
        if(extras==6){
            resources = getResources();
            backgroudImage = resources.getIdentifier("mountain_background","drawable",this.getPackageName());
            musicFileIntro = resources.getIdentifier("water","raw",this.getPackageName());
            musicFileMain = resources.getIdentifier("pani_lansienka","raw",this.getPackageName());

            MainActivity mainActivity = new MainActivity();
            mainTitleText = mainActivity.getMusicTypeItem(extras);
        }



        background = (ConstraintLayout) findViewById(R.id.constraintid);
        background.setBackgroundResource(backgroudImage);

        mainTitle = (TextView) findViewById(R.id.play_music1_main_text_id);
        mainTitle.setText(mainTitleText);

        viewLeftTime = (TextView) findViewById(R.id.timer_left_id);
        viewLeftTime.setVisibility(View.INVISIBLE);

        timerButton = (Button) findViewById(R.id.timerid);

        stopButton = (Button) findViewById(R.id.stopid);
        stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);

        //play intro
        mdx= MediaPlayer.create(PlayMusicOne.this, musicFileIntro);
        mdx.start();
        //play when intro finished
        mdx.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mdx = MediaPlayer.create(PlayMusicOne.this, musicFileMain);
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
                startActivityForResult(intent,1);

            }
        });
    }

    //Response from PopSetupWindow and MainActivity
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
