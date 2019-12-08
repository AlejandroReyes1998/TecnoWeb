package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int row = (int) session.getAttribute("row");
        int col = (int) session.getAttribute("col");
        int x=0;
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>servlet2</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<table border='1'>");
        for (int i = 0; i < row; i++) {
            out.println("<tr>");
            for (int j = 0; j < col; j++) {
                out.println("<td>");
                out.println(request.getParameter("clave" + x++ ));
                out.println("</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a href='index.html'>");
        out.println("Regresar al inicio");
        out.println("</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
