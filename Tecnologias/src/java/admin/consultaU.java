/*
 Servlet que despliega a todos los usuarios registrados en el sistema
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
import procesos.lector;

public class consultaU extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        //Llamamos al archivo xml que contiene los datos de los usuarios
        lector archivoXML = new lector(realpath + "usuarios.xml");
        String id = (String) session.getAttribute("id");
        if (username != null && id != null) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrador</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/administrador.css'>");    
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='col-xs-3 col-xs-offset-4 tabla'>");
            out.println ("<h2>Usuarios</h2>");
            out.println("<div class='row'>");/*Separamos en una fila la tabla*/
            out.println("<table class='table table-bordered'>");
            out.println("<tr class='danger'>");
            out.println("<th>Nombre</th><th>Tipo</th><th>Grupo</th>");
            out.println("</tr>");
            //Para los administradores no desplegamos el grupo, para los otros usuarios sí
            for (Element usuario : archivoXML.getUsuarios()) {
                if (!archivoXML.getTipoTexto(usuario.getChildText("nombre")).equals("admin")) {
                    out.println("<tr>");
                    out.println("<td>" + usuario.getChildText("nombre") + "</td>" + "<td>" + archivoXML.getTipoTexto(usuario.getChildText("nombre")) + "</td>" + "<td>" + archivoXML.getGrupoTexto(usuario.getChildText("nombre")) + "</td>");
                    out.println("</tr>");
                } else {
                    out.println("<tr>");
                    out.println("<td>" + usuario.getChildText("nombre") + "</td>" + "<td>" + archivoXML.getTipoTexto(usuario.getChildText("nombre")) + "</td>" + "<td>No aplica</td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println ("</div>");
            out.println("<a href='usuarios' class='btn btn-warning'>Regresar</a>");
            out.println ("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }
    }
}
