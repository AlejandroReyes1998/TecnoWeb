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
        String n = (String) session.getAttribute("num");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<select>");
        for (int i = 0; i < Integer.parseInt(n); i++) {
            out.print("<option>" + request.getParameter("cont" + i) + "</option>");
        }
        out.println("</select>");
        out.println("<a href='index.html'>");
        out.println("Regresar al inicio");
        out.println("</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
