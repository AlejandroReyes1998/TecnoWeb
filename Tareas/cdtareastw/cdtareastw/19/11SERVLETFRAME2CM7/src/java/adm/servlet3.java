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
public class servlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    )
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String num = (String) session.getAttribute("num");
        int n = Integer.parseInt(num);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet3</title>");
        out.println("</head>");
        out.println("<body>");
        for(int i=0;i<n;i++){
                 out.print( "<a href=' "+request.getParameter("link"+i+"")+"target='content' '>"+request.getParameter("text"+i+"")+"</a><br/>");
        }  
        out.println("</body>");
        out.println("</html>");
    }
}
