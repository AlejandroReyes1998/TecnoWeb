package profesor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorC;
import procesos.lectorCE;
import procesos.lectorSA;
import procesos.lectorS;

public class buscarAlumno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        String ejercicio = request.getParameter("ejercicio");
        session.setAttribute("ejercicio", ejercicio);
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        //Recuperamos los ejercicios del archivo xml
        lectorSA archivoXML = new lectorSA(realpath + "solucionesAlumnos.xml");
        lectorC archivoXML2 = new lectorC(realpath + "calificaciones.xml");
        List<String> resueltos = new ArrayList<>();
        if (userName != null && id != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calificar ejercicio</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/profesor.css'>");
            out.println("</head>");
            out.println("<body>");
            //Buscamos los ejercicios que ya fueron calificados para que no se puedan volver a calificar
            for (Element resp : archivoXML2.getCalificaciones()) {
                if (resp.getAttributeValue("alumno").equals(userName)) {
                    resueltos.add(resp.getAttributeValue("pregunta"));
                }
            }
            out.println("<div class='container'>");
            out.println("<div class='col-xs-4 col-xs-offset-4 form-prof'>");
            out.println("<div class='form-prof-top'><h4>Alumnos que resolvieron: "+ejercicio+"</h4></div>");
            out.println("<form method='get' action='calificarC'>");
            out.println ("<div class='form-prof-campos'>");
            out.println ("<div class='form-group'>");
            out.println("Ejercicio: <select name='alumno' class='form-control'>");
            //por cada lectura generamos una opción
            for (Element lectura : archivoXML.getRespuestas()) {
                if(lectura.getAttributeValue("pregunta").equals(ejercicio))
                out.println("<option value='" + lectura.getAttributeValue("alumno") + "'>" + lectura.getAttributeValue("alumno") + "</option>");
            }
            out.println("</select>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<button class='btn form-control btn-info'>Editar canvas</button>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<a href='inicioprofe' class='btn form-control btn-warning'>Regresar</a>");
            out.println ("</div>");          
            out.println("</form>");
            out.println ("</div>");
            out.println ("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}