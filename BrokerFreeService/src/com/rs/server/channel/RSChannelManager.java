package com.rs.server.channel;

import java.util.ArrayList;
import java.util.HashMap;

import com.rs.server.constants.RSConstants;
import com.rs.server.service.JavaService;


public class RSChannelManager {
//JSONException,
	public HashMap<String, String> processRequest(HashMap<String, String> parameterMap) throws  InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("RSChannelManager EXECUTED:"+parameterMap.values());
		String serviceId =  (String) parameterMap.get(RSConstants.SERVICE_ID);
		String serviceVersion =  (String) parameterMap.get(RSConstants.SERVICE_VERSION);
		String channelId =  (String) parameterMap.get(RSConstants.CHANNEL_ID);
		String requestData = (String) parameterMap.get(RSConstants.REQUEST_DATA);
		System.out.println("requestdata in channel manager:"+requestData);
		System.out.println("EXECUTING JAVASERVICE NAME: "+"com.rs.server.service."+serviceId);
		JavaService service = (JavaService) Class.forName("com.rs.server.service."+serviceId).newInstance();
		String responseString = service.executeService(requestData);
		
		parameterMap.put("RESPONSE_DATA", responseString);
	return parameterMap;
}

}
