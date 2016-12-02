package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import util.ContentStatus;
import util.ContentType;
import util.ErrorCode;

@SuppressWarnings("serial")

public class Radoc extends HttpServlet {
	
	private ApplicationService application = new ApplicationService();
	
	/**
	 * Recupera o relatório (RADOC) indicado
	 * @author Rony Nogueira
	 * 
	 * @see {http://docs.saep.apiary.io/#reference/0//saep/radoc/{id}/get}
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		//No-used
		String uuid = request.getPathInfo();
		
		String responseText = "";
		String contentType;
		int status;
		
		InputStream radocHTML = application.getReportHTML();
		
		if (radocHTML != null){
			status = ContentStatus.OK;
			contentType = ContentType.HTML;
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			int reads = radocHTML.read(); 
			while(reads != -1){ 
				baos.write(reads); 
				reads = radocHTML.read();
			}
			OutputStream output = response.getOutputStream();
			output.write(baos.toByteArray());
			output.close();
			response.setContentLength(output.toString().length());
		}else{
			contentType = ContentType.JSON;
			status = ContentStatus.SERVER_ERROR;
			JSONObject json = new JSONObject();
			try{
				json.put("codigo", ErrorCode.Unknown.CODE);
				json.put("mensagem", ErrorCode.Unknown.MESSAGE);
				responseText = json.toString();
			}catch(JSONException e){
				contentType = ContentType.PLAIN_TEXT;
				responseText = "erro inesperado";
			}
			response.setContentLength(responseText.length());
			response.getWriter().write(responseText);
			response.setContentLength(responseText.length());
		}
		response.setStatus(status);
		response.setContentType(contentType);		
	}
	/**
	 * 
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String report = request.getParameter("resolucao");
		String uuid = request.getPathInfo();
		boolean isCreate = application.createReport(uuid,report);
		String responseText = "";
		int status;
		if(isCreate){
			status = ContentStatus.NO_CONTENT;
		}else{
			JSONObject json = new JSONObject();
			try{
				json.put("codigo", ErrorCode.Unknown.CODE);
				json.put("mensagem", ErrorCode.Unknown.MESSAGE);
				responseText = json.toString();
				status = ContentStatus.SERVER_ERROR;
			}catch(JSONException e){
				status = ContentStatus.SERVER_ERROR;
				responseText = "erro inesperado";
			}
		}
		response.setStatus(status);
		response.setContentType(ContentType.JSON);
		response.setContentLength(responseText.length());
		response.getWriter().write(responseText);
	}
	/**
	 * Apaga um relatório (RADOC) através do UUID fornecido
	 * @author Rony Nogueira
	 * @see {http://docs.saep.apiary.io/#reference/0/saepradocid/delete}
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 * */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uuid = request.getPathInfo();
		String responseText="";
		int status;
		boolean isDelete = application.deleteResolution(uuid);
		if(isDelete){
			status = ContentStatus.NO_CONTENT;
		}else{
			try{
				status = ContentStatus.SERVER_ERROR;
				JSONObject json = new JSONObject();
				json.put("message", ErrorCode.Unknown.MESSAGE);
				responseText = json.toString();
			}catch(JSONException e){
				status = ContentStatus.SERVER_ERROR;
				responseText = ErrorCode.Unknown.MESSAGE;
			}
		}
		response.setContentType(ContentType.JSON);
		response.setStatus(status);
		response.setContentLength(responseText.length());
		response.getWriter().write(responseText);
	}
	
}
