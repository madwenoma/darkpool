package com.tadu.client.core;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.json.JSONObject;

public class ClientRequestUtil {
    
	private static final Logger logger = Logger.getLogger(ClientRequestUtil.class);
	
    public static JSONObject resourceGet(String url) {
    	
    	logger.debug("resourceGet invoked,请求url为:" + url);
    	
    	org.apache.wink.client.RestClient client 	= new org.apache.wink.client.RestClient();
        Resource resource                           = client.resource(url);
        ClientResponse response                     = resource.contentType(MediaType.APPLICATION_JSON).get();
        JSONObject resJson                             = (JSONObject) response.getEntity(JSONObject.class);
        return resJson;

    }

    public static JSONObject resourcePost(String url, JSONObject json){
    	
    	logger.debug("resourcePost invoked,请求url为:"  + url);
    	logger.debug("resourcePost invoked,请求data为:" + json.toString());
    	
        org.apache.wink.client.RestClient restclient = new org.apache.wink.client.RestClient(); 
        Resource resource                            = restclient.resource(url);
        ClientResponse response = (ClientResponse) resource.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(json);
       	return  response.getEntity(JSONObject.class);
       	
    }
}
