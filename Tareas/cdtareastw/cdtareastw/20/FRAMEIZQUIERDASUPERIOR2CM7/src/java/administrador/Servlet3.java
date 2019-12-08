
package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Servlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String anumero=(String)session.getAttribute("numero");
        int numeroi=Integer.parseInt(anumero);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet3</title>");            
            out.println("</head>");
            out.println("<body>");
            for(int i=0;i<numeroi;i++)   
                out.println("<a href='"+request.getParameter("href"+i)+"' target='objeto'>"+request.getParameter("texto"+i)+"</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
