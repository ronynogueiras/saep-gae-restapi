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
public class RadocServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String uri = req.getRequestURI();
        String[] args = uri.split("/");
        resp.setContentType(ContentType.JSON);
        writer.print(FakeResponse.make("Dados do RADOC",args[2],uri));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String uri = req.getRequestURI();
        String[] args = uri.split("/");
        resp.setContentType(ContentType.JSON);
        writer.print(FakeResponse.make("Criação do RADOC realizada ",args[2],uri));
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String uri = req.getRequestURI();
        String[] args = uri.split("/");
        resp.setContentType(ContentType.JSON);
        writer.print(FakeResponse.make("RADOC removido com sucesso",args[2],uri));
    }
}
