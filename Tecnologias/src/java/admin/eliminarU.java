package admin;
//servlet que elimina usuarios
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lector;

public class eliminarU extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        if (username != null && id != null) {
            //Llamamos al archivo xml que usaremos para buscar los usuarios
            String realpath = (String) session.getAttribute("elcaminoreal"); 
            lector archivoXML = new lector(realpath + "usuarios.xml");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrador</title>");
            out.println("<script type='text/javascript' src='js/admin.js'></script>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/administrador.css'>");            
            out.println("<script type='text/javascript' src='js/jquery.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='col-xs-3 col-xs-offset-4 tabla'>");
            out.println ("<h2>Tabla de usuarios</h2>");
            out.println("<div class='row'>");/*Separamos en una fila la tabla*/
            out.println("<table class='table table-bordered'>");
            out.println("<tr class='danger'>");
            out.println("<th>Nombre</th><th>Tipo</th><th>Eliminar</th>");
            out.println("</tr>");
            //Recuperamos los usuarios del archivo xml y llamamos al servlet que se encarga de eliminar los registros mediante ajax
            for (Element usuario : archivoXML.getUsuarios()) {
                //No se pueden eliminar los administradores
                if (archivoXML.getTipoTexto(usuario.getChildText("nombre")).equals("admin")) {
                } else {
                    out.println("<tr id='id" + usuario.getChildText("nombre") + "'>");
                    out.println("<td>" + usuario.getChildText("nombre") + "</td>" + "<td>" + archivoXML.getTipoTexto(usuario.getChildText("nombre")) + "</td><td><button class='btn btn-warning' onClick='eliminar(\"" + usuario.getChildText("nombre") + "\")' id='" + usuario.getChildText("nombre") + "'>Delete</button></td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println("</div>");
            out.println("<a href='usuarios' class='btn btn-warning'>Regresar</a>");
            out.println ("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }

}
