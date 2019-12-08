package adm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String n = request.getParameter("num");
        int nu = Integer.parseInt(n);
        HttpSession session = request.getSession();
        session.setAttribute("num", n);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>servlet1</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form method='get' action='servlet2'>");
        for (int i = 0; i < nu; i++) {
            out.println("contenido: <input type='text' name='cont" + i + "'/>");
        }
        out.println(" <input type=\"submit\" value=\"send\"/>\n" + "</form>");
        out.println("<a href='index.html'>");
        out.println("Regresar al inicio");
        out.println("</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
