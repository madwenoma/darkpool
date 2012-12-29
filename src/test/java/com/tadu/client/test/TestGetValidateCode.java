//package com.tadu.client.test;
//
//import static org.junit.Assert.*;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Test;
//
//import com.tadu.client.core.ClientRequestUtil;
//
//public class TestGetValidateCode {
//	
//	private String rest_url;
//	
//	private JSONObject requestData;
//	
//	private JSONObject responseData;
//	/**
//	 * 请求参数
//		userid	    String	用户唯一标识
//		useridtype	int	"1" --用户唯一标识类型1-手机号, 2-邮箱
//		timestamp	String	时间戳，格式：yyyyMMddHHmmss 如 20120825122500
//		clientid	String	客户端厂商id
//		passcode	String	passcode = md5(clientid +time+key) passcode 由客户端厂商id +时间戳+key 通过MD5加密后生成
//	 * @throws JSONException 
//	 */
//	//@Test
//	public void testGetValidateCode() throws JSONException{
////		rest_url 	= "/rest/read/user/getcode/3";
//		rest_url 	= "http://localhost:8080/test-maven-web-app/getcode";
//		requestData = new JSONObject();
//		requestData.put("userid", "userid-22342342");
//		requestData.put("useridtype", "");
//		requestData.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//		requestData.put("clientid", "clientid-112323");
//		requestData.put("passcode", "mad(key)");
//		responseData = ClientRequestUtil.resourcePost(rest_url, requestData);
//		
//		assertNotNull(responseData);
//		System.out.println(responseData);
//		
//	}
//	
//}
