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
        int num = Integer.parseInt(request.getParameter("num"));
        HttpSession session = request.getSession();
        session.setAttribute("num", num);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet1</title>");
        out.println("</head>");
        out.println("<body>");
        out.print("   <form action=\"servlet2\" method=\"get\">\n");
                for(int i=0;i<num;i++){
                out.print( "          href: <input type=\"text\" name=\"link"+i+"\"/>\n"
                + "         texto a desplegar: <input type=\"text\" name=\"text"+i+"\"/>\n");
                }
        out.println("<input type=\"submit\" value=\"send\"/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
