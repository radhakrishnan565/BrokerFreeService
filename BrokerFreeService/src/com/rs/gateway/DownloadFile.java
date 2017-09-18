package com.rs.gateway;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.rs.dao.util.MIMExtensions;

@Path("/download")
public class DownloadFile {
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM )
	public Response  downloadFile( @QueryParam("downloadData")String downloadData  , @QueryParam("header")String header , @Context HttpServletResponse response  ) throws  IOException{
		
		String fileName ="E:/finalTrunk/MongodbDocs/opsmanager-manual-v1.6.pdf";
		System.out.println("downloadData"+downloadData +"header"+header);
		
		OutputStream outputStream =response.getOutputStream();

		if(fileName  !=null ){
			//
			InputStream is = new FileInputStream(fileName);
	       // ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        int len;
	        byte[] buffer = new byte[4096];
	        while ((len = is.read(buffer, 0, buffer.length)) != -1) {
	            outputStream.write(buffer, 0, len);
	        }
	        System.out.println("Server size: "  );
	        //return Response.ok(baos).build();
			//
			
			System.err.println("file name of dowloaded file "+fileName );
			String contentType=  MIMExtensions.getcontenetType(getFileExtention(fileName));//"application/pdf";
			System.out.println("content type:>>>>>"+contentType);
			
			response.setContentType(contentType  );
			
			if( contentType.equals("application/octet-stream") ){
	       
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName );
				
			}else{     
				
				response.setHeader("Content-Disposition", "inline; filename=" + fileName );
	
				}
			try {
				if(outputStream != null ){
					outputStream.flush();
					outputStream.close();
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ResponseBuilder responsebuilder  = Response.ok();
		            
		responsebuilder.header("Content-Disposition", "filename="+fileName );
		return responsebuilder.build();
 	}
	public String getFileExtention(String fileName){
		
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
	        return fileName.substring(fileName.lastIndexOf(".")+1);
	        else return "";
	}

}
