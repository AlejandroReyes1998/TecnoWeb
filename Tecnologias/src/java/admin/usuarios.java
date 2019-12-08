/*
 Menú de usuarios
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class usuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String id = (String) session.getAttribute("id");
        //Desplegamos las opciones de usuario para el administrador
        if (userName != null && id != null) {
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
            out.println("<h1>Hola ADMIN: " + userName + "</h1>");
            out.println("</div>");
            out.println("<div class='col-xs-4 col-xs-offset-4 menu'>");
            out.println("<div class='mybutton'><a href='altaU' class='btn btn-danger'>AÑADIR USUARIOS</a></div>");
            out.println("<div class='mybutton'><a href='eliminarU' class='btn btn-danger'>BORRAR USUARIOS (SOLO PROFES/ALUMNOS)</a></div>");
            out.println("<div class='mybutton'><a href='cambiosU' class='btn btn-danger'>CAMBIAR USUARIOS (SOLO PROFES/ALUMNOS)</a></div>");
            out.println("<div class='mybutton'><a href='consultaU' class='btn btn-danger'>CONSULTAR USUARIOS</a></div>");
            out.println("<div class='mybutton'><a href='inicioadmin' class='btn btn-warning'>Regresar</a></div>");
            out.println("</div>");            
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }
    }

}
