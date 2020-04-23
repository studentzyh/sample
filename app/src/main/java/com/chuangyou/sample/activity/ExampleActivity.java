package com.chuangyou.sample.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.chuangyou.sample.R;
import com.chuangyou.server.MyAIDLService;

public class ExampleActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private Button startService;
    private Button stopService;
    private Button unbindService;

    private MyAIDLService myAIDLService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG,"绑定成功");
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try{
                int result = myAIDLService.plus(50,50);
                String upperStr = myAIDLService.toUpperCase("comes from ClientTest");
                Log.d(TAG,"result is "+result);
                Log.d(TAG,"upperStr is "+upperStr);
            }catch(RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG,"绑定失败");
            myAIDLService = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        findViewById(R.id.bindService).setOnClickListener(v->{
            Intent intent = new Intent("com.chuangyou.server.MyAIDLService");
            intent.setPackage("com.chuangyou.server");
            bindService(intent,connection, Context.BIND_AUTO_CREATE);
        });
    }
}
