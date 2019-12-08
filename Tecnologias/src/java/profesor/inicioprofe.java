package profesor;
//Menu de inicio de profesor

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorL;

/**
 *
 * @author Alejandro
 */
public class inicioprofe extends HttpServlet {

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
        session.setAttribute("username", userName);
        session.setAttribute("id", id);
        session.setAttribute("grupo", grupo);
        session.setAttribute("elcaminoreal", realpath);
        lectorL archivoXML = new lectorL(realpath + "lecturas.xml");
        int i = 0;
        if (userName != null && id != null) {
            out.println("<!DOCTYPE html>");
            for (Element lectura : archivoXML.getLecturas()) {
                i++;
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Profesor</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/profesor.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='top'>");
            out.println("<h1>Hola PROFESOR: " + userName + "</h1>");
            out.println("</div>");
            out.println("<div class='col-xs-4 col-xs-offset-4 menu'>");
            out.println("<div class='mybutton'><a href='subirArchivo' class='btn btn-success'>Subir Archivo</a></div>");
            out.println("<div class='mybutton'><a href='subirLectura' class='btn btn-success'>Subir Lectura</a></div>");
            if (i > 0) {

                out.println("<div class='mybutton'><a href='asignarEjemplo' class='btn btn-success'>Subir/Editar Ejemplo</a></div>");
                out.println("<div class='mybutton'><a href='ejercicioCanvas' class='btn btn-success'>Subir Ejercicio</a></div>");
                out.println("<div class='mybutton'><a href='modificarEjercicio' class='btn btn-success'>Modificar Ejercicio</a></div>");
                out.println("<div class='mybutton'><a href='solucionEjercicio' class='btn btn-success'>Subir Solucion Ejercicio</a></div>");
            } else {
                out.println("<div class='mybutton'>Registre lecturas para poder subir ejercicios</div>");
            }

            out.println("<div class='mybutton'><a href='calificarEjercicio' class='btn btn-success'>Calificar ejercicio</a></div>");
            out.println("<div class='mybutton'><a href='calfGrupo' class='btn btn-success'>Calificaciones de grupo</a></div>");
            out.println("<div class='mybutton'><a href='logout' class='btn btn-warning'>LogOut</a></div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }
    }
}
