package admin;

//Servlet que imprime los grupos
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorG;

public class consultaG extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String id = (String) session.getAttribute("id");
        if (username != null && id != null) {
            //Llamamos al archivo xml que contiene los grupos
            lectorG archivoXML = new lectorG(realpath + "grupos.xml");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrador</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/administrador.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='col-xs-2 col-xs-offset-5 tabla'>");
            out.println("<h2>Consultar grupos</h2>");
            out.println("<div class='row'>");
            out.println("<table class='table table-bordered'>");
            out.println("<tr class='danger'>");
            out.println("<th>Grupo</th>");
            out.println("</tr>");
            //Recuperamos los grupos registrados en el sistema
            for (Element grupo : archivoXML.getGrupos()) {
                out.println("<tr>");
                out.println("<td>" + grupo.getText() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println ("</div>");
            out.println("<a href='grupos' class='btn btn-warning'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }

}
