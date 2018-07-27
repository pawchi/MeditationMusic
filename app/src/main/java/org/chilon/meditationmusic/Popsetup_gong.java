package org.chilon.meditationmusic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Popsetup_gong extends Activity {
    int gongTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popsetup_gong);
    }

    public int rbGongOnClick(View view){

        return gongTime;
    }
}
