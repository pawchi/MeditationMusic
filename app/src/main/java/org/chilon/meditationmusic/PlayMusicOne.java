package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

public class PlayMusicOne extends Activity  {

    private Button stopButton;
    private Button timerButton;
    private Button gongButton;
    PerfectLoopMediaPlayer plmdx;
    MediaPlayer gong;
    private TextView mainTitle;
    TextView viewLeftTime;
    private static final int TIMER_CODE = 1;
    private static final int GONG_CODE = 2;
    private boolean timerRunning;
    private long timeLeftInMillis = 0; //Setup this value by click OK in popup timer
    private long gongTimeLeftInMillis = 0;
    private int gongTimeResponse = -1;
    ConstraintLayout background;
    int backgroudImage;
    int musicFileIntro;
    int musicFileMain;
    String mainTitleText;
    CountDownTimer countDownTimer2;
    Integer [] images = {R.drawable.background_play,R.drawable.monk_background,R.drawable.mountain_background,
                         R.drawable.background_play,R.drawable.monk_background,R.drawable.mountain_background};
    Integer [] musics_intro = {R.raw.kalimba_test_hq,R.raw.intro,R.raw.kalimba_test_hq,R.raw.intro,R.raw.kalimba_test_hq,R.raw.intro};
    Integer [] musics_main = {R.raw.kalimba,R.raw.birds,R.raw.pani_lansienka,R.raw.kalimba,R.raw.birds,R.raw.pani_lansienka};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play1);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //Response from MainActivity
        int extras = getIntent().getIntExtra(MainActivity.MAIN_RESPONSE,-1);
        MainActivity mainActivity = new MainActivity();
        mainTitleText = mainActivity.getMusicTypeItem(extras);
        backgroudImage = images[extras];
        musicFileIntro = musics_intro[extras];
        musicFileMain = musics_main[extras];


        background = (ConstraintLayout) findViewById(R.id.constraintid);
        background.setBackgroundResource(backgroudImage);

        mainTitle = (TextView) findViewById(R.id.play_music1_main_text_id);
        mainTitle.setText(mainTitleText);

        viewLeftTime = (TextView) findViewById(R.id.timer_left_id);
        viewLeftTime.setVisibility(View.INVISIBLE);

        timerButton = (Button) findViewById(R.id.timerid);

        stopButton = (Button) findViewById(R.id.stopid);
        stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);

        gongButton = (Button) findViewById(R.id.gong_button);

        plmdx = PerfectLoopMediaPlayer.create(PlayMusicOne.this, musicFileIntro);
        plmdx.prepare();
        //plmdx.start();

        gong = MediaPlayer.create(PlayMusicOne.this,R.raw.pani_lansienka);

        //Button Play/Pause
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plmdx.isPlaying()) {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_play);
                    plmdx.pause();
                } else {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
                    plmdx.start();
                }
            }
        });

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),PopUpWindowTimer.class);
                startActivityForResult(intent,1);

            }
        });

        gongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Popsetup_gong.class);
                startActivityForResult(intent,2);
            }
        });
    }

    //Response from SetUpTimer and SetUpGongTime
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //result Timer
        if(TIMER_CODE==requestCode){
            int popSetupResponse = data.getIntExtra(PopUpWindowTimer.RESPONSE,-1);
            if(popSetupResponse!=9999)
            insertTimerResponse(popSetupResponse);
        }

        //result Gong
        if(GONG_CODE==requestCode){
            gongTimeResponse = data.getIntExtra("key",-1);
            if(gongTimeResponse==61||gongTimeResponse==0){
                insertGongResponse(61);
            } else {
                insertGongResponse(gongTimeResponse);
            }
        }
    }

    private void insertTimerResponse(int response){
        if(response!=0) {
            //In minutes should be: timeLeftInMillis = response*60*1000;
            timeLeftInMillis = response*6*1000;
            startTimer();
             plmdx.start();
             stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
            gongButton.setVisibility(View.VISIBLE);

        }
    }

    //Stop MediaPlayer by changing activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        plmdx.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mdx.stop();
    }

    //Timer
    private void startTimer(){
         new CountDownTimer(timeLeftInMillis,1000) {

            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountDowntText();
                timerButton.setBackgroundResource(android.R.drawable.toast_frame);
            }

            public void onFinish() {
                try {
                    plmdx.stop();
                    plmdx.prepare();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                timerRunning = false;
                stopButton.setBackgroundResource(android.R.drawable.ic_media_play);
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

    private void insertGongResponse(int gongResponse){
        if(gongResponse!=61){
            String gongTime = Integer.toString(gongResponse);
            gongButton.setText(gongTime);
            gongTimeLeftInMillis = timeLeftInMillis - gongResponse*1000;
            startGongTime();
        } else {
            gongButton.setText("");
        }
    }

    //Gong timer
    private void startGongTime(){

        countDownTimer2 = new CountDownTimer(gongTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                gongTimeLeftInMillis=millisUntilFinished;
            }

            @Override
            public void onFinish() {
                try {
                    gongButton.setText("fin");
                    gong.start();
                    gongButton.setVisibility(View.INVISIBLE);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
