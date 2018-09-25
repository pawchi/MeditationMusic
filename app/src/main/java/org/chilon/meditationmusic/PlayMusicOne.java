package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class PlayMusicOne extends Activity  {

    private Button stopButton;
    private Button timerButton;
    private Button gongButton;
    private Button addSoundButton;
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
    private SeekBar volumeSeekbar=null;
    private AudioManager audioManager=null;
    ConstraintLayout background;
    int backgroudImage;
    int musicFileIntro;
    int musicFileMain;
    String mainTitleText;
    CountDownTimer countDownTimer2;
    Integer [] images = {R.drawable.energia_poranka_bg,R.drawable.gleboki_spokoj_bg,R.drawable.oczyszczajaca_wibracja_bg,
                         R.drawable.piekno_natury_bg,R.drawable.prosto_z_serca_bg,R.drawable.spokojna_noc_bg,R.drawable.wszechobecna_harmonia_bg,
                            R.drawable.bezruch_pustyni_bg,R.drawable.balsam_na_dusze,R.drawable.delikatny_trans,R.drawable.boski_glos_bg,R.drawable.melodia_nocy_bg};
    Integer [] musics_intro = {R.raw.birds,R.raw.water,R.raw.korg,R.raw.intro,R.raw.kalimba_test_hq,R.raw.waves,R.raw.birds,R.raw.water,R.raw.korg,R.raw.intro,R.raw.kalimba_test_hq,R.raw.waves};
    Integer [] musics_main = {R.raw.kalimba,R.raw.birds,R.raw.pani_lansienka,R.raw.kalimba,R.raw.birds,R.raw.pani_lansienka,R.raw.kalimba,R.raw.birds,R.raw.pani_lansienka,R.raw.kalimba,R.raw.birds,R.raw.pani_lansienka};



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

        //setVolumeControlStream(AudioManager.STREAM_MUSIC);
        volumeControlSeekbar();

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
        gongButton.setBackgroundResource(R.drawable.gong);
        addSoundButton = (Button) findViewById(R.id.add_sound_button);

        plmdx = PerfectLoopMediaPlayer.create(PlayMusicOne.this, musicFileIntro);
        plmdx.prepare();
        //plmdx.start();

        gong = MediaPlayer.create(PlayMusicOne.this,R.raw.gong);

        //Button Play/Pause
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plmdx.isPlaying()) {
                    stopButton.setBackgroundResource(R.drawable.play_icon);
                    plmdx.pause();
                } else {
                    stopButton.setBackgroundResource(R.drawable.pause);
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

        addSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddMusic.class);
                startActivityForResult(intent,3);
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
                    plmdx.pause();
                    //plmdx.stop();
                    //plmdx.prepare();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                timerRunning = false;
                stopButton.setBackgroundResource(android.R.drawable.ic_media_play);
                timerButton.setBackgroundResource(android.R.drawable.ic_menu_recent_history);
                timerButton.setText("");
                gongButton.setVisibility(View.INVISIBLE);

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
                    gong.start();
                    gongButton.setVisibility(View.INVISIBLE);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void volumeControlSeekbar(){
        try {
            volumeSeekbar = (SeekBar) findViewById(R.id.seekVolume);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_SAME,AudioManager.FLAG_SHOW_UI);
            volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_PLAY_SOUND);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_SHOW_UI);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshVolumeSeekbarPositionWhenSystemVolumeChanges(){

    }
}
