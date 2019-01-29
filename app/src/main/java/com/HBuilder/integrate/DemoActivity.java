package com.HBuilder.integrate;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Button;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class DemoActivity extends Activity implements OnClickListener {

    private final String TAG = "MainActivity";

    private String lastResult;

    BridgeWebView webView;

    Button button;

    int RESULT_CODE = 0;

    ValueCallback<Uri> mUploadMessage;

    ValueCallback<Uri[]> mUploadMessageArray;

    static class Location {
        String address;
    }

    static class User {
        String name;
        Location location;
        String testStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);


        //				获取開始-------
        Intent intent18 = new Intent();
        intent18.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

        //包名     包名+类名（全路径）
        intent18.putExtra("transName", "终端参数");

        startActivityForResult(intent18, 0);
//				获取结束-------


        setResult(RESULT_OK, intent18);
        finish();


        webView = (BridgeWebView) findViewById(R.id.webView);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);

        webView.setDefaultHandler(new DefaultHandler());

        webView.setWebChromeClient(new WebChromeClient() {

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                pickFile();
            }

			/*@Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
				mUploadMessageArray = filePathCallback;
				pickFile();
				return true;
			}*/
        });

//        webView.loadUrl("file:///android_asset/demo.html");
        webView.loadUrl("http://192.168.10.71:8087/index.html");
