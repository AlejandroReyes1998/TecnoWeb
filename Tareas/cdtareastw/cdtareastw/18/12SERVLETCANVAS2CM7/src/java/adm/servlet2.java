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
public class servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    )
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String numero = (String) session.getAttribute("num");
        int num = Integer.parseInt(numero);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(" <canvas id=\"myCanvas\" width=\"600\" height=\"800\"></canvas>\n"
                + "        <script>\n"
                + "            var canvas = document.getElementById('myCanvas');\n"
                + "            var context = canvas.getContext('2d');\n"
                + "\n");
        for (int i = 0; i < num; i++) {
            out.println("            context.beginPath();\n"
                    + "            context.moveTo("+request.getParameter("xinicial"+i)+","+request.getParameter("yinicial"+i)+");\n"
                    + "            context.lineTo("+request.getParameter("xfinall"+i)+","+request.getParameter("yfinall"+i)+");\n"
                    + "            context.stroke();\n");
        }
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
    }
}
