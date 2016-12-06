package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
public class Status extends HttpServlet {
	
	/**
	 * Retorna o status de atividade do servidor de aplicação do SAEP 
	 * @see {http://docs.saep.apiary.io/#reference/0/saepstatus/get} 
	 * @author Rony Nogueira
	 * @param HttpServletRequest 
	 * @param HttpServletResponse 
	 * @throws ServletException
	 * @throws IOException 
	 * */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApplicationService application = new ApplicationService();
		
		int status = application.status();
		
		response.setContentType("application/json");
		response.setStatus(status);
		String responseText;
		switch(status){
			case 200:
				responseText = "";
			break;
			case 501:
				responseText = "{\"message\": \"Erro inesperado\"}";
			break;
			default:
				responseText = "{\"message\": \"Erro inesperado\"}";
			break;
		}
		response.setContentLength(responseText.length());
		response.getWriter().write(responseText);
	}
	
	
}
