package com.test;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.common.ToastUtil;
import com.test.decorator_pattern.Beverage;
import com.test.decorator_pattern.Milk;
import com.test.decorator_pattern.Mocha;
import com.test.decorator_pattern.CoffeeBean1;
import com.test.observer_pattern.ConcreateWatch;
import com.test.observer_pattern.ConcreateWatched;
import com.test.observer_pattern.Watch;
import com.test.observer_pattern.Watched;
import com.test.rxjava.SimpleObservable;
import com.test.rxjava.SimpleObserver;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tv_decorator)
    TextView tvDecorator;
    @BindView(R.id.tv_application)
    TextView tvApplicationId;

    private int backClickTime;
    MyService.Binder binder = null;
    NotificationManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        //internationalization
        Resources resources = getBaseContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale("en", "");
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());//更新配置
        LogUtil.d("%s", configuration.locale.getLanguage());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, A.class));
            }
        });

        //get ApplicationId
        tvApplicationId.setText("获取当前ApplicationID相关信息：\n" + "当前进程： " + android.os.Process.myPid()
                + "\n ApplicationID :  " + MyApplication.applicationId
                + "\n 应用名称：" + MyApplication.getInstance().getApplicationInfo().className);

        //new Decorator
        Beverage beverage = new CoffeeBean1();
        beverage = new Milk(beverage);
        beverage = new Mocha(beverage);

        tvDecorator.setText(beverage.getDescription() + "\n 价格：" + beverage.getPrices());
    }

    @Override
    protected void onPause() {
        super.onPause();
//        ToastUtil.showToast("切换到后台");
//        LogUtil.d("TestAPP---> onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ToastUtil.showToast("切换到前台");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_app, R.id.btn_start, R.id.btn_stop, R.id.btn_bind, R.id.btn_unbind, R.id.btn_sync, R.id.btn_notify, R.id.btn_observer, R.id.btn_rxjava_observer, R.id.btn_rxjava, R.id.btn_intentservice})
    public void onClick(View view) {
        Intent intent = new Intent(this, MyService.class);
        switch (view.getId()) {
            case R.id.btn_app:
                ComponentName cn = new ComponentName("com.test1", "com.test.MainActivity");
                intent.setComponent(cn);
                startActivity(intent);
                break;
            case R.id.btn_start:
                Log.d(TAG, "start service");
                startService(intent);
                break;
            case R.id.btn_stop:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.btn_bind:
                bindService(new Intent(this, MyService.class), connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(connection);
                break;
            case R.id.btn_sync:
                if (binder != null) {
                    binder.setData(etName.getText().toString());
                }
                break;
            case R.id.btn_notify:

                NotificationCompat.Builder notify = (NotificationCompat.Builder) new NotificationCompat.Builder(this);
                notify.setSmallIcon(R.mipmap.ic_launcher);
                notify.setContentTitle("测试");
                notify.setContentText("都忘了呀");
                int max = 100;
                int progress = 10;
                boolean indeterminate = false;
                notify.setProgress(max, progress, true);

                int flags = PendingIntent.FLAG_UPDATE_CURRENT;
                Intent inte = new Intent(this, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, flags);
                notify.setContentIntent(pi);


                manager.notify(0, notify.build());

                break;
            case R.id.btn_observer:
                Watched thief = new ConcreateWatched();

                Watch policeMan = new ConcreateWatch();
                Watch policeWomen = new ConcreateWatch();

                thief.addWatch(policeMan);
                thief.addWatch(policeWomen);

                thief.notify("小偷开始行动要去抢银行了！");
                break;
            case R.id.btn_rxjava_observer:
                SimpleObservable simpleObservable = new SimpleObservable();
                SimpleObserver observer = new SimpleObserver(simpleObservable);

                simpleObservable.setData(1);
                simpleObservable.setData(2);
                simpleObservable.setData(2);
                simpleObservable.setData(3);
                break;
            case R.id.btn_rxjava:
                Intent intent1 = new Intent(this, RxJavaActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent1);
                break;

            case R.id.btn_intentservice:
                startService(new Intent(this, TheIntentService.class));
                break;

        }
    }


    ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected" + name + ",," + service.toString());
            binder = (MyService.Binder) service;
            binder.getService().setCallback(new MyService.Callback() {
                @Override
                public void onChangeData(final String data) {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etName.setText(data);
                        }
                    });
//                Message message = new Message();
//                Bundle bundle = new Bundle();
//                bundle.putString("data", data);
//                message.setData(bundle);
//                handler.sendMessage(message);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            binder = null;
        }
    };


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            etName.setText(msg.getData().getString("data"));
        }
    };

    /**
     * 处理返回键：只有在3秒内按返回2次，才退出应用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
                getSupportFragmentManager().popBackStack();
            } else {
                if (backClickTime == 1) {
                    Process.killProcess(Process.myPid());
                    backClickTime = 0;
                } else {
                    ToastUtil.showToast(R.string.back_confirm);
                    backClickTime++;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            backClickTime = 0;
                        }
                    }, 3000);
                }
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

class TheIntentService extends IntentService {

    private static final String TAG = TheIntentService.class.getSimpleName();

    public TheIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "耗时前");

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "耗时后");

    }
}
