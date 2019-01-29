/*
package com.example.H5PlusPlugin;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;


*/
/**
 * 5+ SDK 扩展插件示例
 * 5+ 扩扎插件在使用时需要以下两个地方进行配置
 * 		1  WebApp的mainfest.json文件的permissions节点下添加JS标识
 * 		2  assets/data/properties.xml文件添加JS标识和原生类的对应关系
 * 本插件对应的JS文件在 assets/apps/H5Plugin/js/test.js
 * 本插件对应的使用的HTML assest/apps/H5plugin/index.html
 * 
 * 更详细说明请参考文档http://ask.dcloud.net.cn/article/66
 * **//*

public class PGPlugintest extends StandardFeature
{   

    public void onStart(Context pContext, Bundle pSavedInstanceState, String[] pRuntimeArgs) {
        
        */
/**
         * 如果需要在应用启动时进行初始化，可以继承这个方法，并在properties.xml文件的service节点添加扩展插件的注册即可触发onStart方法
         * *//*

    }

    public void PluginTestFunction(IWebview pWebview, JSONArray array)
    {
    	// 原生代码中获取JS层传递的参数，
    	// 参数的获取顺序与JS层传递的顺序一致
        String CallBackID = array.optString(0);
        JSONArray newArray = new JSONArray();
        newArray.put(array.optString(1));
        newArray.put(array.optString(2));
        newArray.put(array.optString(3));
        newArray.put(array.optString(4));
        // 调用方法将原生代码的执行结果返回给js层并触发相应的JS层回调函数
        JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);

    }

    public void PluginTestFunctionArrayArgu(IWebview pWebview, JSONArray array)
    {
        String ReturnString = null;
        String CallBackID =  array.optString(0);
        JSONArray newArray = null;
        try {

            newArray = new JSONArray( array.optString(1));          
            String inValue1 = newArray.getString(0);
            String inValue2 = newArray.getString(1);
            String inValue3 = newArray.getString(2);
            String inValue4 = newArray.getString(3);
            ReturnString = inValue1 + "-" + inValue2 + "-" + inValue3 + "-" + inValue4;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSUtil.execCallback(pWebview, CallBackID, ReturnString, JSUtil.OK, false);
    }

    public String PluginTestFunctionSyncArrayArgu(IWebview pWebview, JSONArray array)
    {
        JSONArray newArray = null;
        JSONObject retJSONObj = null;
        try {

            newArray = array.optJSONArray(0);
            String inValue1 = newArray.getString(0);
            String inValue2 = newArray.getString(1);
            String inValue3 = newArray.getString(2);
            String inValue4 = newArray.getString(3);

            retJSONObj = new JSONObject();
            retJSONObj.putOpt("RetArgu1", inValue1);
            retJSONObj.putOpt("RetArgu2", inValue2);
            retJSONObj.putOpt("RetArgu3", inValue3);
            retJSONObj.putOpt("RetArgu4", inValue4);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }       

        return JSUtil.wrapJsVar(retJSONObj);
    }

    public String PluginTestFunctionSync(IWebview pWebview, JSONArray array)
    {
        String inValue1 = array.optString(0);
        String inValue2 = array.optString(1);
        String inValue3 = array.optString(2);
        String inValue4 = array.optString(3);

        String ReturnValue = inValue1 + "-" + inValue2 + "-" + inValue3 + "-" + inValue4;
        // 只能返回String类型到JS层。
        return JSUtil.wrapJsVar(ReturnValue,true);
    }

}*/


package com.example.H5PlusPlugin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import android.view.Choreographer;
import android.widget.Toast;

import com.HBuilder.integrate.DemoActivity;
import com.HBuilder.integrate.MainActivity;
import com.HBuilder.integrate.util.GlobalData;
import com.usdk.core.data.BaseData;
import com.usdk.core.data.Busi_Data;
import com.usdk.core.data.RequestData;
import com.fuyousf.android.fuious.service.MOneSupportService;
import com.fuyousf.android.fuious.service.PrintInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSUtil;
/**
 * 更详细说明请参考文档http://ask.dcloud.net.cn/article/66
 **/
public class PGPlugintest extends StandardFeature{


/*	public void PGPlugintest(Context context){

	}*/


    private PrintInterface printService = null;
    // private Context pContext;
    private MOneSupportService stuService = null;
    private RFReceiver rfReceiver ;
    private PrintReceiver printReceiver ;
    private String lastResult;
    private String activityName;

    private Activity nowActivity;

    @Override
    public void onReceiver(Intent content){
        System.out.println("Receiver----------------intent");
        System.out.println(content);

    }

    @Override
    public void onNewIntent(Intent content){
        System.out.println("onNewIntent----------------intent");
        System.out.println(content);
    }

