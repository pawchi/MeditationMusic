package org.chilon.meditationmusic;

import android.app.Activity;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityMainSetup extends Activity {

    ConstraintLayout constraintLayout;
    TextView title;
    TextView scoreUs;
    TextView changeLanguage;
    LinearLayout linLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setup);

        linLay = (LinearLayout) findViewById(R.id.main_setup_lin_layout);
        linLay.setAlpha((float) 0.5);

        /*
        title = (TextView) findViewById(R.id.title_main_setup);
        title.setAlpha((float) 0.5);
        //title.setBackgroundColor(test.alpha(Color.parseColor("#c1cdcd")));

        scoreUs = (TextView) findViewById(R.id.score_us);
        scoreUs.setAlpha((float) 0.5);

        changeLanguage = (TextView) findViewById(R.id.change_language);
        changeLanguage.setAlpha((float) 0.5);
        */
    }
}
