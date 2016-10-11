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
public class ResolucaoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String uri = req.getRequestURI();
        String[] args = uri.split("/");
        resp.setContentType(ContentType.JSON);
        writer.print(FakeResponse.make("Dados da resolução",args[2],uri));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String uri = req.getRequestURI();
        String[] args = uri.split("/");
        resp.setContentType(ContentType.JSON);
        writer.print(FakeResponse.make("Criação da resolução feita com sucesso",args[2],uri));
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String uri = req.getRequestURI();
        String[] args = uri.split("/");
        resp.setContentType(ContentType.JSON);
        writer.print(FakeResponse.make("Remoção da resolução realizada com sucesso",args[2],uri));
    }
}
