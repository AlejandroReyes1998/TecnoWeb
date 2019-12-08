/*
 Servlet que se encarga de recibir los datos del nodo a cambiar
 */
package procesos;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
public class cambioG extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        String userName = (String) session.getAttribute("username");
        String nameGrupo = (String) request.getParameter("namegrupo");
        String id = (String) session.getAttribute("id");
        session.setAttribute("username", userName);
        session.setAttribute("id", id);
        session.setAttribute("elcaminoreal", session.getAttribute("elcaminoreal"));
        if (userName != null && id != null) {
                session.setAttribute("oldGrupo", nameGrupo);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<script src='js/bootstrap.min.js'></script>");
                out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
                out.println("<link rel='stylesheet' href='css/administrador.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<div class='form-alta'>");
                out.println("<div class='col-xs-4 col-xs-offset-4 formulario'>");
                out.println("<div class='form-alta-top'><h3>Ingrese el nombre del nuevo grupo.</h3></div>");
                out.println("<div class='form-alta-campos'>");
                out.println("<form action='changeGrupoXML' method='get'>");
                out.println("<div class='form-group'>");
                out.println("Nombre: <input type='text' value='" + nameGrupo + "' name='namegruponew' class='form-control' required/>");
                out.println("</div>");
                out.println("<button class='btn pull-right btn-success'>REGISTRAR</button>");
                out.println("</form>");
                out.println("<a href='grupos' class='btn  pull-left btn-warning'>Regresar</a>");
                out.println("<div class='clearfix bottom-left'></div>");
                out.println("<div class='clearfix bottom-right'></div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } 
         else {
            response.sendRedirect("index.html");
        }
    }
}