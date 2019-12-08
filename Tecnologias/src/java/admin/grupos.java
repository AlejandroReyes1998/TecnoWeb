/*
 Menú de grupos
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class grupos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String id = (String) session.getAttribute("id");
        //Menú de grupos, donde realiza las operaciones relacionadas con los mismos
        if (username != null && id != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Admin</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/administrador.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='welcome-top'>");
            out.println("<h1>Hola ADMIN: " + username + "</h1>");
            out.println("</div>");            
            out.println("<div class='col-xs-4 col-xs-offset-4 menu'>");
            out.println("<div class='mybutton'><a href='altaG' class='btn btn-danger'>AÑADIR GRUPO</a></div>");
            out.println("<div class='mybutton'><a href='consultaG' class='btn btn-danger'>CONSULTAR GRUPO</a></div>");
            out.println("<div class='mybutton'><a href='cambiosG' class='btn btn-danger'>CAMBIAR GRUPO</a></div>");
            out.println("<div class='mybutton'><a href='eliminarG' class='btn btn-danger'>ELIMINAR GRUPO</a></div>");
            out.println("<div class='mybutton'><a href='inicioadmin' class='btn btn-warning'>Regresar</a></div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }

}
