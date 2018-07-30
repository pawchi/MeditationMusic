package org.chilon.meditationmusic;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

public class Popsetup_gong extends Activity {
    int gongTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popsetup_gong);

        //Set screen in %: (int)(width*.9) = 90%

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*.7));
    }

    public int rbGongOnClick(View view){

        return gongTime;
    }
}
