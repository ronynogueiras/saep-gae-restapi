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
public class Changes extends HttpServlet {
	/**
	 * Recupera as alterações realizadas para um dado parecer 
	 * @see {http://docs.saep.apiary.io/#reference/0/saepalteracoesid/get}
	 * @author Rony Nogueira
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException
	 * @throws IOException 
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uuid = request.getPathInfo();
		ApplicationService application = new ApplicationService();
		
		String changes = application.getChangesDocument(uuid);
		String responseText,contentType;
		int status;
		if(changes != null){
			responseText = changes;
			contentType = ContentType.PLAIN_TEXT;
			status = ContentStatus.OK;
		}else{
			JSONObject json = new JSONObject();
			try{
				json.put("codigo", ErrorCode.Unknown.CODE );
				json.put("mensagem", ErrorCode.Unknown.MESSAGE);
				responseText = json.toString();
				contentType = ContentType.JSON;
				status = ContentStatus.SERVER_ERROR;
			}catch(JSONException e){
				responseText = "erro inesperado";
				status = ContentStatus.SERVER_ERROR;
				contentType = ContentType.PLAIN_TEXT;
			}
		}
		response.setStatus(status);
		response.setContentLength(responseText.length());
		response.setContentType(contentType);
		response.getWriter().write(responseText);
	}
	
}
