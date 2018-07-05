package org.chilon.meditationmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayMusicOne extends Activity  {

    private Button stopButton;
    private Button timerButton;
    private MediaPlayer mdx;
    boolean isPlay = true;
    private TextView textView;
    TextView viewLeftTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play1);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        viewLeftTime = (TextView) findViewById(R.id.timer_left_id);

        MainActivity mainActivity = new MainActivity();
        textView = (TextView) findViewById(R.id.play_music1_main_text_id);
        textView.setText(mainActivity.getMusicTypeItem(0));

        timerButton = (Button) findViewById(R.id.timerid);

        stopButton = (Button) findViewById(R.id.stopid);
        stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
        mdx = MediaPlayer.create(PlayMusicOne.this, R.raw.pani_lansienka);
        mdx.start();
        mdx.setLooping(true);

        //Button Play/Pause
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay) {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_play);
                    mdx.pause();
                    mdx = MediaPlayer.create(PlayMusicOne.this, R.raw.pani_lansienka);
                    isPlay = false;
                } else {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
                    mdx.start();
                    isPlay = true;
                }
            }
        });

        //Button Timer

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayMusicOne.this,PopSetupWindow.class));

            }
        });
    }

/*
    @Override
    protected void onResume(){
        super.onResume();

        int timerTime = getIntent().getIntExtra("timer time",0);
        //String timer = getIntent().getStringExtra("timer time");
        viewLeftTime.setText(timerTime);
    }*/
/*
    @Override
    protected void onStart(){
        super.onStart();
        int timerTime = getIntent().getIntExtra("timer time",0);
        //String timer = getIntent().getStringExtra("timer time");
        viewLeftTime.setText(timerTime);
    }*/
}
