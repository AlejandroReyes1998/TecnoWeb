/*
 Pedimos el nombre del grupo que quiere registrar el administrador.
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class altaG extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String id = (String) session.getAttribute("id");

        PrintWriter out = response.getWriter();
        if (username != null && id != null) {
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
            out.println("<div class='form-alta'>");            
            out.println("<div class='col-xs-3 col-xs-offset-4 formulario'>");
            out.println ("<div class='form-alta-top'>");
            out.println("<h4>Registrar nuevo grupo</h4>");
            out.println("</div>");
            out.println ("<div class='form-alta-campos'>");
            out.println("<form action='registroGrupo' method='get'>");
            out.println("<div class='form-group'>");
            out.println("Nombre Grupo: <input type='text' name='grupo' class='form-control' pattern='[A-Za-z0-9]+' title='Solo letras y numeros' required/>");
            out.println ("</div>");
            out.println("<button class='btn pull-right btn-success'>Registrar</button>");
            out.println("</form>");
            out.println("<a href='grupos' class='btn pull-left btn-warning'>Regresar</a>");
            out.println("<div class='clearfix button-right'></div> ");
            out.println ("</div>");
            out.println ("</div>");
            out.println ("</div>");
            out.println("</body>");
            out.println("</html>");
        }else{
            response.sendRedirect("index.html");
        }

    }
}
