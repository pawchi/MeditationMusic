package org.chilon.meditationmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayMusicOne extends Activity  {

    private Button stopButton;
    private MediaPlayer mdx;
    boolean isPlay = true;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play1);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        MainActivity mainActivity = new MainActivity();
        textView = (TextView) findViewById(R.id.play_music1_main_text_id);
        textView.setText(mainActivity.getMusicTypeItem(0));

        stopButton = (Button) findViewById(R.id.stopid);
        stopButton.setBackgroundResource(android.R.drawable.ic_media_pause);
        mdx = MediaPlayer.create(PlayMusicOne.this, R.raw.pani_lansienka);
        mdx.start();
        mdx.setLooping(true);

        //Button
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
    }
}