    @Override
    public void onResume(){
        Toast.makeText(this.getDPluginContext(), "onStart", Toast.LENGTH_LONG).show(); //测试
        System.out.println("resume------------------------");
        System.out.println(GlobalData.dataString);
//        GlobalData.CallBackID = array.optString(0);
//        GlobalData.pWebview = pWebview;
        JSUtil.execCallback(GlobalData.pWebview, GlobalData.CallBackID , GlobalData.dataString, JSUtil.OK, false);


    }
    @Override
    public void onStart(Context pContext, Bundle pSavedInstanceState, String[] pRuntimeArgs) {
        super.onStart(pContext, pSavedInstanceState, pRuntimeArgs);
        System.out.println(GlobalData.dataString);
        System.out.println(this.getDPluginContext());
        nowActivity = this.getCurrentActivity();
        System.out.println("----------------------------------------nowActivitys=-==================");
        System.out.println(nowActivity);
        GlobalData.globalActivity = nowActivity;
        this.initDPlugin(pContext, nowActivity);

        this.getDPluginActivity();
        System.out.println("pContextdddddddddddddd------------------------------------");
        System.out.println(pContext);
        System.out.println(this.getDPluginContext());
//		this.nowActivity = (Activity)(this.getDPluginActivity());
//		this.nowActivity = this.getDPluginActivity();
        System.out.println("-----------------------------------000000000");
        System.out.println(this.nowActivity);
        /**
         * 如果需要在应用启动时进行初始化，可以继承这个方法，并在properties.xml文件的service节点添加扩展插件的注册即可触发onStart方法
         */
//		 Toast.makeText(pContext, "onStart", Toast.LENGTH_LONG).show();
        // this.pContext = pContext;
        System.out.println("我已经启动了----------------------------------");
        this.initService();
        this.initPrintService();

//        PrintService.initService(mApplicationContext);
//        PrintService.initPrintService(mApplicationContext);
    }

    //绑定服务，注册广播
    private void initService(){
        registerReceiver();
        Intent terminalIntent = new Intent("com.fuyousf.android.fuious.service.MOneSupportService");
        terminalIntent.setAction("com.fuyousf.android.fuious.service.MOneSupportService");
        terminalIntent.setPackage("com.fuyousf.android.fuious");
//		bindService(terminalIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        mApplicationContext.bindService(terminalIntent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private void initPrintService(){
        printRegisterReceiver();
        Intent printIntent = new Intent(/*"com.fuyousf.android.fuious.service.PrintInterface"*/);
        printIntent.setAction("com.fuyousf.android.fuious.service.PrintInterface");
        printIntent.setPackage("com.fuyousf.android.fuious");
//		bindService(printIntent, printServiceConnection, Context.BIND_AUTO_CREATE);
        mApplicationContext.bindService(printIntent, printServiceConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection printServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            printService = PrintInterface.Stub.asInterface(arg1);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {

        }

    };

    //服务的链接
    private ServiceConnection serviceConnection = new ServiceConnection() {

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
    private void registerReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.fuyousf.android.fuious.service");
        rfReceiver = new RFReceiver();
//		registerReceiver(rfReceiver, intentFilter);
    }

    private void printRegisterReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.fuyousf.android.fuious.service.print");
        printReceiver = new PrintReceiver();
//		registerReceiver(printReceiver, intentFilter);
    }


    class RFReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String status = intent.getStringExtra("status");
            String result = intent.getStringExtra("result");
//			etBack.setText("状态："+status+"--|结果："+result);
        }
    }

    class PrintReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
//			etBack.setText("reason："+result);

        }
    }

