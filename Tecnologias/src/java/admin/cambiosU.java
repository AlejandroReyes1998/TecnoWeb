package admin;

//SELECCIONA EL USUARIO A CAMBIAR
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lector;

/**
 *
 * @author Alejandro
 */
public class cambiosU extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        lector archivoXML = new lector(realpath + "usuarios.xml");
        PrintWriter out = response.getWriter();
        String id = (String) session.getAttribute("id");
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
            out.println ("<div class='container'>");
            out.println ("<div class='form-alta'>");
            out.println ("<div class='col-xs-4 col-xs-offset-4 formulario'>");
            out.println ("<div class='form-alta-top'><h3>Buscar usuario...</h3></div>");
            out.println ("<div class='form-alta-campos'>");
            out.println("<div class='row'>");
            out.println("<div class='col-xs-9'>");
            out.println("<form action='cambio' method='get'>");            
            out.println("<div class='form-group'>");
            out.println("<select name='username' class='form-control'>");
            //Recurperamos los usuarios del archivo xml, solo los usuarios que no son administradores pueden ser modificados
            for (Element usuario : archivoXML.getUsuarios()) {
                if (archivoXML.getTipoTexto(usuario.getChildText("nombre")).equals("admin")) {
                } else {
                    out.println("<option value=" + usuario.getChildText("nombre") + ">" + usuario.getChildText("nombre") + "</option>");
                }
            }
            out.println("</select>");
            out.println ("</div>");
            out.println ("</div>");
            out.println("<button class='btn pull-right btn-primary'>Change</button>");
            out.println("<a href='usuarios' class='btn btn-warning'>Regresar</a>");           
            out.println("</form>");
            out.println ("</div>");
            out.println ("</div>");
            out.println ("</div>");            
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }

}
