package com.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by chenzhenxing on 17/12/16.
 */

public class D extends Activity implements View.OnClickListener {

    public static final String TAG = D.class.getSimpleName();

    private TestService.TestBind bind = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        Log.d(TAG, "onCreate" + "," + this.getTaskId());
        TextView textView = findViewById(R.id.tv_text);
        textView.setText("测试Laucher mode" + "-----" + TAG + "------" + this.getTaskId());
        findViewById(R.id.btn_a).setOnClickListener(this);
        findViewById(R.id.btn_b).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
        findViewById(R.id.btn_d).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_bind).setOnClickListener(this);
        findViewById(R.id.btn_unbind).setOnClickListener(this);
        findViewById(R.id.btn_destory).setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_a:
                startActivity(new Intent(this, A.class));
                break;
            case R.id.btn_b:
                startActivity(new Intent(this, B.class));
                break;
            case R.id.btn_c:
                startActivity(new Intent(this, C.class));
                break;
            case R.id.btn_d:
                startActivity(new Intent(this, D.class));
                break;
            case R.id.btn_start:
                Log.d(TAG, "start btn");
                startService(new Intent(this, TestService.class));
                break;
            case R.id.btn_bind:
                Log.d(TAG, "bind btn");
                bindService(new Intent(this, TestService.class), connection, 0);
                break;
            case R.id.btn_unbind:
                Log.d(TAG, "unbind btn");
                unbindService(connection);
                break;
            case R.id.btn_destory:
                Log.d(TAG, "destory btn");
                stopService(new Intent(this, TestService.class));
                break;
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "connected " + name + "，" + service.toString());
            bind = (TestService.TestBind) service;
            bind.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "disconnected" + name);
            bind = null;
        }
    };
}
