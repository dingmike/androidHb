package com.usdk.core.data;

import android.graphics.Bitmap;

import org.json.JSONStringer;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class BaseData implements Serializable {
	/**
	 * 业务编码
	 */
	public static final String BUSINESS_ID = "BUSINESS_ID";
	/**
	 * 字段：操作员号
	 */
	public static final String OPER_NO = "OPER_NO";
	/**
	 * 字段：卡片类型
	 */
	public static final String CARDTYPE = "CARDTYPE";
	/**
	 * 字段：卡片类型中文
	 */
	public static final String CARDTYPE_CN = "CARDTYPE_CN";
	/**
	 * 字段：交易类型
	 */
	public static final String TRANSTYPE = "TRANSTYPE";
	/**
	 * 字段：交易类型中文名
	 */
	public static final String TRANSTYPE_CN = "TRANSTYPE_CN";
	/**
	 * 字段：交易金额
	 */
	public static final String AMOUNT = "AMOUNT";

	/**
	 * 字段：优惠金额
	 */
	public static final String DISC_AMOUNT = "DISC_AMOUNT";

	/**
	 * 字段：优惠码
	 */
	public static final String COUPON_CODE = "COUPON_CODE";


	/**
	 * 字段：实付金额
	 */
	public static final String ACTUALLY_AMOUNT = "ACTUALLY_AMOUNT";


	/**
	 * 字段：原交易凭证号
	 */
	public static final String ORIG_TRACE_NO = "ORIG_TRACE_NO";
	/**
	 * 字段：原交易参考号
	 */
	public static final String ORIG_REF_NO = "ORIG_REF_NO";
	/**
	 * 字段：原交易授权号
	 */
	public static final String ORIG_AUTH_NO = "ORIG_AUTH_NO";
	/**
	 * 字段：原交易日期
	 */
	public static final String ORIG_DATE = "ORIG_DATE";
	/**
	 * 字段：卡号
	 */
	public static final String CARDNO = "CARDNO";

	/**
	 * 字段：二维码信息
	 */
	public static final String QRCODE = "QRCODE";
	/**
	 * 字段：卡片有效器
	 */
	public static final String EXP_DATE = "EXP_DATE";
	/**
	 * 字段：签购单追加打印文字
	 */
	public static final String PRINT_APPEND_TEXT = "PRINT_APPEND_TEXT";
	/**
	 * 字段：签购单追加打印图片-Bitmap格式
	 */
	public static final String PRINT_APPEND_PIC = "PRINT_APPEND_PIC";
	/**
	 * 字段：签购单追加打印联数
	 */
	public static final String PRINT_APPEND_PAGE = "PRINT_APPEND_PAGE";
	/**
	 *  字段 ：打印状态
	 */
	public static final String PRINT_VERSION = "PRINT_VERSION";

	/**
	 *  字段 ：打印内容
	 */
	public static final String PRINT_APPEND_CONTENT="PRINT_APPEND_CONTENT";
	/**
	 *  字段 ：打印字体是否为粗体
	 */
	public static final String PRINT_APPEND_BOLD = "PRINT_APPEND_BOLD";


	/**
	 *  字段 ：打印字体大小
	 */
	public static final String PRINT_APPEND_SIZE = "PRINT_APPEND_SIZE";

	/**
	 *  字段 ：打印位置
	 */
	public static final String PRINT_APPEND_LOCATE = "PRINT_APPEND_LOCATE";

	/**
	 * 字段：返回码
	 */
	public static final String REJCODE = "REJCODE";
	/**
	 * 字段：返回码中文解释
	 */
	public static final String REJCODE_CN = "REJCODE_CN";
	/**
	 * 字段：商户名称
	 */
	public static final String MERCH_NAME = "MERCH_NAME";
	/**
	 * 字段：商户编码
	 */
	public static final String MERCH_ID = "MERCH_ID";
	/**
	 * 字段：终端编码
	 */
	public static final String TER_ID = "TER_ID";
	/**
	 * 字段：发卡行中文名称
	 */
	public static final String ISS_NAME = "ISS_NAME";
	/**
	 * 字段：发卡行编号
	 */
	public static final String ISS_NO = "ISS_NO";
	/**
	 * 字段：交易日期
	 */
	public static final String DATE = "DATE";
	/**
	 * 字段：交易时间
	 */
	public static final String TIME = "TIME";
	/**
	 * 字段：批次号
	 */
	public static final String BATCH_NO = "BATCH_NO";
	/**
	 * 字段：凭证号
	 */
	public static final String TRACE_NO = "TRACE_NO";
	/**
	 * 字段：系统参考号
	 */
	public static final String REF_NO = "REF_NO";
	/**
	 * 字段：授权号
	 */
	public static final String AUTH_NO = "AUTH_NO";
	/**
	 * 字段：卡片回收标识
	 */
	public static final String CARDBACK = "CARDBACK";
	/**
	 * 字段：卡组织缩写
	 */
	public static final String CUPS = "CUPS";
	/**
	 * 字段：备注字段
	 */
	public static final String MEMO = "MEMO";
	/**
	 * 字段：交易金额总计
	 */
	public static final String TOTAL = "TOTAL";
	/**
	 * 字段：小费金额
	 */
	public static final String TIPS = "TIPS";
	/**
	 * 字段：卡片余额
	 */
	public static final String BALANCE_AMOUNT = "BALANCE_AMOUNT";
	/**
	 * 字段：交易唯一标识
	 */
	public static final String TRANS_CHECK = "TRANS_CHECK";
	/**
	 * 字段：小票交易单号
	 */
	public static final String TRANS_TICKET_NO = "TRANS_TICKET_NO";
	/**
	 * 字段：脱机交易标识
	 */
	public static final String OFFLINE_FLAG = "OFFLINE_FLAG";
	/**
	 * 字段：扫码支付-签名密钥
	 */
	public static final String QR_SIGN_KEY = "QR_SIGN_KEY";
	/**
	 * 字段：会员账号
	 */
	public static final String USER_ID = "HTTPS_USER_ID";
	/**
	 * 字段：会员序号，用于识别会员身份
	 */
	public static final String APP_ID = "APP_ID";
	/**
	 * 字段：会员序号，用于识别会员身份
	 */
	public static final String APP_KEY = "APP_KEY";
	/**
	 * 字段：交易签名，用于验证请求合法性
	 */
	public static final String SIGN = "SIGN";
	/**
	 * 字段：增值业务参数开关
	 */
	public static final String PARA_ADD = "PARA_ADD";

	/**
	 * 字段：是否打印电子发票二维码
	 */
	public static final String PRINT_ELEC_INVOICE = "PRINT_ELEC_INVOICE";

	/**
	 * 字段：订单支付编号
	 */
	public static final String ORDER_NO = "ORDER_NO";

	/**
	 * 字段：设备序列号
	 */
	public static final String SN = "SN";

	/**
	 * 字段：终端机型
	 */
	public static final String MODEL = "MODEL";

	/**
	 * 字段：终端提供商
	 */
	public static final String VENDOR = "VENDOR";

	/**
	 * 字段：入网许可证
	 */
	public static final String CERTIFICATION = "CERTIFICATION";

	/**
	 * 字段：设备服务版本
	 */
	public static final String VERSIONCODE = "VERSIONCODE";

	/**
	 * 字段：通联终端序号
	 */
	public static final String AIPSN = "AIPSN";

	/**
	 * 字段：工作模式
	 */
	public static final String WORKMODE = "WORKMODE";

	/**
	 * 字段：智收银versionCode
	 */
	public static final String CASH_VERSION_CODE = "CASH_VERSION_CODE";

	/**
	 * 字段：借贷记卡标识
	 */
	public static final String CARD_TYPE_IDENTY = "CARD_TYPE_IDENTY";

	/**
	 *  字段：是否显示身份验证结果界面
	 */
	public static final String IS_SUP_SHOW = "IS_SUP_SHOW";

	/**
	 *  字段：姓名
	 */
	public static final String NAME = "NAME";
	/**
	 *  字段：性别
	 */
	public static final String SEX = "SEX";
	/**
	 *  字段：民族
	 */
	public static final String NATION = "NATION";
	/**
	 *  字段：出生日期
	 */
	public static final String BIRTHDAY = "BIRTHDAY";
	/**
	 *  字段：是身份证号码
	 */
	public static final String IDENTITYNO = "IDENTITYNO";
	/**
	 *  字段：住址
	 */
	public static final String ADDRESS = "ADDRESS";
	/**
	 *  字段：签发机关
	 */
	public static final String AUTHORITY = "AUTHORITY";
	/**
	 *  字段：有效期限
	 */
	public static final String PERIOD = "PERIOD";
	/**
	 *  字段：DN码
	 */
	public static final String DN = "DN";
	/**
	 *  字段：证件照片
	 */
	public static final String PHOTOIMAGE = "PHOTOIMAGE";
	/**
	 *  字段：身份证反面照片
	 */
	public static final String OFFPHOTOIMAGE = "OFFPHOTOIMAGE";

	/**
	 *  字段：身份证正面照片
	 */
	public static final String ONPHOTOIMAGE = "ONPHOTOIMAGE";

	/**
	 * 字段：包含USDK所有参数的JSON串
	 */
	public static final String USDK_PARA = "USDK_PARA";

	/**
	 * 字段：扩展字段 包含子商户号等
	 */
	public static final String BUS_INFO = "BUS_INFO";

	/**
	 * 字段：具体功能
	 */
	public static final String FUNCTION = "FUNCTION";

	/**
	 * 字段：内外卡标识  0-内卡 1-外卡
	 */
	public static final String WILD_CARD_SIGN = "WILD_CARD_SIGN";


//	/**
//	 * 字段：交易单号
//	 */
//	public static final String AIP_ORDER_NO = "AIP_ORDER_NO";
//	/**
//	 * 字段：原交易单号
//	 */
//	public static final String ORIG_AIP_ORDER_NO = "ORIG_AIP_ORDER_NO";
	/**
	 * 字段：自定义字段
	 */
	public static final String PREDEF_0 = "PREDEF_0";
	public static final String PREDEF_1 = "PREDEF_1";
	public static final String PREDEF_2 = "PREDEF_2";
	public static final String PREDEF_3 = "PREDEF_3";
	public static final String PREDEF_4 = "PREDEF_4";
	public static final String PREDEF_5 = "PREDEF_5";
	public static final String PREDEF_6 = "PREDEF_6";
	public static final String PREDEF_7 = "PREDEF_7";
	public static final String PREDEF_8 = "PREDEF_8";
	public static final String PREDEF_9 = "PREDEF_9";
	public static final String PREDEF_A = "PREDEF_A";
	public static final String PREDEF_B = "PREDEF_B";
	public static final String PREDEF_C = "PREDEF_C";
	public static final String PREDEF_D = "PREDEF_D";
	public static final String PREDEF_E = "PREDEF_E";
	public static final String PREDEF_F = "PREDEF_F";
	/**
	 * 交易类型：上送电子签名
	 */
	public static final String TRANSTYPE_ESIGN = "080";
	/**
	 * BaseData序列号
	 */
	private static final long serialVersionUID = -5340290284730521989L;
	/**
	 * 卡类型：银行卡
	 */
	public static String CARDTYPE_BANKCARD = "001";
	/**
	 * 卡类型：万商通联
	 */
	public static String CARDTYPE_WSTL = "002";
	/**
	 * 卡类型：扫码支付
	 */
	public static String CARDTYPE_QRCODE = "100";
	/**
	 * 卡类型：通联钱包
	 */
	public static String CARDTYPE_AIP_WALLET = "101";
	/**
	 * 卡类型：微信支付
	 */
	public static String CARDTYPE_WXP = "102";
	/**
	 * 卡类型：支付宝钱包
	 */
	public static String CARDTYPE_ALP = "103";
	/**
	 * 卡类型：百度钱包
	 */
	public static String CARDTYPE_BDP = "104";
	/**
	 * 卡类型：主扫扫码
	 */
	public static String CARDTYPE_QRCODE_INITIATIVE = "105";
	/**
	 * 卡类型：快捷支付
	 */
	public static String CARDTYPE_QUICK_PAY = "200";
	/**
	 * 卡类型：管理功能
	 */
	public static String CARDTYPE_MANAGER = "999";
	/**
	 * 交易类型：签到
	 */
	public static String TRANSTYPE_LOGON = "001";
	/**
	 * 交易类型：消费
	 */
	public static String TRANSTYPE_SALE = "002";
	/**
	 * 交易类型：消费撤销
	 */
	public static String TRANSTYPE_VOID = "003";
	/**
	 * 交易类型：退货
	 */
	public static String TRANSTYPE_REFUND = "004";
	/**
	 * 交易类型：订单支付
	 */
	public static String TRANSTYPE_ORDERPAY = "005";
	/**
	 * 交易类型：预授权
	 */
	public static String TRANSTYPE_AUTH = "006";
	/**
	 * 交易类型：预授权撤销
	 */
	public static String TRANSTYPE_AUTH_VOID = "007";
	/**
	 * 交易类型：预授权完成
	 */
	public static String TRANSTYPE_AUTH_CM = "008";
	/**
	 * 交易类型：预授权完成通知
	 */
	public static String TRANSTYPE_AUTH_CM_OFFLINE = "010";
	/**
	 * 交易类型：预授权完成撤销
	 */
	public static String TRANSTYPE_AUTH_CM_VOID = "011";
	/**
	 * 交易类型：结算
	 */
	public static String TRANSTYPE_SETTLE = "014";
	/**
	 * 交易类型：提取交易信息
	 */
	public static String TRANSTYPE_GET_TRANS_INFO = "015";
	/**
	 * 交易类型：重打印
	 */
	public static String TRANSTYPE_REPRINT = "016";
	/**
	 * 交易类型：查询余额
	 */
	public static String TRANSTYPE_GET_BALANCE = "018";
	/**
	 * 交易类型：重打结算单
	 */
	public static String TRANSTYPE_REPRINT_SETTLE = "019";
	/**
	 * 交易类型：提取交易明细
	 */
	public static String TRANSTYPE_GET_DETAIL = "020";
	/**
	 * 交易类型：电子现金支付
	 */
	public static String TRANSTYPE_EC_SALE = "026";
	/**
	 * 交易类型：电子现金查余
	 */
	public static String TRANSTYPE_EC_BALANCE = "027";
	/**
	 * 交易类型：查询末笔交易
	 */
	public static String TRANSTYPE_QUERY_LAST = "050";
	/**
	 * 交易类型：打印交易汇总
	 */
	public static String TRANSTYPE_PRINT_TOTAL = "095";
	/**
	 * 交易类型：打印交易明细
	 */
	public static String TRANSTYPE_PRINT_DETAIL = "096";
	/**
	 * 交易类型：提取二磁道卡号信息
	 */
	public static String TRANSTYPE_GET_CARDNO = "098";
	/**
	 * 交易类型：下载终端参数
	 */
	public static String TRANSTYPE_LOAD_TEMPARA = "125";
	/**
	 * 交易类型：下载终端主密钥
	 */
	public static String TRANSTYPE_LOAD_MKEY = "126";
	/**
	 * 交易类型：更新卡bin表
	 */
	public static String TRANSTYPE_LOAD_BIN = "127";
//	/**
//	 * 交易类型：下载终端参数
//	 */
//	public static String TRANSTYPE_LOAD_PARA = "128";
	/**
	 * 交易类型：下载公钥
	 */
	public static String TRANSTYPE_LOAD_CPK = "129";
	/**
	 * 交易类型：下载IC参数
	 */
	public static String TRANSTYPE_LOAD_AID = "130";
	/**
	 * 交易类型：打印签购单并附加内容
	 */
	public static String TRANSTYPE_APPEND_PAPER = "131";
	/**
	 * 交易类型：调用打印机打印内容
	 */
	public static String TRANSTYPE_PRINT_PAPER = "132";
	/**
	 * 交易类型：下载非接参数
	 */
	public static String TRANSTYPE_LOAD_QPBOC = "133";
	/**
	 * 交易类型：通联钱包消费
	 */
	public static String TRANSTYPE_AIPWALLET_SALE = "200";
	/**
	 * 交易类型：通联钱包撤销
	 */
	public static String TRANSTYPE_AIPWALLET_VOID = "201";
	/**
	 * 冲正
	 */
	public static String TRANSTYPE_REVERSE = "800";
	/**
	 * 交易类型：显示交易记录
	 */
	public static String TRANSTYPE_SHOW_TRANS = "998";
	/**
	 * 交易类型：未知交易
	 */
	public static String TRANSTYPE_UNKNOWN = "999";
	/**
	 * 交易类型：参数设置
	 */
	public static String TRANSTYPE_CONFIG_PARA = "001";
	/**
	 * 交易类型：检查更新
	 */
	public static String TRANSTYPE_CHECK_UPDATE = "002";

	private HashMap<String, Object> paraMap = new HashMap<String, Object>();

	/**
	 * 获取指定字段的值
	 *
	 * @param key 字段
	 * @return 字 值
	 */
	public String getValue(String key) {
		key = key.toUpperCase();
		if (!paraMap.containsKey(key)) {
			return "";
		}
		return paraMap.get(key).toString();
	}

	/**
	 * 获取指定字段的图片信息
	 *
	 * @param key 字段
	 * @return 对应 图片信息
	 */
	public byte[] getByte(String key) {
		byte[] b = null;
		key = key.toUpperCase();
		if (!paraMap.containsKey(key)) {
			return null;
		}
		Object obj = paraMap.get(key);
		if (obj instanceof byte[]) {
			return (byte[])obj;
		} else {
			try {
				b = str2Bcd((String) obj);
			} catch (Exception e) {
			}
		}
		return b;

	}
	/**
	 * @功能: 10进制串转为BCD码
	 * @参数: 10进制串
	 * @结果: BCD码
	 */
	public  byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}
	/**
	 * 向指定字段赋值
	 *
	 * @param key   字段
	 * @param value 字段值
	 */
	public void putValue(String key, Object value) {
		key = key.toUpperCase();
		if (value instanceof Integer) {
			paraMap.put(key, value);
		} else if (value instanceof String) {
			paraMap.put(key, value);
		} else if (value instanceof Bitmap) {
			try {
				// Bitmap转换成byte[]
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				((Bitmap) value).compress(Bitmap.CompressFormat.PNG, 0, baos);// 压缩位图
				byte [] compressImag = baos.toByteArray();
				paraMap.put(key, compressImag);// 创建分配字节数组
			} catch (Exception e) {
				// paraMap.put(key, value);
			}
		} else if (value instanceof byte[]) {
			paraMap.put(key, value);
		}else if(value instanceof List){
			paraMap.put(key, value);
		}else  if(value instanceof JSONStringer){
			paraMap.put(key, value);
		}
	}

	public HashMap<String, Object> getMap() {
		return paraMap;
	}

	/**
	 * 获取指定字段的值
	 *
	 * @param key 字段
	 * @return 字 值
	 */
	public Object getObjValue(String key) {
		key = key.toUpperCase();
		if (!paraMap.containsKey(key)) {
			return null;
		}
		return paraMap.get(key);
	}
}
