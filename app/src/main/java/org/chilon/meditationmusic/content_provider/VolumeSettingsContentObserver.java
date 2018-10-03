package org.chilon.meditationmusic.content_provider;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.widget.SeekBar;

public class VolumeSettingsContentObserver extends ContentObserver implements SeekBar.OnSeekBarChangeListener {
    private AudioManager audioManager;
    private OnVolumeChangedListener listener;

    public VolumeSettingsContentObserver(Context context, Handler handler, OnVolumeChangedListener listener) {
        super(handler);
        this.listener = listener;
        audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return false;
    }

    @Override
    public void onChange(boolean selfChange) {
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        listener.onVolumeChanged(currentVolume);
    }

    public void setVolume(int currentVolume){
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
    }

    public int getVolume(){
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public void register(Context context){
        context.getApplicationContext().getContentResolver().registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, this);
    }

    public void unregister(Context context) {
        context.getApplicationContext().getContentResolver().unregisterContentObserver(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        setVolume(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}



