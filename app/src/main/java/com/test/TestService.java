package com.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

    private static final String TAG = TestService.class.getSimpleName();

    public String data = "service study";
    private boolean running = false;

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "bind service");
        return new TestBind();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "unbind service");
        return super.onUnbind(intent);
    }

    class TestBind extends android.os.Binder{

        public void startDownload(){
            Log.d(TAG, "start download ...");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "create service");
        running = true;
        new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while(running){
                    i++;
                    Log.d(TAG, data+"---"+i);

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "start command" + "," + flags + "," + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destory service");
        running = false;
    }

}