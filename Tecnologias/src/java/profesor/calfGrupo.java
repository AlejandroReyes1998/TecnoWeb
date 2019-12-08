/*
 Despliega las califiicaciones del grupo del profesor
 */
package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorC;

/**
 *
 * @author alejandro
 */
public class calfGrupo extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String id = (String) session.getAttribute("id");
        lectorC archivoXML = new lectorC(realpath + "calificaciones.xml");
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calificaciones de Grupo</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/profesor.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='col-xs-4 col-xs-offset-4 tabla'>");
            out.println("<h2>Consultar calificacion de grupos</h2>");
            out.println("<div class='row'>");
            out.println("<table class='table table-bordered'>");
            out.println("<tr><h4>Calificaciones del grupo: "+grupo+"</h4></tr>");
            out.println("<tr class='danger'><th>Alumno</th><th>Nombre Ejercicio</th><th>Calificacion</th></tr>");
            for (Element calf : archivoXML.getCalificaciones()) {
                if(archivoXML.esDelGrupo(calf, grupo)){
                    out.println("<tr>");
                    out.println("<th>"+calf.getAttributeValue("alumno")+"</th>");
                    out.println("<th>"+calf.getAttributeValue("ejercicio")+"</th>");
                    out.println("<th>"+calf.getText()+"</th>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println ("</div>");
            out.println("<div class='mybutton'>");
            out.println("<a href='inicioprofe' class='btn btn-warning'>Regresar</a>");
            out.println("</div>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
