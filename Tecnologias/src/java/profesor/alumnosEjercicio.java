/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import procesos.lectorE;
import procesos.lectorR;

/**
 *
 * @author alejandro
 */
public class alumnosEjercicio extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String ejercicio = request.getParameter("ejercicio");
        String id = (String) session.getAttribute("id");
        String grupo = (String) session.getAttribute("grupo");
        session.setAttribute("ejercicio", ejercicio);
        String realpath = (String) session.getAttribute("elcaminoreal");
        lectorR archivoXML = new lectorR(realpath + "respuestas.xml");
        if (userName != null && id != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Buscar alumnos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<H2>Estos alumnos han resuelto el ejercicio: "+ejercicio+"</H2>");
            out.println("<form method='get' action='calificar'>");
            out.println("Alumno: <select name='usuario'>");
            //por cada usuario generamos una opción
            for (Element resp: archivoXML.getRespuestas()) {
                if(resp.getAttributeValue("nombre").equals(ejercicio))
                out.println("<option value='" + resp.getAttributeValue("alumno") + "'>" + resp.getAttributeValue("alumno")+ "</td>");
            }
            out.println("</select></BR></BR>");
            out.println("<input type='submit' value='Calificar'/>");          
            out.println("</form>");
            out.println("<a href='inicioprofe'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }
    }
  
}
