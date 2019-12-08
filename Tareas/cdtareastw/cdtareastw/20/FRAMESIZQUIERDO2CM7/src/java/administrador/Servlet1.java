
package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet1 extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String anumero=request.getParameter("numero");
        int numeroi=Integer.parseInt(anumero);
        HttpSession session=request.getSession();
        session.setAttribute("numero",anumero);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Servlet2' method='get'>");
            for(int i=0;i<numeroi;i++)
            {
                out.println("LINKS:<input type='text' name='HREF"+i+"'/>");
                out.println("TEXTO:<input type='text' name='texto"+i+"'/>");
                out.println("<br/>");
                
            }    
            out.println("<input type='submit'/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
