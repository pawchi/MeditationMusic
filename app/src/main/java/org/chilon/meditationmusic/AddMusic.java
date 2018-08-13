package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class AddMusic extends Activity {
    PerfectLoopMediaPlayer music_1;
    SeekBar mainSoundVolume;
    SeekBar systemVolume;
    ImageView firstSoundToAddImage;
    ImageView secondSoundToAddImage;
    ImageView thirdSoundToAddImage;
    LinearLayout firstSoundToAddLayout;
    LinearLayout existingLayoutForInserts;
    PerfectLoopMediaPlayer soundOne;
    PerfectLoopMediaPlayer soundTwo;
    ImageView cancelSoundOne;
    View sound_1;
    boolean view_1_inflated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);
        this.setFinishOnTouchOutside(false);

        existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);

        firstSoundToAddImage = (ImageView) findViewById(R.id.sound_1_to_add_imageview);
        firstSoundToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater rowInLinearLayoutToInflate = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);

                sound_1 = rowInLinearLayoutToInflate.inflate(R.layout.linearlayout_add_sound,existingLayoutForInserts,false);
                existingLayoutForInserts.addView(sound_1);

                soundTwo = PerfectLoopMediaPlayer.create(AddMusic.this,R.raw.water);
                view_1_inflated=true;
            }
        });


            cancelSoundOne = (ImageView) findViewById(R.id.music_1_cancel__from_xml);
            if(cancelSoundOne!=null) {

                cancelSoundOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        soundTwo.pause();
                        existingLayoutForInserts.removeView(sound_1);
                    }
                });
            }





        secondSoundToAddImage = (ImageView) findViewById(R.id.sound_2_to_add_imageview);

        secondSoundToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //Add LinearLayout from XML
        thirdSoundToAddImage = (ImageView) findViewById(R.id.sound_3_to_add_imageview);

        thirdSoundToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater rowInLinearLayoutToInflate = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);

                View view = rowInLinearLayoutToInflate.inflate(R.layout.linearlayout_add_sound,existingLayoutForInserts,false);
                existingLayoutForInserts.addView(view);

                soundOne = PerfectLoopMediaPlayer.create(AddMusic.this,R.raw.korg);
                soundOne.prepare();



            }
        });
    }
}
