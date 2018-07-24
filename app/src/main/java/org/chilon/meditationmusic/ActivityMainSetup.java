package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityMainSetup extends Activity {

    ConstraintLayout constraintLayout;
    TextView title;
    TextView rateUs;
    TextView changeLanguage;
    LinearLayout linLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setup);

        linLay = (LinearLayout) findViewById(R.id.main_setup_lin_layout);
        linLay.setAlpha((float) 0.5);

        rateUs = (TextView) findViewById(R.id.rate_us_text);

        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=appinventor.ai_pawchism.Rubic_Cube")));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=appinventor.ai_pawchism.Rubic_Cube")));
                }
            }
        });

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
