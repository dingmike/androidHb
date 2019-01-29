/*
package com.HBuilder.integrate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.HBuilder.integrate.util.GlobalData;
import com.fuyousf.android.fuious.service.MOneSupportService;
import com.fuyousf.android.fuious.service.PrintInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {

	private MOneSupportService stuService = null;
	private PrintInterface printService = null;
	private RFReceiver rfReceiver ;
	private PrintReceiver printReceiver ;
	private EditText etBack;
	
	private Button[] bt;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);


		//				获取開始-------
		Intent intent18 = new Intent();
		intent18.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

		//包名     包名+类名（全路径）
		intent18.putExtra("transName", "终端参数");

		startActivityForResult(intent18, 0);
//				获取结束-------

//		setResult(RESULT_OK, intent18);
//		finish();


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 获取打开页面返回的数据
		super.onActivityResult(requestCode, resultCode, data);

		GlobalData.dataIntent = data;

//		Intent intent = new Intent();
//		data.putExtra("data_return", "Hello A");
//		setResult(RESULT_OK, data);
//		finish();

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


//			etBack.setText(jsO.toString());
			GlobalData.dataString = jsO.toString();

			Intent intent32=new Intent();
//            intent32.setClass(this.getDPluginContext(), DemoActivity.class);
			intent32.setClass(this.getApplicationContext(),  GlobalData.globalActivity.getClass());
			this.startActivity(intent32);
			finish();
			break;
		}
	}


    
*/
/*    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if(null != rfReceiver){
    		unregisterReceiver(rfReceiver);
    	}
    	if(null != printReceiver){
    		unregisterReceiver(printReceiver);
    	}
    	unbindService(serviceConnection);
    	unbindService(printServiceConnection);
    }*//*

	     
    class RFReceiver extends BroadcastReceiver {

		@Override
        public void onReceive(Context context, Intent intent) {
            String status = intent.getStringExtra("status");
            String result = intent.getStringExtra("result");
            etBack.setText("状态："+status+"--|结果："+result);
        }
    }
    
	class PrintReceiver extends BroadcastReceiver {
	
		@Override
	   public void onReceive(Context context, Intent intent) {
	       String result = intent.getStringExtra("result");
	       etBack.setText("reason："+result);
	   }
	}
}
*/


package com.HBuilder.integrate;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.HBuilder.integrate.util.GlobalData;

import com.example.H5PlusPlugin.PrintUtils;
import com.fuyousf.android.fuious.service.MOneSupportService;
import com.fuyousf.android.fuious.service.PrintInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

@SuppressLint("ShowToast")
public class MainActivity extends Activity{

	private MOneSupportService stuService = null;
	private PrintInterface printService = null;
	private RFReceiver rfReceiver ;
	private PrintReceiver printReceiver ;
	private EditText etBack;

	/*private int [] idR = new int[]{R.id.button32,R.id.button222,R.id.button1,R.id.button2,R.id.button3,R.id.button4,
			R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,R.id.button10,R.id.button11,
			R.id.button12,R.id.button13,R.id.button14,R.id.button15,R.id.button16,R.id.button17,R.id.button18,
			R.id.button19,R.id.button20,R.id.button21,R.id.button22,R.id.button23,R.id.button24,R.id.button25,
			R.id.button26,R.id.button27,R.id.button28,R.id.button29,R.id.button30,R.id.button31};*/

//	private Button[] bt;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

//		etBack = (EditText) findViewById(R.id.transData);//返回数据
		//进入就初始化数据
//		bt = new Button[idR.length];
//		for(int i=0;i<idR.length;i++){
//			bt[i] = (Button) findViewById(idR[i]);
//			bt[i].setOnClickListener(this);
//		}

		initService();
		initPrintService();

		this.goNativeApi(GlobalData.callMethodId);


	}

