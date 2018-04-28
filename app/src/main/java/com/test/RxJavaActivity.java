package com.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.test.common.ToastUtil;
import com.test.rxjava.RxDownLoadUtil;
import com.test.rxjava.RxJavaUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xingle on 2018/1/23.
 */

public class RxJavaActivity extends Activity {

    @BindView(R.id.img_download) ImageView imgDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_create, R.id.btn_print, R.id.btn_from, R.id.btn_download})
    void onClickListener(View view){
        switch (view.getId()) {
            case R.id.btn_create:
                RxJavaUtil.create();
                break;
            case R.id.btn_print:
                RxJavaUtil.createPrint();
                break;
            case R.id.btn_from:
//                RxJavaUtil.from();
//                RxJavaUtil.interval();
                RxJavaUtil.just();
                break;
            case R.id.btn_download:
                RxDownLoadUtil downLoadUtil = new RxDownLoadUtil();
                downLoadUtil.downloadImg("http://q.qlogo.cn/qqapp/1106695018/22A771CDC3C851BF197AAD2589F4FE86/100")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<byte[]>() {
                            @Override
                            public void onCompleted() {
                                ToastUtil.showToast("下载完成");
                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil.showToast("下载失败"+e.getMessage());
                            }

                            @Override
                            public void onNext(byte[] bytes) {
                                LogUtil.d("%s",bytes.length+"");
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                imgDownload.setImageBitmap(bitmap);
                            }
                        });
                break;
        }
    }
}
