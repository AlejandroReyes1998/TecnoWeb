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
        int row = Integer.parseInt(request.getParameter("row"));
        int col = Integer.parseInt(request.getParameter("col"));
        int x=0;
        HttpSession session = request.getSession();
        session.setAttribute("row", row);
        session.setAttribute("col", col);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>servlet1</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='servlet2' method='get'>");
        out.println("<table border='1'>");
        for (int i = 0; i < row; i++) {
            out.println("<tr>");
            for (int j = 0; j < col; j++) {
                out.println("<td>");
                out.println("texto : <input type='text' name='clave" + x++ + "'/>");
                out.println("</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<input type=\"submit\" value=\"send\"/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
