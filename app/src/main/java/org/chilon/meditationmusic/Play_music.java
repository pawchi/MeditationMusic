package org.chilon.meditationmusic;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class Play_music extends Activity  {

    Button stopButton;
    MediaPlayer mdx;
    SeekBar skb;
    SeekBar skvolume;
    AudioManager audioManager;
    //Handler handler;
    Runnable runnable;
    boolean isPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintid);
        constraintLayout.setBackgroundResource(R.drawable.background_play);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        skb=(SeekBar) findViewById(R.id.seekbar);
        //handler=new Handler();


        stopButton = (Button) findViewById(R.id.stopid);
        stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
        mdx = MediaPlayer.create(Play_music.this,R.raw.pani_lansienka);
        mdx.start();
        mdx.setLooping(true);

        /*SeekBar Volume
        skvolume = (SeekBar) findViewById(R.id.seekVolume);
        int maxV = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //po dodaniu tego cały widok przestaje być wodoczny
        int curV = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        skvolume.setMax(maxV);
        skvolume.setProgress(curV);

        skvolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

        //Button
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlay) {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_play);
                    mdx.pause();
                    mdx = MediaPlayer.create(Play_music.this, R.raw.pani_lansienka);
                    isPlay=false;
                } else {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
                    mdx.start();
                    isPlay=true;
                }
            }
        });

        /*//SeekBar
        mdx.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                skb.setMax(mdx.getDuration());
                playCycle();
                mdx.start();
            }
        });

        skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if(input){
                    mdx.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/
    }
    /*
    public void playCycle(){
        skb.setProgress(mdx.getCurrentPosition());

        if(mdx.isPlaying()){
            runnable=new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        mdx.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mdx.pause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mdx.release();
        handler.removeCallbacks(runnable);
    }*/
}
