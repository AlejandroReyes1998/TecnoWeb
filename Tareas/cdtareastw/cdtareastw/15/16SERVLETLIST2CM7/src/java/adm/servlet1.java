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
        String l = request.getParameter("l");
        String link = request.getParameter("link");
        int linki = Integer.parseInt(link);
        HttpSession session = request.getSession();
        session.setAttribute("link", link);
        session.setAttribute("l", l);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>servlet1</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form method='get' action='servlet2'>");
        for (int i = 0; i < linki; i++) {
            out.println("Enlace: <input type='text' name='en" + i + "'/>"+"HREF: <input type='text' name='ref" + i + "'/>");
        }
        out.println(" <input type=\"submit\" value=\"send\"/>\n" + "</form>");
        out.println("<a href='index.html'>");
        out.println("Regresar al inicio");
        out.println("</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