//	@Override
	public void goNativeApi(Integer methodId) {
		/*if(!TextUtils.isEmpty(etBack.getText().toString())){
			etBack.setText("");
		}*/
		switch (methodId) {
			// 测试webview打卡网页
			case 1000:
				Intent intent32=new Intent();
				intent32.setClass(this, DemoActivity.class);
				startActivity(intent32);
				break;
			//签到
			case 1001:
				Intent intent222=new Intent();
				intent222.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
				//包名     包名+类名（全路径）
				intent222.putExtra("transName", "签到");
				startActivityForResult(intent222, 0);
				break;
			//自定义打印
			case 1002:
				copyFile("pay.bmp");
				Intent intent=new Intent();
				intent.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.CustomPrinterActivity"));
				//包名     包名+类名（全路径）
				String str = getFromAssets("json.txt");
				intent.putExtra("data", str);
				intent.putExtra("isPrintTicket", "true");
				startActivityForResult(intent, 99);
				break;
			//结算
			case 1003:
				//copyFile("pay.bmp");

				Intent intent2=new Intent();
				intent2.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
				//包名     包名+类名（全路径）
				intent2.putExtra("transName", "微信银行卡结算");
				intent2.putExtra("isPrintSettleTicket", "false");
				startActivityForResult(intent2, 0);
				break;
			//签退
			case 1004:

				Intent intent3=new Intent();
				intent3.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
				//包名     包名+类名（全路径）
				intent3.putExtra("isPrintTicket", "true");
				intent3.putExtra("transName", "签退");
				startActivityForResult(intent3, 0);
				break;
			//消费
//			case R.id.button4:
//				Intent intent4 = new Intent();
//				intent4.setClass(this, SaleBackActivity.class);
//				intent4.putExtra("flag", "消费");
//				intent4.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				intent4.putExtra("oldNo", "false");//是否传原凭证号  true-传  false-不传
//				startActivity(intent4);
//				break;
//			//支付宝消费
//			case R.id.button5:
//				Intent intent5 = new Intent();
//				intent5.setClass(this, SaleBackActivity.class);
//				intent5.putExtra("flag", "支付宝消费");
//				intent5.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				intent5.putExtra("oldNo", "false");//是否传原凭证号  true-传  false-不传
//				startActivity(intent5);
//
//				break;
//			case R.id.button6:
//				//支付宝退款
//				Intent intent6 = new Intent();
//				intent6.setClass(this, SaleBackActivity.class);
//				intent6.putExtra("flag", "支付宝退款");
//				intent6.putExtra("oldNo", "true");//是否传原证号  true-传  false-不传
//				intent6.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				startActivity(intent6);
//				break;
//			//支付宝末笔查询
//			case R.id.button7:
//
//				Intent intent7 = new Intent();
//				intent7.setClass(this, SaleBackActivity.class);
//				intent7.putExtra("flag", "支付宝末笔查询");
//				intent7.putExtra("oldNo", "false");//是否传原证号  true-传  false-不传
//				intent7.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				startActivity(intent7);
//				break;
//			case R.id.button8:
//				//消费撤销
//				Intent intent8 = new Intent();
//				intent8.setClass(this, SaleBackActivity.class);
//				intent8.putExtra("flag", "消费撤销");
//				intent8.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				intent8.putExtra("oldNo", "true");//是否传原凭证号  true-传  false-不传
//				startActivity(intent8);
//				break;
//			//退货
//			case R.id.button9:
//				Intent intent9 = new Intent();
//				intent9.setClass(this, SaleBackActivity.class);
//				intent9.putExtra("flag", "退货");
//				intent9.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				intent9.putExtra("oldNo", "false");//是否传原凭证号  true-传  false-不传
//				startActivity(intent9);
//				break;
//			//微信消费
//			case R.id.button10:
//				Intent intent10 = new Intent();
//				intent10.setClass(this, SaleBackActivity.class);
//				intent10.putExtra("flag", "微信消费");
//				intent10.putExtra("oldNo", "false");//是否传原证号  true-传  false-不传
//				intent10.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				startActivity(intent10);
//				break;
//			case R.id.button11:
//				//微信退款
//				Intent intent11 = new Intent();
//				intent11.setClass(this, SaleBackActivity.class);
//				intent11.putExtra("flag", "微信退款");
//				intent11.putExtra("oldNo", "true");//是否传原证号  true-传  false-不传
//				intent11.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				startActivity(intent11);
//				break;
//			//微信末笔查询
//			case R.id.button12:
//
//				Intent intent12 = new Intent();
//				intent12.setClass(this, SaleBackActivity.class);
//				intent12.putExtra("flag", "微信末笔查询");
//				intent12.putExtra("oldNo", "false");//是否传原证号  true-传  false-不传
//				intent12.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
//				startActivity(intent12);
//				break;
			//余额查询
			case 1005:
				Intent intent13 = new Intent();
				intent13.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				intent13.putExtra("transName", "余额查询");
				startActivityForResult(intent13, 0);
				break;
			//交易查询
			case 1006:
				Intent intent14 = new Intent();
				intent14.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				intent14.putExtra("transName", "查询数据");
				startActivityForResult(intent14, 0);
				break;
//			//打印任意一笔
//			case R.id.button16:
//				Intent intent16 = new Intent();
//				intent16.setClass(this, PrintActivity.class);
//				startActivity(intent16);
//				break;
			//打印最后一笔
			case 1007:
				Intent intent23 = new Intent();
				intent23.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				intent23.putExtra("transName", "打印最后一笔");
				startActivityForResult(intent23, 0);
				break;
			//系统管理
			case 1008:
				Intent intent17 = new Intent();
				intent17.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				intent17.putExtra("transName", "系统管理");
				startActivityForResult(intent17, 0);
				break;
			//终端参数
			case 1009:
				Intent intent18 = new Intent();
				intent18.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				intent18.putExtra("transName", "终端参数");
				startActivityForResult(intent18, 0);
				break;
			//预授权
			case 1010:
				Intent intent19 = new Intent();
				intent19.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				Bundle bundle = new Bundle();
				intent19.putExtra("transName", "预授权");
				startActivityForResult(intent19, 0);
				break;
			//预授权撤销
			case 1011:
				Intent intent20 = new Intent();
				intent20.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				intent20.putExtra("transName", "预授权撤销");
				startActivityForResult(intent20, 0);
				break;
			//预授权请求完成
			case 1012:
				Intent intent21 = new Intent();
				intent21.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				intent21.putExtra("transName", "预授权完成（请求）");
				startActivityForResult(intent21, 0);
				break;
			//预授权完成撤销
			case 1013:
				Intent intent22 = new Intent();
				intent22.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

				//包名     包名+类名（全路径）
				intent22.putExtra("transName", "预授权完成撤销");
				startActivityForResult(intent22, 0);
				break;
			//M1读取
			case 1014:
				//底层调用传入值，开始读取卡

				if(null != stuService){
					byte[] keyA = hexString2Bytes("FFFFFFFFFFFF");
					try {
						stuService.readBlock(1, 2, keyA);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				break;
			//二维码扫码
			case 1015:
				Intent intent25 = new Intent();
				intent25.setComponent(new ComponentName("com.fuyousf.android.fuious",
						"com.fuyousf.android.fuious.NewSetScanCodeActivity"));
				intent25.putExtra("flag", "true");
				startActivityForResult(intent25, 0);
				break;
			//订单号查询
			case 1016:
				Intent intent26 = new Intent();
				intent26.setClass(this, SaleBackActivity.class);
				intent26.putExtra("flag", "订单号查询");
				intent26.putExtra("oldNo", "false");//是否传原证号  true-传  false-不传
				intent26.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
				startActivity(intent26);
				break;
			//联机查询
			case 1017:
				Intent button27 = new Intent();
				button27.setClass(this, SaleBackActivity.class);
				button27.putExtra("flag", "单笔查询");
				button27.putExtra("oldNo", "true");//是否传原证号  true-传  false-不传
				button27.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
				startActivity(button27);
				break;
			//消费末笔查询
			case 1018:
				Intent intent28 = new Intent();
				intent28.setClass(this, SaleBackActivity.class);
				intent28.putExtra("flag", "消费末笔查询");
				intent28.putExtra("oldNo", "false");//是否传原证号  true-传  false-不传
				intent28.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
				startActivity(intent28);
				break;
			//新自定义打印
			case 1019:
				if(null != printService){
					String jsonDate = printDetail();
					Log.i("TAG", "json："+jsonDate);
					try {
						printService.print(jsonDate);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				break;
			case 1020:
				Intent intent30 = new Intent();
				intent30.setClass(this, SaleBackActivity.class);
				intent30.putExtra("flag", "联机失败查询");
				intent30.putExtra("oldNo", "true");//是否传原证号  true-传  false-不传
				intent30.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
				startActivity(intent30);
				break;

			case 1021:
				Intent intent31 = new Intent();
				intent31.setClass(this, SaleBackActivity.class);
				intent31.putExtra("flag", "打印任意一笔（联机）");
				intent31.putExtra("oldNo", "true");//是否传原证号  true-传  false-不传
				intent31.putExtra("orderNo", "true");//是否传订单号  true-传  false-不传
				intent31.putExtra("date", "true");//日期
				startActivity(intent31);
				break;
		}
	}

	/**
	 * 下面是从assets中将图片复制到SD卡目录的参考方法 图片是黑白单色图
	 * */
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 获取打开页面返回的数据
		super.onActivityResult(requestCode, resultCode, data);
		GlobalData.dataIntent = data;

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
//				etBack.setText(jsO.toString());

				GlobalData.dataString = jsO.toString();

				Intent intent32=new Intent();
//            intent32.setClass(this.getDPluginContext(), DemoActivity.class);
				intent32.setClass(this.getApplicationContext(),  GlobalData.globalActivity.getClass());
				this.startActivity(intent32);
				finish();

				break;
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

	//绑定服务，注册广播
	private void initService(){
		registerReceiver();
		Intent terminalIntent = new Intent(/*"com.fuyousf.android.fuious.service.MOneSupportService"*/);
		terminalIntent.setAction("com.fuyousf.android.fuious.service.MOneSupportService");
		terminalIntent.setPackage("com.fuyousf.android.fuious");
		bindService(terminalIntent, serviceConnection, Context.BIND_AUTO_CREATE);

	}

	private void initPrintService(){
		printRegisterReceiver();
		Intent printIntent = new Intent(/*"com.fuyousf.android.fuious.service.PrintInterface"*/);
		printIntent.setAction("com.fuyousf.android.fuious.service.PrintInterface");
		printIntent.setPackage("com.fuyousf.android.fuious");
		bindService(printIntent, printServiceConnection, Context.BIND_AUTO_CREATE);
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

	//广播祖册
	private void registerReceiver(){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.fuyousf.android.fuious.service");
		rfReceiver = new RFReceiver();
		registerReceiver(rfReceiver, intentFilter);
	}

	private void printRegisterReceiver(){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.fuyousf.android.fuious.service.print");
		printReceiver = new PrintReceiver();
		registerReceiver(printReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(null != rfReceiver){
			unregisterReceiver(rfReceiver);
		}
		if(null != printReceiver){
			unregisterReceiver(printReceiver);
		}
		unbindService(serviceConnection);
		unbindService(printServiceConnection);
	}

	class RFReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String status = intent.getStringExtra("status");
			String result = intent.getStringExtra("result");
			etBack.setText("状态："+status+"--|结果："+result);
		}
	}

	class PrintReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String result = intent.getStringExtra("result");
			etBack.setText("reason："+result);
		}
	}


	public static byte[] hexString2Bytes(String data)
	{
		byte[] result = new byte[(data.length() + 1) / 2];
		if ((data.length() & 0x1) == 1) {
			data = data + "0";
		}
		for (int i = 0; i < result.length; ++i) {
			result[i] = (byte)(hex2byte(data.charAt(i * 2 + 1)) | hex2byte(data.charAt(i * 2)) << 4);
		}
		return result;
	}


	public static byte hex2byte(char hex)
	{
		if ((hex <= 'f') && (hex >= 'a')) {
			return (byte)(hex - 'a' + 10);
		}

		if ((hex <= 'F') && (hex >= 'A')) {
			return (byte)(hex - 'A' + 10);
		}

		if ((hex <= '9') && (hex >= '0')) {
			return (byte)(hex - '0');
		}

		return 0;
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


}
