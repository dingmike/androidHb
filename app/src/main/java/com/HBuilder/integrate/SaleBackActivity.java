package com.HBuilder.integrate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@SuppressLint("NewApi")
public class SaleBackActivity extends Activity {
	private EditText et1 ;
	private EditText et2 ;
	private EditText et3 ;
	private EditText et4 ;
	private EditText etBack;
	private LinearLayout spinner_layout;
	private LinearLayout date;
	private Spinner spinner_type;
	private TextView text_choice_date;
	private View view;
	private LinearLayout spinner_layout2;
	private Spinner spinner_type2;
	
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private String transType = "";//传递过去的交易类型
    
    
	int mYear, mMonth, mDay;
	   //获取日期格式器对象
	DateFormat fmtDate = new java.text.SimpleDateFormat("yyyyMMdd");
	DateFormat fmtTime = new java.text.SimpleDateFormat("HHmmss");
	  //获取一个日历对象
	Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
 
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sale_back);
		
		etBack = (EditText) findViewById(R.id.transData);
		
		final String flag= getIntent().getExtras().getString("flag");
		final String oldNo = getIntent().getExtras().getString("oldNo");
		final String orderNo = getIntent().getExtras().getString("orderNo");
		final String dateFlag = getIntent().getExtras().getString("date");
		
		Log.i("TAG", "flag:"+flag+"--|-"+"oldNo:"+oldNo+"--|-"+"orderNo:"+orderNo);
		
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
		et4  = (EditText) findViewById(R.id.editText4);//请输入原订单号
		spinner_layout = (LinearLayout) findViewById(R.id.spinner_layout);
		spinner_type = (Spinner) findViewById(R.id.spinner_type);
		
		spinner_layout2 = (LinearLayout) findViewById(R.id.spinner_layout2);
		spinner_type2 = (Spinner) findViewById(R.id.spinner_type2);
		view = findViewById(R.id.view);
		date = (LinearLayout) findViewById(R.id.date);
		text_choice_date = (TextView) findViewById(R.id.text_choice_date);
		
		if(!"消费撤销".equals(flag)){
			et1.setVisibility(View.GONE);
		}else{
			et1.setVisibility(View.VISIBLE);
		}
		//是否传原凭证号
		if("true".equals(oldNo)){
			et2.setVisibility(View.VISIBLE);
		}else{
			et2.setVisibility(View.GONE);
		}
		//是否传原订单号
		if("true".equals(orderNo)){
			et3.setVisibility(View.VISIBLE);
		}else{
			et3.setVisibility(View.GONE);
		}
		
		if("true".equals(dateFlag)){
			date.setVisibility(View.VISIBLE);
		}else{
			date.setVisibility(View.GONE);
		}
	
		if("打印任意一笔（联机）".equals(flag)){
			spinner_layout2.setVisibility(View.VISIBLE);
			view.setVisibility(View.VISIBLE);
			list.add("银行卡消费");
			list.add("微信消费");
			list.add("支付宝消费");
			
			adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner_type2.setAdapter(adapter); 
			transType = adapter.getItem(0);//获取默认值
			spinner_type2.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view,
                                           int position, long arg3) {
					transType = adapter.getItem(position);
					adapterView.setVisibility(View.VISIBLE);
					Log.i("TAG", "spinner选择的是："+adapter.getItem(position));
				}

				@Override
				public void onNothingSelected(AdapterView<?> adapterView) {
					adapterView.setVisibility(View.VISIBLE);
				}
			});
		}else{
			spinner_layout2.setVisibility(View.GONE);
			view.setVisibility(View.GONE);
		}
		
		if("单笔查询".equals(flag) || "联机失败查询".equals(flag)){
			spinner_layout.setVisibility(View.VISIBLE);
			view.setVisibility(View.VISIBLE);
			list.add("消费失败查询");
			list.add("微信失败查询");
			list.add("支付宝失败查询");
			
			adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner_type.setAdapter(adapter); 
			transType = adapter.getItem(0);//获取默认值
			spinner_type.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view,
                                           int position, long arg3) {
					transType = adapter.getItem(position);
					adapterView.setVisibility(View.VISIBLE);
					Log.i("TAG", "spinner选择的是："+adapter.getItem(position));
				}

				@Override
				public void onNothingSelected(AdapterView<?> adapterView) {
					adapterView.setVisibility(View.VISIBLE);
				}
			});
		}else{
			spinner_layout.setVisibility(View.GONE);
			view.setVisibility(View.GONE);
		}
		
		
		Button bt = (Button) findViewById(R.id.buttonSure);
		
		
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				check(flag,oldNo,orderNo);
			}
		});
		
		initDate();
		text_choice_date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				getDate();
			}
		});
	}
	
	private void initDate(){
		//获取年月日
		final Calendar ca = Calendar.getInstance();
		mYear = ca.get(Calendar.YEAR);
		mMonth = ca.get(Calendar.MONTH);
		mDay = ca.get(Calendar.DAY_OF_MONTH);
		
	}
	
	private void getDate(){
		//自定义的日期选择框
	 	AlertDialog.Builder builder = new AlertDialog.Builder(SaleBackActivity.this, AlertDialog.THEME_HOLO_LIGHT);
	 	View view = (LinearLayout) getLayoutInflater().inflate(R.layout.date_dialog, null);
	 	final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
		datePicker.setCalendarViewShown(false);	
//		dateAndTime.setTimeInMillis(System.currentTimeMillis());
		datePicker.init(dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH),
				dateAndTime.get(Calendar.DAY_OF_MONTH), null);
		//设置date布局
		builder.setView(view);
		builder.setTitle("设置日期信息");
		builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
            		datePicker.clearFocus();
                	//将页面TextView的显示更新为最新时间
        			dateAndTime.set(Calendar.YEAR,datePicker.getYear());
					dateAndTime.set(Calendar.MONTH,datePicker.getMonth());
					dateAndTime.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
					updateBeginDate(); 
                    dialog.cancel(); 
                } 
        }); 
		builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	dialog.cancel(); 
            }
		});
		builder.create().show();
	}
	
	private void updateBeginDate() {
	    	text_choice_date.setText(fmtDate.format(dateAndTime.getTime()));
		}
	
	private void check(String str, String oldNo, String orderNo){
		if(!TextUtils.isEmpty(str)){
			if("消费撤销".equals(str)){
				if(!TextUtils.isEmpty(et1.getText().toString().trim()) &&
						!TextUtils.isEmpty(et2.getText().toString().trim())){
					Intent intent8 = new Intent();
					intent8.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
					//包名     包名+类名（全路径）  
					intent8.putExtra("transName", "消费撤销");
					intent8.putExtra("amount", et1.getText().toString().trim());
					intent8.putExtra("oldTrace", et2.getText().toString().trim());
					intent8.putExtra("isManagePwd", "false");
					if("true".equals(orderNo)){
						intent8.putExtra("orderNumber",  et3.getText().toString().trim());
					}
					if(!TextUtils.isEmpty(et4.getText().toString().trim())){
						intent8.putExtra("version", et4.getText().toString().trim());
					}
					
					startActivityForResult(intent8, 0);
				}else{
					Toast.makeText(SaleBackActivity.this, "请输入消费金额和原凭证号", Toast.LENGTH_SHORT).show();
				}
				
			}else if("微信退款".equals(str)){
					Intent intent11 = new Intent();
					intent11.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

					//包名     包名+类名（全路径）  
					intent11.putExtra("transName", "微信退款");
					if("true".equals(oldNo)){
						intent11.putExtra("oldTrace", et2.getText().toString().trim());
					}
					intent11.putExtra("isPrintTicket", "true");
					if("true".equals(orderNo)){
						intent11.putExtra("orderNumber", et3.getText().toString().trim());
					}
					if(!TextUtils.isEmpty(et4.getText().toString().trim())){
						intent11.putExtra("version", et4.getText().toString().trim());
					}
					startActivityForResult(intent11, 0);
				
			}else if("支付宝退款".equals(str)){
					Intent intent6 = new Intent();
					intent6.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));

					//包名     包名+类名（全路径）  
					intent6.putExtra("transName", "支付宝退款");
					intent6.putExtra("isPrintTicket", "true");
					if("true".equals(orderNo)){
						intent6.putExtra("orderNumber",  et3.getText().toString().trim());
					}
					if("true".equals(oldNo)){
						intent6.putExtra("oldTrace", et2.getText().toString().trim());//原凭证号
					}
					if(!TextUtils.isEmpty(et4.getText().toString().trim())){
						intent6.putExtra("version", et4.getText().toString().trim());
					}
					startActivityForResult(intent6, 0);
			}else if("微信末笔查询".equals(str)){
				Intent intent12 = new Intent();
				intent12.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
	
				//包名     包名+类名（全路径）  
				intent12.putExtra("transName", "微信末笔查询");
				intent12.putExtra("isPrintTicket", "true");
				if("true".equals(orderNo)){
					intent12.putExtra("orderNumber",  et3.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent12.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent12, 0);
			}else if("支付宝末笔查询".equals(str)){
				Intent intent7 = new Intent();
				intent7.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
	
				//包名     包名+类名（全路径）  
				intent7.putExtra("transName", "支付宝末笔查询");
				intent7.putExtra("isPrintTicket", "true");
				if("true".equals(orderNo)){
					intent7.putExtra("orderNumber",  et3.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent7.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent7, 0);
			}else if("退货".equals(str)){
				Intent intent9 = new Intent();
				intent9.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
	
				//包名     包名+类名（全路径）  
				intent9.putExtra("transName", "退货");
				intent9.putExtra("amount", "000000000001");
				if("true".equals(orderNo)){
					intent9.putExtra("orderNumber",  et3.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent9.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent9, 0);
			}else if("消费".equals(str)){
				Intent intent4 = new Intent();
				intent4.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
	
				//包名     包名+类名（全路径）  
				intent4.putExtra("amount", "000000000001");
				intent4.putExtra("transName", "消费");
				if("true".equals(orderNo)){
					intent4.putExtra("orderNumber", et3.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent4.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent4, 0);
			}else if("微信消费".equals(str)){
				Intent intent10 = new Intent();
				intent10.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
	
				//包名     包名+类名（全路径）  
				intent10.putExtra("transName", "微信消费");
				intent10.putExtra("amount", "000000000001");
				intent10.putExtra("isPrintTicket", "true");
				if("true".equals(orderNo)){
					intent10.putExtra("orderNumber", et3.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent10.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent10, 0);
			}else if("支付宝消费".equals(str)){
				Intent intent5 = new Intent();
				intent5.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
	
				//包名     包名+类名（全路径）  
				intent5.putExtra("amount", "000000000001");
				intent5.putExtra("transName", "支付宝消费");
				intent5.putExtra("isPrintTicket", "true");
				if("true".equals(orderNo)){
					intent5.putExtra("orderNumber", et3.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent5.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent5, 0);
			}else if("订单号查询".equals(str)){
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
	
				//包名     包名+类名（全路径）  
				intent.putExtra("transName", "订单号查询");
				intent.putExtra("isPrintTicket", "true");
				if("true".equals(orderNo)){
					intent.putExtra("orderNumber", et3.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent, 0);
			}else if("联机查询".equals(str)){
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
	
				//包名     包名+类名（全路径）  
				Log.i("TAG", "====传递过去的交易名是："+transType);
				intent.putExtra("transName", transType);
				intent.putExtra("isPrintTicket", "true");
				//订单号
				if("true".equals(orderNo)){
					intent.putExtra("orderNumber", et3.getText().toString().trim());
				}
				//凭证号
				if("true".equals(oldNo)){
					intent.putExtra("oldTrace", et2.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent.putExtra("version", et4.getText().toString().trim());
				}else{
					Toast.makeText(this, "请输入版本号！", Toast.LENGTH_SHORT);
				}
				startActivityForResult(intent, 0);
			}else if("消费末笔查询".equals(str)){
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
				//包名     包名+类名（全路径）  
				intent.putExtra("transName", "消费末笔查询");
				intent.putExtra("isPrintTicket", "true");
				//订单号
				if("true".equals(orderNo)){
					intent.putExtra("orderNumber", et3.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent, 0);
			}else if("联机失败查询".equals(str)){
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
				//包名     包名+类名（全路径）  
				intent.putExtra("transName", transType);
				intent.putExtra("isPrintTicket", "true");
				//订单号
				if("true".equals(orderNo)){
					intent.putExtra("orderNumber", et3.getText().toString().trim());
				}
				//凭证号
				if("true".equals(oldNo)){
					intent.putExtra("oldTrace", et2.getText().toString().trim());
				}
				if(!TextUtils.isEmpty(et4.getText().toString().trim())){
					intent.putExtra("version", et4.getText().toString().trim());
				}
				startActivityForResult(intent, 0);
			}else if("打印任意一笔（联机）".equals(str)){
				
				if("银行卡消费".equals(transType)){
					gotoActivty("联机消费查询",text_choice_date.getText().toString(),et2.getText().toString().trim(),"OC",et4.getText().toString().trim());
				}else if("微信消费".equals(transType)){
					gotoActivty("联机微信查询",text_choice_date.getText().toString(),et2.getText().toString().trim(),"OW",et4.getText().toString().trim());
				}else if("支付宝消费".equals(transType)){
					gotoActivty("联机支付宝查询",text_choice_date.getText().toString(),et2.getText().toString().trim(),"OZ",et4.getText().toString().trim());
				}
			}
		}
		
	}
	
	private void gotoActivty(String transName, String date, String orderNo, String status, String version){
		Intent intent = new Intent();
		intent.setComponent(new ComponentName("com.fuyousf.android.fuious","com.fuyousf.android.fuious.MainActivity"));
		//包名     包名+类名（全路径）  
		intent.putExtra("transName", transName);
		if(date.length()>4){
			date = date.substring(4, date.length());
			Log.i("TAG","日期："+date);
		}
		intent.putExtra("transDate", date);
		//自动不零
		if(orderNo.length()<6){
			StringBuilder sb = new StringBuilder();
			int i = 6-orderNo.length();
			for(int j=0;j<i;j++){
				sb.append("0");
			}
			orderNo = sb.append(orderNo).toString();
			Log.i("TAG", "拼接后的数据："+orderNo);
		}
		intent.putExtra("transOrderNo", orderNo);
		intent.putExtra("transStatus", status);
		intent.putExtra("version", version);
		if(!TextUtils.isEmpty(et4.getText().toString().trim())){
			intent.putExtra("version", et4.getText().toString().trim());
		}
		//订单号
		if(!TextUtils.isEmpty(et3.getText().toString().trim())){
			intent.putExtra("orderNumber", et3.getText().toString().trim());
		}
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
		case Activity.RESULT_OK:
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
			
			String orderNo = data.getStringExtra("orderNo");//支付宝微信返回的订单号
			
			String oldTrace = data.getStringExtra("oldTrace");//撤销交易返回的原凭证号

			JSONObject jsO = new JSONObject();
			try {
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
				jsO.put("orderNo", orderNo);
				jsO.put("oldTrace", oldTrace);
				jsO.put("settleJson", settleData);
				jsO.put("json", json);
				
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
			etBack.setText(jsO.toString());
			break;
		}
	}
}