//        webView.loadUrl("http://www.baidu.com");

        webView.registerHandler("submitFromWeb", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG, "handler = submitFromWeb, data from web = " + data);
                function.onCallBack("submitFromWeb exe, response data 中文 from Java");
            }

        });


        //自定义打印
        webView.registerHandler("AIDLPrintFromWeb", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {


//				打印開始-------
                copyFile("pay.bmp");
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.fuyousf.android.fuious", "com.fuyousf.android.fuious.CustomPrinterActivity"));
                //包名     包名+类名（全路径）
                String str = getFromAssets("json.txt");
                intent.putExtra("data", str);
                intent.putExtra("isPrintTicket", "true");
                startActivityForResult(intent, 99);
//				打印结束-------


                Log.i(TAG, "handler = AIDLPrintFromWeb, data from web = " + data);
                function.onCallBack("AIDLPrintFromWeb exe, response data 中文 from Java");
            }

        });

        //扫码
        webView.registerHandler("scanFromWeb", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {


//				扫码開始-------
                Intent intent25 = new Intent();
                intent25.setComponent(new ComponentName("com.fuyousf.android.fuious",
                        "com.fuyousf.android.fuious.NewSetScanCodeActivity"));
                intent25.putExtra("flag", "true");
                startActivityForResult(intent25, 0);
//				扫码结束-------


                Log.i(TAG, "handler = AIDLPrintFromWeb, data from web = " + data);
                function.onCallBack("AIDLPrintFromWeb exe, response data 中文 from Java");
            }

        });


        // 获取终端参数
        webView.registerHandler("getTerminalParamsFromWeb", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {


//				获取開始-------
                Intent intent18 = new Intent();
                intent18.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

                //包名     包名+类名（全路径）
                intent18.putExtra("transName", "终端参数");

                startActivityForResult(intent18, 0);
//				获取结束-------


                Log.i(TAG, "handler = AIDLPrintFromWeb, data from web = " + data);
//                function.onCallBack("AIDLPrintFromWeb exe, response data 中文 from Java");
                function.onCallBack(lastResult);
            }

        });




        User user = new User();
        Location location = new Location();
        location.address = "SDU";
        user.location = location;
        user.name = "大头鬼";

        webView.callHandler("functionInJs", new Gson().toJson(user), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });

        webView.send("hello");

    }

    public void pickFile() {
        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setType("image/*");
        startActivityForResult(chooserIntent, RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        lastResult =  intent.getExtras().getString("merchantId");





          if (requestCode == RESULT_CODE) {
            if (null == mUploadMessage && null == mUploadMessageArray) {
                return;
            }
            if (null != mUploadMessage && null == mUploadMessageArray) {
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }

            if (null == mUploadMessage && null != mUploadMessageArray) {
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUploadMessageArray.onReceiveValue(new Uri[]{result});
                mUploadMessageArray = null;
            }

        }
    }
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 获取打开页面返回的数据
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Fuiou", "resultCode--返回值："+resultCode);
        Log.i("TAG", "resultCode--返回值："+resultCode);
        switch (resultCode) {
            case Activity.RESULT_CANCELED:
                String reason = "";
                String traceNo = "";
                String batchNo = "";
                String ordernumber = "";
                if (data != null) {
                    Bundle b = data.getExtras();
                    if (b != null) {
                        reason = (String) b.get("reason");
                        traceNo = (String)b.getString("traceNo");
                        batchNo = (String)b.getString("batchNo");
                        ordernumber = (String)b.getString("ordernumber");
                    }
                }
                if (reason != null) {
                    Log.d("reason", reason);
                    Toast.makeText(this, reason, 0).show();
                }
                Log.w("TAG", "失败返回值--reason--返回值："+reason+"/n 凭证号："+traceNo+"/n 批次号："+batchNo+"/n 订单号："+ordernumber);

                etBack.setText("失败："+reason+"/n 凭证号："+traceNo+"/n 批次号："+batchNo+"/n 订单号："+ordernumber);
                break;
            case Activity.RESULT_OK: // 请求成功返回数据
                String print = data.getStringExtra("reason");//打印成功返回数据
                Log.w("TAG", "成功返回值--reason--返回值："+print);

                String b = data.getStringExtra("batchNo");//批次号
                String c = data.getStringExtra("traceNo");//流水号
                String d = data.getStringExtra("cardNo");//卡号

                String e = data.getStringExtra("merchantId");//商户号
                String f = data.getStringExtra("terminalId");//终端号

                String g = data.getStringExtra("referenceNo");//参考号
                String h = data.getStringExtra("issue");//发卡行
                String i = data.getStringExtra("type");//卡类型
                String j = data.getStringExtra("date");//日期
                String k = data.getStringExtra("time");//时间

                String l = data.getStringExtra("wireless.apn");//apn
                String m = data.getStringExtra("wireless.username");//用户名
                String n = data.getStringExtra("wireless.password");//密码
                String o = data.getStringExtra("wireless.apnEnabled");//Apn是否开启
                String p = data.getStringExtra("merchantName");//商户名

                String r = data.getStringExtra("oldReferenceNo");//原参考号

                String t = data.getStringExtra("orderNumber");
                String tzfb = data.getStringExtra("zfbOrderNumber");
                String twx = data.getStringExtra("wxOrderNumber");

                String u = data.getStringExtra("oldOrderNumber");
                String sWx = data.getStringExtra("wxOldOrderNumber");
                String sZfb = data.getStringExtra("zfbOldOrderNumber");

                String wMb = data.getStringExtra("zfbMbOldOrderNumber");
                String zMb = data.getStringExtra("wxMbOldOrderNumber");

                String tui = data.getStringExtra("tuiOldOrderNumber");

                String settleData = data.getStringExtra("settleJson");
                String json = data.getStringExtra("json");

                String return_Code = data.getStringExtra("return_txt");//扫码返回数据

                String authorizationCode = data.getStringExtra("authorizationCode");//预授权 授权码

                String backOldReferenceNo = data.getStringExtra("backOldReferenceNo");//退货的原参考号

                String referenceNoSuccess = data.getStringExtra("referenceNoSuccess");//订单查询返回参考号

                String oldReferenceNoSuccess = data.getStringExtra("oldReferenceNoSuccess");//订单查询返回原参考号

                String amount = data.getStringExtra("amount");//金额

                String acqId = data.getStringExtra("acqId");//收单行

                String expiredDate = data.getStringExtra("expiredDate");//卡有效期

                JSONObject jsO = new JSONObject();
                try {
                    jsO.put("reason", print);
                    jsO.put("batchNo", b);//批次号
                    jsO.put("traceNo", c);//流水号
                    jsO.put("cardNo", d);//卡号
                    jsO.put("merchantId", e);//商户号
                    jsO.put("terminalId", f);//终端号
                    jsO.put("referenceNo", g);//参考号
                    jsO.put("issue", h);//发卡行
                    jsO.put("type", i);//卡类型
                    jsO.put("date", j);//日期
                    jsO.put("time", k);//时间
                    jsO.put("wireless.apn", l);//apn
                    jsO.put("wireless.username", m);//用户名
                    jsO.put("wireless.password", n);//密码
                    jsO.put("wireless.apnEnabled", o);//Apn是否开启
                    jsO.put("merchantName", p);//商户名
                    jsO.put("oldReferenceNo", r);//原参考号
                    jsO.put("backOldReferenceNo", backOldReferenceNo);//退货原参考号
                    jsO.put("orderNumber", t);//消费订单号
                    jsO.put("zfbOrderNumber", tzfb);//支付宝消费订单号
                    jsO.put("wxOrderNumber", twx);//微信消费订单号
                    jsO.put("oldOrderNumber", u);//原消费订单号
                    jsO.put("wxOldOrderNumber", sWx);//原支付宝消费订单号
                    jsO.put("zfbOldOrderNumber", sZfb);//原微信消费订单号
                    jsO.put("zfbMbOldOrderNumber",wMb);//原支付宝末笔订单号
                    jsO.put("wxMbOldOrderNumber", zMb);//原微信末笔订单号
                    jsO.put("tuiOldOrderNumber", tui);//退款订单号
                    jsO.put("return_txt", return_Code);//扫码返回数据
                    jsO.put("authorizationCode", authorizationCode);//授权码
                    jsO.put("referenceNoSuccess", referenceNoSuccess);//订单查询参考号
                    jsO.put("oldReferenceNoSuccess", oldReferenceNoSuccess);//订单查询原参考号
                    jsO.put("amount", amount);
                    jsO.put("acqId", acqId);
                    jsO.put("expiredDate", expiredDate);
                    jsO.put("settleJson", settleData);
                    jsO.put("json", json);

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                // 需要返回数据给js
                etBack.setText(jsO.toString());
                break;
        }
    }*/


    @Override
    public void onClick(View v) {
        if (button.equals(v)) {
            webView.callHandler("functionInJs", "data from Java", new CallBackFunction() {

                @Override
                public void onCallBack(String data) {
                    // TODO Auto-generated method stub
                    Log.i(TAG, "reponse data from js " + data);
                }

            });
        }

    }

    /**
     * 下面是从assets中将图片复制到SD卡目录的参考方法 图片是黑白单色图
     */
    private void copyFile(String filename) {
        AssetManager assetManager = this.getAssets();

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

    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    getResources().getAssets().open(fileName));
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
}