//	old

    public void printTicket(IWebview pWebview, JSONArray array) {
        // 原生代码中获取JS层传递的参数，
        // 参数的获取顺序与JS层传递的顺序一致

        GlobalData.CallBackID = array.optString(0);
        GlobalData.pWebview = pWebview;
        System.out.println(this.getDPluginContext());
        invokeGetTerminal(0, array);// array 为参数
//        JSUtil.execCallback(pWebview, array.optString(0), "1", JSUtil.OK, false);
    }
    public void getDeviceInfo(IWebview pWebview, JSONArray array) {
        // 原生代码中获取JS层传递的参数，
        // 参数的获取顺序与JS层传递的顺序一致
        GlobalData.CallBackID = array.optString(0);
        GlobalData.pWebview = pWebview;
        System.out.println(this.getDPluginContext());
        invokeFuPrintTicket(0, array);// array 为参数

    }
    public void scanInfo(IWebview pWebview, JSONArray array) {
        // 原生代码中获取JS层传递的参数，
        // 参数的获取顺序与JS层传递的顺序一致
        GlobalData.CallBackID = array.optString(0);
        GlobalData.pWebview = pWebview;
        System.out.println(this.getDPluginContext());
        invokeFuScan(0, array);// array 为参数

    }
    private void invokeFuPrintTicket(int requsetCode, JSONArray array) {

        try {

            //				打印開始-------
            /*if(null != printService){
                JSONArray data = new JSONArray(array.optString(1));
                String jsonDate = printDetail();
                Log.i("TAG", "json："+jsonDate);
                Log.i("TAG", "json："+data);
                try {
                    printService.print(jsonDate);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }*/

            if(null!=printService){
                System.out.println("js come array--------");
                System.out.println(array);
                Intent intent32=new Intent();
//              intent32.setClass(this.getDPluginContext(), DemoActivity.class);
                intent32.setClass(this.getDPluginContext(), MainActivity.class);
                GlobalData.callMethodId = 1007;// 打印最后一笔
                nowActivity.startActivity(intent32); // 启动
            }

            //				打印结束-------


        } catch (Exception e) {
            // Toast.makeText(pContext, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private void invokeFuScan(int requsetCode, JSONArray array) {

        try {
            System.out.println("js come array--------");
            System.out.println(array);
            Intent intent32=new Intent();
//              intent32.setClass(this.getDPluginContext(), DemoActivity.class);
            intent32.setClass(this.getDPluginContext(), MainActivity.class);
            GlobalData.callMethodId = 1015;// 扫码
            nowActivity.startActivity(intent32); // 启动


        } catch (Exception e) {
            // Toast.makeText(pContext, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void invokeGetTerminalParams(IWebview pWebview, JSONArray array) {
        // 原生代码中获取JS层传递的参数，
        // 参数的获取顺序与JS层传递的顺序一致
        String CallBackID = array.optString(0);

//		invokePrintTicket(0, array);
        invokeGetTerminal(0, array);

        JSUtil.execCallback(pWebview, CallBackID, "1", JSUtil.OK, false);
    }


    private void invokeGetTerminal(int requsetCode, JSONArray array) {

        try {
            System.out.println("js come array--------");
            System.out.println(array);
            Intent intent32=new Intent();
//            intent32.setClass(this.getDPluginContext(), DemoActivity.class);
            intent32.setClass(this.getDPluginContext(), MainActivity.class);
            GlobalData.callMethodId = 1009;
            nowActivity.startActivity(intent32); // 启动
        } catch (Exception e) {
            // Toast.makeText(pContext, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }



    private String getBitmapBase64Sttring(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 0, baos);// 压缩位图
        byte[] compressImag = baos.toByteArray();
        String icon = Base64.encodeToString(compressImag, Base64.DEFAULT);
        return icon;
    }


    /**
     * 下面是从assets中将图片复制到SD卡目录的参考方法 图片是黑白单色图
     */
    private void copyFile(String filename,Context context) {
        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            makeRootDirectory("/sdcard/tmp");
            String newFileName = "/sdcard/tmp/" + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.d("Fuiou", e.getMessage());
        }

    }

    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                Log.d("Login", "new filePath:" + filePath);
                file.mkdir();
            }
        } catch (Exception e) {

        }
    }

    public String getFromAssets(String fileName,Context context) {
        try {
            Resources mResources = context.getResources();// 在非Activity调用getResources()方法
            InputStreamReader inputReader = new InputStreamReader(
                    mResources.getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 打印交易明细
     *
     */
    public static String printDetail() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            //打印文字
            jsonArray.put(PrintUtils.setStringContent("交易明细/TXN LIST", 2, 3));
            jsonArray.put(PrintUtils.setStringContent("凭证号      商户单号      订单金额      交易金额", 1, 1));
            jsonArray.put(PrintUtils.setStringContent("VOUCHER  ORDER NUMBER    TOTALAMOUNT  SALEAMOUNT", 1, 1));
            jsonArray.put(PrintUtils.setStringContent("================================================", 1, 1));// 打印虚线
            for (int i = 0; i < 2; i++) {
                /*
                 * 打印交易明细, 1根据ctype判断要打印的内容 1.C1,C2微信查询和支付宝查询不打印交易明细
				 * 2.D1,D2微信撤销和支付宝撤销加负号,price等于0,两个都用了money 3.
				 */
                jsonArray.put(PrintUtils.setStringContent("" + i + " " + "201709061133123456"
                        + "0.01" + "0.01", 1, 1));
            }
            jsonArray.put(PrintUtils.setStringContent("POS签购单", 1, 3));// 打印切纸线
            jsonArray.put(PrintUtils.setfreeLine("5"));
            jsonObject.put("spos", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i("Fuiou", "resultCode--返回值："+resultCode);
        Log.i("TAG", "resultCode--返回值："+resultCode);
        System.out.println("-----------------------------------");
        System.out.println(intent);
        lastResult =  intent.getExtras().getString("merchantId");

    }


    // 获取当前Activity
    public static Activity getCurrentActivity () {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
