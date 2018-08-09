package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.util.Size;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.zip.Inflater;

public class AddMusic extends Activity {
    PerfectLoopMediaPlayer music_1;
    SeekBar mainSoundVolume;
    SeekBar systemVolume;
    ImageView firstSoundToAddImage;
    ImageView secondSoundToAddImage;
    ImageView thirdSoundToAddImage;
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


                LinearLayout newLinearLayoutForFirstSound = new LinearLayout(AddMusic.this);
                newLinearLayoutForFirstSound.setOrientation(LinearLayout.HORIZONTAL);

                //newLinearLayoutForFirstSound.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

                LinearLayout.LayoutParams newLayoutParams =  new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                //newLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                //newLayoutParams.gravity = Gravity.CENTER_VERTICAL;
                //newLayoutParams.topMargin = 25;
                newLayoutParams.setMargins(0,25,0,0);
                newLinearLayoutForFirstSound.setWeightSum(10);

                existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);
                existingLayoutForInserts.addView(newLinearLayoutForFirstSound,newLayoutParams);

                //****************************
                LinearLayout.LayoutParams newImageLeft = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                newImageLeft.width = 20;
                newImageLeft.height = 20;
                newImageLeft.gravity = Gravity.CENTER_VERTICAL;
                newImageLeft.setMarginStart(25);
                newImageLeft.setMarginEnd(10);
                newImageLeft.weight = 2;

                ImageView insertSoundImage = new ImageView(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertSoundImage,newImageLeft);
                insertSoundImage.setBackgroundResource(android.R.drawable.btn_dialog);

                //*****************************
                LinearLayout.LayoutParams seekBar = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                seekBar.topMargin = 10;
                seekBar.bottomMargin = 10;
                seekBar.weight = 6;


                SeekBar insertSeekBar = new SeekBar(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertSeekBar,seekBar);
                //insertSeekBar.setThumb(getResources().getDrawable(R.drawable.custom_thumb));

                //*****************************
                LinearLayout.LayoutParams newImageRight = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                newImageLeft.width = 80;
                newImageLeft.height = 80;
                newImageLeft.gravity = Gravity.CENTER_VERTICAL;
                newImageLeft.setMarginStart(10);
                newImageLeft.setMarginEnd(25);
                newImageLeft.weight = 2;

                ImageView insertCancelImage = new ImageView(AddMusic.this);
                newLinearLayoutForFirstSound.addView(insertCancelImage,newImageRight);
                insertCancelImage.setBackgroundResource(android.R.drawable.btn_dialog);
            }
        });

        secondSoundToAddImage = (ImageView) findViewById(R.id.sound_2_to_add_imageview);

        secondSoundToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout newLinearLayoutForFirstSound = new LinearLayout(AddMusic.this);
                newLinearLayoutForFirstSound.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams newLayoutParams =  new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                //newLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                //newLayoutParams.gravity = Gravity.CENTER_VERTICAL;  nie działa
                //newLayoutParams.topMargin = 25;
                //newLayoutParams.setMargins(0,25,0,0);
                newLinearLayoutForFirstSound.setWeightSum(10);
                newLinearLayoutForFirstSound.setGravity(Gravity.CENTER_VERTICAL);

                existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);
                existingLayoutForInserts.addView(newLinearLayoutForFirstSound);

                //****************************
                ImageView insertImage_1 = new ImageView(AddMusic.this);
                LinearLayout.LayoutParams image_1_Params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                //image_1_Params.width = 40;
                //image_1_Params.height = 40;

                image_1_Params.setMarginStart(25);
                image_1_Params.setMarginEnd(10);
                //image_1_Params.weight = 2;

                newLinearLayoutForFirstSound.addView(insertImage_1,image_1_Params);
                insertImage_1.setBackgroundResource(android.R.drawable.btn_dialog);

                //*****************************
                SeekBar insertSeekBar = new SeekBar(AddMusic.this);
                /*
                LinearLayout.LayoutParams seekBar = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                seekBar.topMargin = 10;
                seekBar.bottomMargin = 10;
                //seekBar.weight = 6;
                seekBar.gravity = Gravity.CENTER_VERTICAL;
                */

                newLinearLayoutForFirstSound.addView(insertSeekBar);
                //insertSeekBar.setThumb(getResources().getDrawable(R.drawable.custom_thumb));

                //*****************************
                ImageView insertImage_2 = new ImageView(AddMusic.this);

                LinearLayout.LayoutParams image_2_Params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                image_2_Params.gravity = Gravity.CENTER_VERTICAL;
                image_2_Params.setMarginStart(10);
                image_2_Params.setMarginEnd(25);
                //image_2_Params.weight = 2;


                newLinearLayoutForFirstSound.addView(insertImage_2,image_2_Params);
                insertImage_2.setBackgroundResource(android.R.drawable.btn_dialog);

            }
        });

        //Add LinearLayout from XML - na razie nie działa
        thirdSoundToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);
                View view = inflater.inflate(R.layout.linearlayout_add_sound,existingLayoutForInserts,false);
                existingLayoutForInserts.addView(view);
            }
        });
    }
}
