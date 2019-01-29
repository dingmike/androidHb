package com.example.H5PlusPlugin;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.fuyousf.android.fuious.service.MOneSupportService;
import com.fuyousf.android.fuious.service.PrintInterface;

/**
 * Created by admin on 2019/1/29.
 */

public class PrintService {
    private static PrintInterface printService = null;
    // private Context pContext;
    private static MOneSupportService stuService = null;
    private static RFReceiver rfReceiver ;
    private static PrintReceiver printReceiver ;

    //绑定服务，注册广播
    public static void initService(Context mApplicationContext){
        registerReceiver();
        Intent terminalIntent = new Intent("com.fuyousf.android.fuious.service.MOneSupportService");
        terminalIntent.setAction("com.fuyousf.android.fuious.service.MOneSupportService");
        terminalIntent.setPackage("com.fuyousf.android.fuious");
//		bindService(terminalIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        mApplicationContext.bindService(terminalIntent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    public static void initPrintService(Context mApplicationContext){
        printRegisterReceiver();
        Intent printIntent = new Intent(/*"com.fuyousf.android.fuious.service.PrintInterface"*/);
        printIntent.setAction("com.fuyousf.android.fuious.service.PrintInterface");
        printIntent.setPackage("com.fuyousf.android.fuious");
//		bindService(printIntent, printServiceConnection, Context.BIND_AUTO_CREATE);
        mApplicationContext.bindService(printIntent, printServiceConnection, Context.BIND_AUTO_CREATE);
    }


    private static ServiceConnection printServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            printService = PrintInterface.Stub.asInterface(arg1);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {

        }

    };

    //服务的链接
    private static ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.w("TAG", "--绑定service--");
            stuService = MOneSupportService.Stub.asInterface(service);
        }
    };

    //广播注册
    private static void registerReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.fuyousf.android.fuious.service");
        rfReceiver = new PrintService.RFReceiver();
//		registerReceiver(rfReceiver, intentFilter);
    }

    private static void printRegisterReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.fuyousf.android.fuious.service.print");
        printReceiver = new PrintService.PrintReceiver();
//		registerReceiver(printReceiver, intentFilter);
    }


    static class RFReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String status = intent.getStringExtra("status");
            String result = intent.getStringExtra("result");
//			etBack.setText("状态："+status+"--|结果："+result);
        }
    }

    static class PrintReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
//			etBack.setText("reason："+result);

        }
    }



}
