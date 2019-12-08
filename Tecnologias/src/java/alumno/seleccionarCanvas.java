package alumno;

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
import procesos.lectorCE;
import procesos.lectorSA;

public class seleccionarCanvas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        //Recuperamos los ejercicios del archivo xml
        lectorCE archivoXML = new lectorCE(realpath + "ejercicioCanvas.xml");
        lectorSA archivoXML2 = new lectorSA(realpath + "solucionesAlumnos.xml");
        List<String> resueltos = new ArrayList<>();
        if (userName != null && id != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Seleccionar Ejercicio</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/alumno.css'>");
            out.println("</head>");
            out.println("<body>");
            //Buscamos los ejercicios que ya resolvió el alumno para que no los vuelva a hacer
            for (Element resp : archivoXML2.getRespuestas()) {
                if (resp.getAttributeValue("alumno").equals(userName)) {
                    resueltos.add(resp.getAttributeValue("pregunta"));
                }
            }
            out.println("<div class='container'>");
            out.println("<div class='col-xs-4 col-xs-offset-4 formulario'>");
            out.println("<div class='formulario-top'><h4>Seleccionar Ejercicio</h4></div>");
            out.println("<form method='get' action='resolver'>");
             out.println("<div class='formulario-campos'>");
            out.println ("<div class='form-group'>");
            out.println("Ejercicio: <select name='ejercicio' class='form-control'>");
            for (Element ejercicio : archivoXML.getCanvas()) {
                //Si no los ha resuelto, se puede desplegar el ejercicio
                if (resueltos.indexOf(ejercicio.getAttributeValue("pregunta"))==-1) {
                    //Si existen ejercicios/lecturas asociados al grupo del alumno, estos se despliegan
                    if (ejercicio.getAttributeValue("grupo").equals(grupo)) {
                        out.println("<option value='" + ejercicio.getAttributeValue("pregunta") + "'>" + ejercicio.getAttributeValue("pregunta") + "</option>");
                    }
                }
            }
            out.println("</select>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<button class='btn form-control btn-info'>Resolver</button>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<a href='inicioalumno' class='btn form-control btn-warning'>Regresar</a>");
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

