package admin;
//servlet que elimina GRUPOS
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorG;

public class eliminarG extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        if (username != null && id != null) {
            //Llamamos al archivo xml que usaremos para buscar los grupos
            String realpath = (String) session.getAttribute("elcaminoreal"); 
            lectorG archivoXML = new lectorG(realpath + "grupos.xml");
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
            out.println ("<h2>Tabla de grupos</h2>");
            out.println("<div class='row'>");/*Separamos en una fila la tabla*/
            out.println("<table class='table table-bordered'>");
            out.println("<tr class='danger'>");
            out.println("<th>Nombre</th><th>Eliminar</th>");
            out.println("</tr>");
            //Recuperamos los GRUPOS del archivo xml y llamamos al servlet que se encarga de eliminar los registros mediante ajax
            for (Element grupo : archivoXML.getGrupos()) {               
                    out.println("<tr id='id" + grupo.getText() + "'>");
                    out.println("<td>" + grupo.getText() + "</td>" +"</td><td><button class='btn btn-warning' onClick='eliminarG(\"" + grupo.getText() + "\")' id='" +grupo.getText() + "'>Delete</button></td>");
                    out.println("</tr>");
            }
            out.println("</table>");
            out.println("</div>");
            out.println("<a href='grupos' class='btn btn-warning'>Regresar</a>");
            out.println ("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }

}
