package org.chilon.meditationmusic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

public class AddMusic extends Activity {
    PerfectLoopMediaPlayer music_1;
    SeekBar seekBar_1;
    SeekBar seekBar_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);
        this.setFinishOnTouchOutside(false);



    }
}
