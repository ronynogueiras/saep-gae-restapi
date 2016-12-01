package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import java.io.InputStream;

import util.ContentStatus;
import util.ContentType;
import util.ErrorCode;
@SuppressWarnings("serial")
public class Resolution extends HttpServlet{
	
	private ApplicationService application = new ApplicationService();
	
	/**
	 * Recupera a resolução com o UUID fornecido
	 * @see {http://docs.saep.apiary.io/#reference/0//saep/resolucao/{id}/get}
	 * @author Rony Nogueira
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException
	 * @throws IOException 
	 * */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String path = request.getPathInfo();
		try{
			InputStream pdf = (InputStream) application.getResolution("uuid");
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			int reads = pdf.read(); 
			while(reads != -1){ 
				baos.write(reads); 
				reads = pdf.read();
			} 		
			response.setStatus(ContentStatus.OK);
			response.setContentType(ContentType.PDF);
			response.setContentLength(baos.toByteArray().length);
			
			OutputStream output = response.getOutputStream();
			output.write(baos.toByteArray());
			output.close();
			
		}catch(IOException e){
			response.setStatus(ContentStatus.SERVER_ERROR);
			e.printStackTrace();
		}		
	}
	/**
	 * Cria uma nova resolução a partir do UUID fornecido e a resolução
	 * @author Rony Nogueira
	 * @see {http://docs.saep.apiary.io/#reference/0/saepresolucaoid/post}
	 * @param HttpServletRequest 
	 * @param HttpServletResponse 
	 * @throws ServletException
	 * @throws IOException
	 * */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String resolution = request.getParameter("resolucao");
		String uuid = request.getPathInfo();
		boolean isCreate = application.createResolution(uuid,resolution);
		String responseText = "";
		int status;
		if(isCreate){
			status = ContentStatus.NO_CONTENT;
		}else{
			JSONObject json = new JSONObject();
			try{
				json.put("codigo", 9471169);
				json.put("mensagem", "erro inesperado");
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
	 * Apaga uma resolução que possui o UUID fornecido
	 * @author Rony Nogueira
	 * @see { http://docs.saep.apiary.io/#reference/0/saepresolucaoid/delete }
	 * @throws ServletException
	 * @throws IOException
	 * */
	@Override
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
