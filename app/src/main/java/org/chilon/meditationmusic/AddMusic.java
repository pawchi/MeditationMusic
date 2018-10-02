package org.chilon.meditationmusic;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import org.chilon.meditationmusic.content_provider.OnVolumeChangedListener;
import org.chilon.meditationmusic.content_provider.VolumeSettingsContentObserver;

public class AddMusic extends Activity {

    LinearLayout existingLayoutForInserts;
    PerfectLoopMediaPlayer soundOne;
    PerfectLoopMediaPlayer soundTwo;
    SeekBar volumeSeekbar;
    final int soundOneId = R.id.sound_1_to_add_imageview;
    final int soundTwoId = R.id.sound_2_to_add_imageview;
    Integer tagOne = 1111;
    Integer tagTwo = 2222;
    ImageView cancelSoundOne;
    ImageView cancelSoundTwo;
    View sound_1;
    boolean view_1_inflated = false;
    Integer tempIdCancelViewOne;
    Integer tempIdCancelViewTwo;
    int layoutId = R.layout.activity_add_music;
    VolumeSettingsContentObserver volumeSettingsContentObserver;
    OnVolumeChangedListener listener = new OnVolumeChangedListener() {
        @Override
        public void onVolumeChanged(int currentVolume) {
            volumeSeekbar.setProgress(currentVolume);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null){
            layoutId = savedInstanceState.getInt("storedLayoutId",R.layout.activity_add_music);
            setContentView(layoutId);
        } else {
            setContentView(R.layout.activity_add_music);
        }


        volumeSeekbar = findViewById(R.id.gen_vol_seekBar);
        volumeSeekbar.setMin(0);
        volumeSeekbar.setMax(15);
        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                volumeSettingsContentObserver.setVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.setFinishOnTouchOutside(false);
        existingLayoutForInserts = (LinearLayout) findViewById(R.id.linear_layout_for_inserts);

    }

    @Override
    protected void onStart() {
        super.onStart();
        volumeSettingsContentObserver = new VolumeSettingsContentObserver(this, new Handler(Looper.getMainLooper()), listener);
        volumeSettingsContentObserver.register(this);
        volumeSeekbar.setProgress(volumeSettingsContentObserver.getVolume());
    }

    @Override
    protected void onStop() {
        volumeSettingsContentObserver.unregister(this);
        super.onStop();
    }

    public void onDelete(View view){


        if (view.getId()==cancelSoundOne.getId()){
            soundOne.pause();
        }
/*
        if(view.getId()==cancelSoundTwo.getId()){
            soundTwo.pause();
        }
*/
        existingLayoutForInserts.removeView((View) view.getParent());
    }
    public void addSound(View view){
        int tempId = view.getId();
        LayoutInflater rowInLinearLayoutToInflate = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sound_1 = rowInLinearLayoutToInflate.inflate(R.layout.linearlayout_add_sound,existingLayoutForInserts,false);
        existingLayoutForInserts.addView(sound_1);


        cancelSoundOne = (ImageView) findViewById(R.id.music_1_cancel__from_xml);

        switch (tempId){
            case soundOneId:
                tempIdCancelViewOne = View.generateViewId();
                cancelSoundOne.setId(tempIdCancelViewOne);
                soundOne = PerfectLoopMediaPlayer.create(AddMusic.this,R.raw.water);
                soundOne.prepare();

                break;
/*
            case soundTwoId:
                //cancelSoundTwo = (ImageView) findViewById(R.id.music_1_cancel__from_xml);
                //cancelSoundTwo.setId(View.generateViewId());
                tempIdCancelViewTwo = View.generateViewId();
                cancelSoundTwo.setId(tempIdCancelViewTwo);
                soundTwo = PerfectLoopMediaPlayer.create(AddMusic.this,R.raw.korg);
                soundTwo.prepare();

                break;
*/
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("storedLayoutId",layoutId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
