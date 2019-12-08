/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import procesos.lectorCE;

public class buscarSolucion extends HttpServlet {

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
        if (userName != null && id != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calificar ejercicio</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/alumno.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='col-xs-4 col-xs-offset-4 formulario'>");
            out.println("<div class='formulario-top'><h4>Buscar solucion de ejercicio</h4></div>");
            out.println("<form method='get' action='verSolucion'>");
             out.println("<div class='formulario-campos'>");
            out.println ("<div class='form-group'>");
            out.println("Ejercicio: <select name='ejercicio' class='form-control'>");
            //por cada lectura generamos una opción
            for (Element lectura : archivoXML.getCanvas()) {
                if(lectura.getAttributeValue("grupo").equals(grupo))
                out.println("<option value='" + lectura.getAttributeValue("pregunta") + "'>" + lectura.getAttributeValue("pregunta") + "</option>");
            }
            out.println("</select>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<button class='btn form-control btn-info'>Ver solucion</button>");
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

