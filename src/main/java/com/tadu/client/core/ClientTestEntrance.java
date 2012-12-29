package com.tadu.client.core;
import static java.lang.System.out;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

public class ClientTestEntrance {
	
	private static final String EXIT 		  = "exit";
	private static final String GET  		  = "1";  /**/
	private static final String SEPARATOR 	  = "/";
	private static final String QUESTION_MARK = "?";
	private static final String AND_SIGN	  = "&";	
	private static final String EQUAL_SIAGN   = "=";
	
	static{
		InputStream in = ClientTestEntrance.class.getClass().getResourceAsStream("/log4j.properties");
		PropertyConfigurator.configure(in);
	}
	
	public static final Logger logger = Logger.getLogger(ClientTestEntrance.class);
	
	public static void main(String[] args) {
		
		showInstruction();
		
		execute();
		
	}
	
	private static void execute(){
		Scanner sc = new Scanner(System.in);
		out.println("请输入服务器IP和端口(如'192.168.1.16:8080或192.168.1.16:8080/restServiceName'):");
		String serverURL = sc.nextLine();
		serverURL = "http://" + serverURL; // http://13.134.1.33:8080/
		while(true){ 
			out.println();
			sc = new Scanner(System.in);
			out.println("选择请求方法：1表示Get，其他表示Post，输入'exit'结束程序");
 			String inputParam = sc.nextLine();
 			if(inputParam.equalsIgnoreCase(EXIT)){
 				System.exit(0);
 			}
			RequestMethod method = inputParam.equals(GET) ? RequestMethod.Get : RequestMethod.Post;
			
			sc = new Scanner(System.in);
			out.println("输入" + method.toString() + "请求URI(如'/rest/read/user/getcode/')：");
			String requestURI = sc.nextLine();
			
			requestURI = requestURI.startsWith(SEPARATOR) ? requestURI : SEPARATOR  + requestURI;
			requestURI = requestURI.endsWith(SEPARATOR)   ? requestURI : requestURI + SEPARATOR;
			
			out.println("输入请求参数列表,以空格和逗号分割(如'key1 value1,key2 value2')回车结束:");
			String param = sc.nextLine().toLowerCase();
 			
			switch (method) {
				/*Get*/
				case Get:
					requestURI = (serverURL + requestURI + QUESTION_MARK + param.replaceAll(",", AND_SIGN).replaceAll(" ", EQUAL_SIAGN)).toLowerCase(); 
					out.println("本次get请求路径为:" + requestURI);
					out.println("开始请求...");
					try {
						JSONObject resGetData = ClientRequestUtil.resourceGet(requestURI);
						out.println("本次get请求服务器返回结果为：" + resGetData.toString()); 
						logger.info("get请求" + requestURI + "，服务器返回结果为：" + resGetData.toString());
					} catch (Exception e) {
						out.println("ERROR:请求" + requestURI + "异常" + e);
						logger.error("请求" + requestURI + "异常" + e);;
					}
					break;
				/*Post*/	
				case Post:
					requestURI = serverURL + requestURI;
					
					JSONObject postData = new JSONObject();
					try {
						String[] abs = param.split(",");
						for (String string : abs) {
							String[] content = string.split(" ");
							postData.put(content[0], content[1]);
						}
												
					} catch (Exception e) {
						out.println("post json参数格式不正确" + e);
						logger.error("post json参数格式不正确" + e);
					}
					try {
						out.println("本次post请求路径为:" + requestURI);
						out.println("本次post请求发送的json数据为：" + postData.toString());
						out.println("开始请求...");
						
						JSONObject resPostData = ClientRequestUtil.resourcePost(requestURI, postData);
						out.println("本次post请求服务器返回结果为：" + resPostData);
						logger.info("post请求" + requestURI + "服务器返回结果为：" + resPostData);
					} catch (Exception e) {
						out.println("ERROR:请求" + requestURI + "异常" + e);
						logger.error("请求" + requestURI + "异常" + e);;
					}
					break;
			default:
				break;
			}
			out.println();
		}
	}
	
	private static void showInstruction(){
		out.println();
		out.println("******************************使用说明*******************************");
		out.println();
		out.println("*  本脚本用于测试ZET ZXIS-RSP系列REST接口.");
		out.println("*  REST接口基于HTTP协议,请求方式通常分为 Get和Post.");
		out.println("*  在测试接口时，需要指定接口请求方式.");
		out.println("*  1.请求URI,形如/rest/read/、rest/read/、rest/read、/rest/read均可.");
		out.println("*  2.请求参数：");
		out.println("*      1.Get请求参数，  请使用标准key1 value1,key2 value2格式.");
		out.println("*          或直接输入参数长串如:'userid=001&clientid=445'");
		out.println("*      2.Post请求参数，请使用标准格式.");
		out.println("*      3.分隔符不可出现在开头和结尾，且不可连续出现两次及以上.");
		out.println("*  3.程序会记录成功的测试日志和异常日志，位于程序当前目录下.");
		out.println("*  4.程序功能未经严格测试，如有问题，请及时反馈，谢谢.");
		out.println();
		out.println("*********************************************************************");
		out.println();
	}
	
	public static void telentCheck() {
		Socket server = null;
		try {
			server = new Socket();
			InetSocketAddress address = new InetSocketAddress("119.75.218.77", 80);
			server.connect(address, 5000);
			out.println("ok!");
		} catch (UnknownHostException e) {
			out.println("wrong!");
			e.printStackTrace();
		} catch (IOException e) {
			out.println("wrong");
			e.printStackTrace();
		}

	}
}


enum RequestMethod{
	Get,
	Post,
}