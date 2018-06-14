package org.chilon.meditationmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class Play_music extends Activity {

    Button startButton;
    Button stopButton;
    MediaPlayer mdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        startButton = (Button) findViewById(R.id.playid);
        stopButton = (Button) findViewById(R.id.stopid);
        mdx = MediaPlayer.create(Play_music.this,R.raw.pani_lansienka);
        mdx.start();

        //final MediaPlayer paniL = MediaPlayer.create(this,R.raw.pani_lansienka);
        //Button button = (Button) findViewById(R.id.playid);



        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdx.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdx.stop();
                mdx = MediaPlayer.create(Play_music.this,R.raw.pani_lansienka);
            }
        });
    }

}
