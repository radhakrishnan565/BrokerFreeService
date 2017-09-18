package com.rs.dao.util;

public class MIMExtensions {
	
	public  static String getcontenetType( String  extention){
		
		String contentType= null ;
		
		if(extention.equals("jpeg")|| extention.equals("jpg")) {
			
			contentType= "image/jpeg";
			
		}else if(extention.equals("pdf")) {
		
			contentType= "application/pdf";
			
		}else if(extention.equals("xml")) {
			
			contentType= "application/xml";
		}else{
				
			contentType = "application/octet-stream";

		}
		
		return contentType ;
	
	}
	

}
