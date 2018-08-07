package org.chilon.meditationmusic;

import android.app.Activity;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class AddMusic extends Activity {
    PerfectLoopMediaPlayer music_1;
    SeekBar mainSoundVolume;
    SeekBar systemVolume;
    ImageView firstSoundToAddImage;
    ImageView secondSoundToAddImage;
    LinearLayout firstSoundToAddLayout;
    LinearLayout existingLayoutForInserts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);
        this.setFinishOnTouchOutside(false);

        firstSoundToAddImage = (ImageView) findViewById(R.id.sound_1_to_add_imageview);

        firstSoundToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout.LayoutParams newLayoutParams =  new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);
                LinearLayout newLinearLayoutForFirstSound = new LinearLayout(AddMusic.this);
                existingLayoutForInserts.addView(newLinearLayoutForFirstSound,newLayoutParams);
                newLinearLayoutForFirstSound.setGravity(Gravity.CENTER_HORIZONTAL);
                newLinearLayoutForFirstSound.setOrientation(LinearLayout.HORIZONTAL);


                ImageView insertSoundImage = new ImageView(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertSoundImage,newLayoutParams);
                insertSoundImage.setBackgroundResource(android.R.drawable.btn_dialog);

                SeekBar insertSeekBar = new SeekBar(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertSeekBar);

                ImageView insertCancelImage = new ImageView(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertCancelImage);
                insertCancelImage.setBackgroundResource(android.R.drawable.btn_dialog);
            }
        });

        secondSoundToAddImage = (ImageView) findViewById(R.id.sound_2_to_add_imageview);

        secondSoundToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams newLayoutParams =  new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);
                LinearLayout newLinearLayoutForFirstSound = new LinearLayout(AddMusic.this);
                existingLayoutForInserts.addView(newLinearLayoutForFirstSound,newLayoutParams);
                newLinearLayoutForFirstSound.setGravity(Gravity.CENTER_HORIZONTAL);
                newLinearLayoutForFirstSound.setOrientation(LinearLayout.HORIZONTAL);


                ImageView insertSoundImage = new ImageView(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertSoundImage,newLayoutParams);
                insertSoundImage.setBackgroundResource(android.R.drawable.btn_dialog);

                SeekBar insertSeekBar = new SeekBar(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertSeekBar);

                ImageView insertCancelImage = new ImageView(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertCancelImage);
                insertCancelImage.setBackgroundResource(android.R.drawable.btn_dialog);

            }
        });


    }
}
