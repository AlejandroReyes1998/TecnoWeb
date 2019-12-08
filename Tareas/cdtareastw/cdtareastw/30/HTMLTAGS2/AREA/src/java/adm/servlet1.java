package adm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String text = request.getParameter("text");
        String text2 = request.getParameter("text2");
        String text3 = request.getParameter("text3");
        String text4 = request.getParameter("text4");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>servlet1</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<map name='shapes-map'>");
        out.println("<area shape='rect' title='square' coords='"+text+","+text2+","+text3+","+text4+"' href='c43.png' target='_blank'/>");
        out.println("</map>");
        out.println("<a href='index.html'>");
        out.println("Regresar al inicio");
        out.println("</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
