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
        String link = (String) session.getAttribute("link");
        String l = (String) session.getAttribute("l");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        if (l.equals("li")) {
            for (int i = 0; i < Integer.parseInt(link); i++) {
                out.print("<li><a href='" + request.getParameter("ref" + i)+"'>"+ request.getParameter("en" + i) + "</a></li>");
            }
        } else {
            out.println("<" + l + ">");
            for (int i = 0; i < Integer.parseInt(link); i++) {
                out.print("<li><a href='" + request.getParameter("ref" + i)+"'>"+ request.getParameter("en" + i) + "</a></li>");
            }
            out.println("</" + l + ">");
        }
        out.println("<a href='index.html'>");
        out.println("Regresar al inicio");
        out.println("</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
