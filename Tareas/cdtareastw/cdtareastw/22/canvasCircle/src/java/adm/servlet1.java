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
        HttpSession session = request.getSession();
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        int xi = Integer.parseInt(x);
        int yi = Integer.parseInt(y);
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
            out.println("            context.beginPath();\n"
                    + "            context.arc("+xi+","+yi+",50,0,2*Math.PI);\n"
                   
                    + "            context.stroke();\n");
        
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
    }
}
