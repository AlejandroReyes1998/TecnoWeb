package administrador;

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
        String valor1 = request.getParameter("claveparametro1");
        HttpSession session = request.getSession();
        session.setAttribute("claveatributo1", valor1);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>servlet1</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='servlet2' method='get'>");
        for (int i = 0; i < Integer.parseInt(valor1); i++) {
            out.println("url " + (i + 1) + ": <input type='text' name='claveurl" + i + "' placeholder='url'/>");
            out.println("<br/>");
            out.println("valor del nodo " + (i + 1) + ": <input type='text' name='clavevalor" + i + "' placeholder='valor'/>");
            out.println("<br/>");
        }
        out.println("<input type=\"submit\" value=\"send\"/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
