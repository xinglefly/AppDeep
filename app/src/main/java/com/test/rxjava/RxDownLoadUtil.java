package com.test.rxjava;

import com.test.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by xingle on 2018/2/2.
 */

public class RxDownLoadUtil {

    OkHttpClient client;

    public RxDownLoadUtil() {
        client = new OkHttpClient();
    }

    public Observable<byte[]> downloadImg(String url){
        return Observable.create(new Observable.OnSubscribe<byte[]>(){

            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(url).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            LogUtil.Object(response);
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                subscriber.onNext(data);
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });
    }
}
