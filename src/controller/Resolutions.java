package controller;

import java.io.IOException;

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

public class Resolutions extends HttpServlet {
	/**
	 * Responde a requisição em /resolucoes, retorna JSON contendo os identificadores das resolucoes gravadas
	 * @see {http://docs.saep.apiary.io/#reference/0//saep/resolucoes/get}
	 * @author Rony Nogueira
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException, IOException, JSONException
	 * */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ApplicationService application = new ApplicationService();
		String resolutionIds = application.getIdsResolutions();
		String responseText = "";
		JSONObject json = new JSONObject();
		int status;
		
		try {
			
			if(resolutionIds != null){
				
				json.put("ids", resolutionIds);
				responseText = json.toString();
				status = ContentStatus.OK;
				
			}else{
				
				json.put("cod", ErrorCode.Unknown.CODE);
				json.put("message",ErrorCode.Unknown.MESSAGE);
				responseText = json.toString();
				status = ContentStatus.SERVER_ERROR;
				
			}
			
		} catch (JSONException e) {
			
			status = ContentStatus.SERVER_ERROR;
			responseText = ErrorCode.Unknown.MESSAGE;
			
		}
		response.setContentType(ContentType.JSON);
		response.setContentLength(responseText.length());
		response.setStatus(status);
		response.getWriter().write(responseText);
	}
}
