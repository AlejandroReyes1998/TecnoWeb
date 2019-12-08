/*
 Servlet que se encarga de recibir los datos del nodo a cambiar
 */
package procesos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
public class cambio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        String userName = (String) session.getAttribute("username");
        String nombreAnt = request.getParameter("username");
        String id = (String) session.getAttribute("id");
        if (userName != null && id != null) {
            if (nombreAnt != null) {
                session.setAttribute("old", nombreAnt);
                String pass = "";
                String tipo = "";
                int i = 0;
                String realpath = (String) session.getAttribute("elcaminoreal");
                File fichero = new File(realpath + "usuarios.xml");
                lector archivoXML = new lector(realpath + "usuarios.xml");
                lectorG archivoXML2 = new lectorG(realpath + "grupos.xml");
                //recuperamos los datos anteriores del ususario
                for (Element usuario : archivoXML.getUsuarios()) {
                    if (usuario.getChildText("nombre").equals(nombreAnt)) {
                        pass = usuario.getChildText("password");
                        tipo = archivoXML.getTipoTexto(usuario.getChildText("nombre"));
                        session.setAttribute("iduser", usuario.getAttributeValue("id"));
                        break;
                    }
                }
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
                out.println("<div class='form-alta-top'><h3>Registre los nuevos datos</h3></div>");
                out.println("<div class='form-alta-campos'>");
                out.println("<form action='changeXML' method='get'>");
                //llena con los nuevos datos
                out.println("<div class='form-group'>");
                out.println("Nombre: <input type='text' value='" + nombreAnt + "' name='usernamenew' class='form-control' pattern='[A-Za-z]+' title='Solo letras.' required/>");
                out.println("</div>");
                out.println("<div class='form-group'>");
                out.println("Pass: <input type='password' value='" + pass + "'name='pass' class='form-control' pattern='[A-Za-z0-9]+' title='Solo letras y numeros' required/>");
                out.println("</div>");
                out.println("<div class='form-group'>");
                out.println("Tipo Usuario: <select name='tipo' class='form-control'/>");
                out.println("<option value='profesor'>PROFESOR</option>");
                out.println("<option value='alumno'>ALUMNO</option>");
                out.println("</select>");
                out.println("</div>");
                out.println("<div class='form-group'>");
                out.println("Grupo: <select name='grupo' class='form-control'/>");
                //recuperamos los grupos del archivo de grupos
                for (Element grupo : archivoXML2.getGrupos()) {
                    out.println("<option value='" + grupo.getText() + "'>" + grupo.getText() + "</td>");
                }
                out.println("</select>");
                out.println("</div>");
                out.println("<button class='btn pull-right btn-success'>REGISTRAR</button>");
                out.println("</form>");
                out.println("<a href='usuarios' class='btn  pull-left btn-warning'>Regresar</a>");
                out.println("<div class='clearfix bottom-left'></div>");
                out.println("<div class='clearfix bottom-right'></div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<script>alert('No ha seleccionado usuario');");
                out.println("location='cambiosU'</script>'");
            }

        } else {
            response.sendRedirect("index.html");
        }
    }
}
