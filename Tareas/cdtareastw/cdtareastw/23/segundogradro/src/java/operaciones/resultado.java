/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author theva
 */
public class resultado extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        float a = Float.parseFloat(request.getParameter("a1"));
        float b = Float.parseFloat(request.getParameter("b2"));
        float c = Float.parseFloat(request.getParameter("c3"));
        String res = operacion(a, b, c);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>resultado</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(res);
        out.println("<a href='index.html'>");
        out.println("regresar");
        out.println("</a>");
        out.println("</body>");
        out.println("</html>");
    }

    String operacion(float a1, float b2, float c3) {
        float formula = (b2 * b2) - (4 * a1 * c3);
        if (formula >= 0.0) {         
            float r1 = (-b2 + (float)Math.sqrt(formula))/(2*a1);
            float r2 = (-b2 - (float)Math.sqrt(formula))/(2*a1);
            String res1 = Float.toString(r1);
            String res2 = Float.toString(r2);
            return "Raíz 1 = "+res1+" Raíz 2 = "+res2;
        } else {
            return "La ecuación no tiene raíces reales";
        }
    }
}
