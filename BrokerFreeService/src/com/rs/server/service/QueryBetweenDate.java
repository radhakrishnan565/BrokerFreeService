package com.rs.server.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rs.dao.util.MongoConnectionUtil;

public class QueryBetweenDate extends JavaService{

	@Override
	public String executeService(String requestData) {
		try {
			JSONObject object = new JSONObject("{}");
			object.getClass();
			JSONArray jsonList = new JSONArray();
			MongoConnectionUtil.getClient();
		} catch (JSONException e) {
			System.out.println("Exception Occured while using json objects..");
			e.printStackTrace();
		}
		
		return "Cool your web service worked correctly...........";
	}

}
