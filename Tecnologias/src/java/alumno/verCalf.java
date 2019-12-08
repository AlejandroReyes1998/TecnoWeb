/*
 Despliega las calificaciones del alumno
 */
package alumno;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorC;

public class verCalf extends HttpServlet {
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
            out.println("<title>Calificaciones</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/alumno.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='col-xs-4 col-xs-offset-4 tabla'>");
            out.println("<h2>Consultar calificacion</h2>");
            out.println("<div class='row'>");
            out.println("<table class='table table-bordered'>");
            out.println("<tr><h4>Calificaciones de: "+userName+"</h4></tr>");
            out.println("<tr class='info'><th>Nombre Ejercicio</th><th>Calificacion</th></tr>");
            for (Element calf : archivoXML.getCalificaciones()) {
                if(archivoXML.esDelAlumno(calf, userName, grupo)){
                    out.println("<tr>");   
                    out.println("<th>"+calf.getAttributeValue("ejercicio")+"</th>");
                    out.println("<th>"+calf.getText()+"</th>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println("</div>");
            out.println("<a href='inicioalumno' class='btn btn-warning'>Regresar</a>");
            out.println("</div>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
