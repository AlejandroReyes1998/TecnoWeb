/*
 Menu principal de administrador
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorG;

public class inicioadmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String id = (String) session.getAttribute("id");
        int i = 0;
        if (userName != null && id != null) {
            //Desplegamos los dos menús, el de usuario y el de grupos
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
            lectorG archivoXML = new lectorG(realpath + "grupos.xml");
            for (Element grupo : archivoXML.getGrupos()) {
                i++;
            }
            if (i > 0) {
                out.println("<div class='mybutton'><a href='usuarios' class='btn btn-danger'> USUARIOS</a></div>");
            }else{
                out.println("<div class='mybutton'>Registre grupos para registrar usuarios</div>");
            }
            out.println("<div class='mybutton'><a href='grupos' class='btn btn-danger'>GRUPOS</a></div>");
            out.println("<div class='mybutton'><a href='logout' class='btn btn-warning'>LogOut</a></div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
