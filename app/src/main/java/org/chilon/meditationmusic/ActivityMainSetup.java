package org.chilon.meditationmusic;

import android.app.Activity;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityMainSetup extends Activity {

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setup);

        constraintLayout = (ConstraintLayout) findViewById(R.id.main_setup_constr_layout);
        constraintLayout.setBackgroundColor(Color.parseColor("#b4eeb4"));


    }
}
