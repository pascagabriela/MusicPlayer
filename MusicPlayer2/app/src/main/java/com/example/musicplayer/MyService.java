package com.example.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    MediaPlayer musicPlayer;
    private final IBinder mBinder = new MyLocalBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyLocalBinder extends Binder {
        MyService getBoundService(){
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int song = intent.getIntExtra("song", 0);
        musicPlayer = MediaPlayer.create(this, song);
        musicPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        musicPlayer.stop();
        super.onDestroy();
    }
}
