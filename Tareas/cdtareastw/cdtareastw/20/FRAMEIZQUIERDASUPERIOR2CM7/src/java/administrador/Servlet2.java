
package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet2 extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String anumero=(String)session.getAttribute("numero");
        int numeroi=Integer.parseInt(anumero);
        String parametros="";
        for(int i=0;i<numeroi;i++)
        {
            parametros+="href"+i+"="+request.getParameter("href"+i)+"&"+"texto"+i+"="+request.getParameter("texto"+i)+"&";
        }
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<frameset cols='80%,*'>");
            out.println("<frameset rows='80%,*'>");
            out.println("<frame src='Servlet4' name='objeto'>");
            out.println("<frame src='Servlet3?"+parametros+"'>");
            out.println("</frameset>");
            out.println("<frame src='Servlet5'>");
            out.println("</frameset>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
