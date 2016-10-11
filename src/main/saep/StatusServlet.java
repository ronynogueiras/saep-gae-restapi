package main.saep;

import main.saep.util.ContentType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Rony on 11/10/2016.
 */
public class StatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType(ContentType.JSON);
        String uri = req.getRequestURI();
        writer.print(FakeResponse.make("Serviço SAEP operando normalmente","",uri));
    }
}
