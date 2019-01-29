package com.usdk.core.data;

import java.io.Serializable;

/**
 * 交易请求数据
 *
 * @author Shallow
 */
public class ResponseData extends BaseData implements Serializable {

	public static final String KEY_ERTRAS = "RESPONSE";
	/**
	 * 交易渠道
	 */
	public static final String TRANS_CHANNEL = "TRANS_CHANNEL";
	public static final String TRANS_CHANNEL_AIP = "002";
	public static final String TRANS_CHANNEL_SYB = "006";

	public static final String PRINT_FLAG = "PRINT_FLAG";


	private static final long serialVersionUID = 4466613844088756185L;

}
