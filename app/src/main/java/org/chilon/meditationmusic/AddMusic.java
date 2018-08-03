package org.chilon.meditationmusic;

import android.app.Activity;
import android.os.Bundle;

public class AddMusic extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);
        this.setFinishOnTouchOutside(false);


    }
}
