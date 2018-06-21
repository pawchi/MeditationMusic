package org.chilon.meditationmusic;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class PlayMusicTwo extends Activity {

    Button stopButton;
    MediaPlayer mdx;
    SeekBar skb;
    boolean isPlay = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintid);
        constraintLayout.setBackgroundResource(R.drawable.monk_background);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        skb = (SeekBar) findViewById(R.id.seekbar);


        stopButton = (Button) findViewById(R.id.stopid);
        stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
        mdx = MediaPlayer.create(PlayMusicTwo.this, R.raw.violin_guitar);
        mdx.start();
        mdx.setLooping(true);

        //Button
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay) {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_play);
                    mdx.pause();
                    mdx = MediaPlayer.create(PlayMusicTwo.this, R.raw.violin_guitar);
                    isPlay = false;
                } else {
                    stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
                    mdx.start();
                    isPlay = true;
                }
            }
        });
    }
}
