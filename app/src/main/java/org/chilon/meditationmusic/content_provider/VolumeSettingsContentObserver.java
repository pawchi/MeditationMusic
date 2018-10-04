package org.chilon.meditationmusic.content_provider;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.SeekBar;

public class VolumeSettingsContentObserver extends ContentObserver implements SeekBar.OnSeekBarChangeListener {
    private AudioManager audioManager;
    private OnVolumeChangedListener listener;
    private boolean skip = false;

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
    public void onChange(boolean selfChange) {}

    public void setVolume(int currentVolume) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
    }

    public int getVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public void register(Context context) {
        context.getApplicationContext().getContentResolver().registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, this);
    }

    public void unregister(Context context) {
        context.getApplicationContext().getContentResolver().unregisterContentObserver(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        if (!skip) {
            setVolume(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public boolean dispatchKeyEvent(KeyEvent event){
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            return onKeyDown(event);
        } else if (event.getAction() == KeyEvent.ACTION_UP) {
            return onKeyUp(event);
        } else {
            return false;
        }
    }

    private boolean onKeyUp(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                skip = false;
                return true;
            default:
                return false;
        }
    }


    private boolean onKeyDown(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                                AudioManager.ADJUST_RAISE,
                                AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                listener.onVolumeChanged(getVolume());
                skip = true;
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                                AudioManager.ADJUST_LOWER,
                                AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                skip = true;
                listener.onVolumeChanged(getVolume());
                return true;

            default:
                return false;
        }
    }
}



