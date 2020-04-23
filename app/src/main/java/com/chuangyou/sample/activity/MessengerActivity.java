package com.chuangyou.sample.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.chuangyou.sample.R;

public class MessengerActivity extends AppCompatActivity {

    private static final String TAG = "MessengerActivity";
    private static final int MSG_SAY_HELLO = 1;
    private static final int MSG_SAY_WORLD = 2;

    private Messenger mMessenger = null;
    private boolean mBound ;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG,"绑定成功");
            mMessenger = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG,"绑定失败");
            mMessenger = null;
            mBound = false;
        }
    };

    private void sayHello(){
        if (!mBound)
            return;
        Message msg = Message.obtain();
        msg.what = MSG_SAY_HELLO;
        try {
            mMessenger.send(msg);
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }

    private void sayWorld() {
        if (!mBound)
            return;
        Message msg = Message.obtain();
        msg.what = MSG_SAY_WORLD;
        try{
            mMessenger.send(msg);
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_messenger);

        Button say_hello = findViewById(R.id.say_hello);
        say_hello.setOnClickListener(v -> sayHello());
        Button say_world = findViewById(R.id.say_world);
        say_world.setOnClickListener(v -> sayWorld());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
        Intent intent = new Intent("com.chuangyou.messenger");
        intent.setPackage("com.chuangyou.server");
        bindService(intent,connection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
        if (mBound) {
            unbindService(connection);
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }
}
