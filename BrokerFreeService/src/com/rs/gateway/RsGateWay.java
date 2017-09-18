package com.rs.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.rs.server.channel.RSChannelManager;
import com.rs.server.constants.RSConstants;
	@Path("{serviceId}/{version}")
	public class RsGateWay {
		@POST
		@Consumes(MediaType.APPLICATION_JSON +";charset=UTF-8")
		@Produces(MediaType.APPLICATION_JSON +";charset=UTF-8")
		public String process(@PathParam("serviceId")String serviceId, @PathParam("version")String version, @Context HttpServletRequest request) 
												throws  IOException,  InstantiationException, IllegalAccessException, ClassNotFoundException {
			System.out.println("path:"+request.getPathInfo());
			String path[] = request.getPathInfo().split("/");
			System.out.println(path+"Broker Free Service version 1.0"+path[1]+path[2]);
			Long startTime = new Date().getTime();
			
			HashMap<String, String> parameterMap = new HashMap<String, String>();
			Enumeration<String> parameterEnum = request.getParameterNames();
			while (parameterEnum.hasMoreElements()) {
				
				String key = (String) parameterEnum.nextElement();
				parameterMap.put(key, request.getParameter(key));
				System.out.println("key: "+key+" -- value: "+request.getParameter(key));
			}
			ServletContext servletContext = request.getServletContext();
			parameterMap.put(RSConstants.CHANNEL_ID,servletContext.getInitParameter(RSConstants.CHANNEL_ID));
			parameterMap.put(RSConstants.SERVICE_ID,path[1]);
			parameterMap.put(RSConstants.SERVICE_VERSION,path[2]);
			parameterMap.put(RSConstants.REQUEST_DATA, getRequestIputData(request));
			System.out.println(getRequestIputData(request)+"-PARAMETER MAP:"+parameterMap.values());
			if(parameterMap.get(RSConstants.REQUEST_DATA) != null){
				RSChannelManager channelManager = new RSChannelManager();
				channelManager.processRequest(parameterMap);	
			}else{
				System.out.println("serviceid null");
			}
			
			Long endTime = new Date().getTime();
			return (String) parameterMap.get(RSConstants.RESPONSE_DATA);
		}

		private String getRequestIputData(HttpServletRequest request) throws IOException {
		    StringBuilder inputData = new StringBuilder();
		    BufferedReader br = null;
		    try {
		        InputStream inputStream = request.getInputStream();
		        if (inputStream != null) {
		            br = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = br.read(charBuffer)) > 0) {
		                inputData.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            inputData.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    }
			return inputData.toString();
		}
	}
