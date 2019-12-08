/*
 Menu principal de alumno
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
import procesos.lectorG;

public class inicioalumno extends HttpServlet {

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
        int i=0;
        if (userName != null && id != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Alumno</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/alumno.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println ("<div class='container'>");
            out.println ("<div class='top'><h2>Hola ALUMNO: " + userName + "</h2></div>");
            out.println("<div class='col-xs-4 col-xs-offset-4 menu'>");
            out.println("<div class='mybutton'>");
            out.println("<a href='ejemplos' class='btn btn-info'>Ver Ejemplos</a>");
            out.println ("</div>");
            out.println("<div class='mybutton'>");
            out.println("<a href='seleccionarCanvas' class='btn btn-info'>Resolver Ejercicio</a>");
            out.println ("</div>");
            out.println("<div class='mybutton'>");
            out.println("<a href='lecturas' class='btn btn-info'>Lecturas</a>");
            out.println ("</div>");
            out.println("<div class='mybutton'>");
            out.println("<a href='verCalf' class='btn btn-info'>Ver calificaciones</a>");
            out.println ("</div>");
            lectorC archivoXML = new lectorC(realpath + "calificaciones.xml");
            for (Element x : archivoXML.getCalificaciones()) {
                i++;
            }
            if (i > 0) {
                out.println("<div class='mybutton'><a href='buscarSolucion' class='btn btn-info'>Soluciones</a></div>");
            }else{
                out.println("<div class='mybutton'>Cuando tenga calificaciones podrá consultar las soluciones de los ejercicios que resolvió</div>");
            }
            out.println("<div class='mybutton'>");
            out.println("<a href='logout' class='btn btn-warning'>LogOut</a>");
            out.println ("</div>");
            out.println ("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
