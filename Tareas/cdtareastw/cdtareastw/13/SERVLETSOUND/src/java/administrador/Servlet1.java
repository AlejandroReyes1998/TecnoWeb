package administrador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");//tipo mime
        String nombresonido = request.getParameter("nombresonido");
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello world</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<audio src='" + nombresonido + ".mp3' autoplay></audio>");
             out.println("<a href='Formulario.html'>REGRESAR</a>");
            out.println("</body>");
            out.println("</html>");        
    }    
}  