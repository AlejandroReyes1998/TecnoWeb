/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alejandro
 */
public class servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    )
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String valor = request.getParameter("num");
        int valori= Integer.parseInt(valor);
        HttpSession session = request.getSession();
        session.setAttribute("num", valor);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='servlet2' method='get'>");
        for (int i = 0; i < valori; i++) {
            out.println("PUNTO X INICIAL" + i + "': <input type='text' name='xinicial" + i + "'/>");
            out.println("PUNTO Y INICIAL" + i + "': <input type='text' name='yinicial" + i + "'/><BR/>");
            out.println("PUNTO X FINAL" + i + "': <input type='text' name='xfinal" + i + "'/>");
            out.println("PUNTO Y FINAL" + i + "': <input type='text' name='yfinial" + i + "'/><BR>");
        }
        out.println("<input type='submit' value='send'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
