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
        int num =(int) session.getAttribute("num");
        String parametros="?";
        for(int i=0;i<num;i++){
            parametros=parametros+"link"+i+"="+request.getParameter("link"+i)+"&"+"text"+i+"="+request.getParameter("text"+i)+"&";
        }
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Servlet2</title>");
        out.println("</head>");
        out.println("<frameset cols=\"30%,*\">");
            out.println("<frame src='s3"+parametros+"'/>");
            out.println("<frame src='s4' name='content'/>");
        out.println("</frameset>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
    }
}