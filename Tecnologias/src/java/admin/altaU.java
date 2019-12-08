package admin;
/*Pedimos los datos del usuario que el administrador quiere registrar*/
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorG;

public class altaU extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        PrintWriter out = response.getWriter();
        lectorG archivoXML = new lectorG(realpath + "grupos.xml");
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
            //Para registrar a un usuario necesitamos su nombre, su contraseña, el tipo de usuario que será (profesor/alumno)
            //y el grupo, para ello deberán existir grupos previamente en el sistema
            out.println ("<div class='container'>");
            out.println ("<div class='col-xs-4 col-xs-offset-4 form-alta'>");
            out.println ("<div class='form-alta-top'><h1>Registro</h1></div>");
            out.println ("<div class='form-alta-campos'>");
            out.println("<form action='registro' method='get'>");
            out.println("<div class='form-group'>");
            out.println("Nombre: <input type='text ' name='username' class='form-control' pattern='[A-Za-z]+' title='Solo letras' required/>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("Contraseña: <input type='password' name='pass' class='form-control' pattern='[A-Za-z0-9]+' title='Solo letras y numeros' required/>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("Tipo Usuario: <select name='tipo' class='form-control'/>");
            out.println("<option value='profesor'>PROFESOR</option>");
            out.println("<option value='alumno'>ALUMNO</option>");
            out.println("</select>");
            out.println("</div>");
            out.println ("<div class='form-group'>");
            out.println("Grupo: <select name='grupo' class='form-control'/>");
            //Recuperamos los grupos del archivo XML
            for (Element grupo : archivoXML.getGrupos()) {
                out.println("<option value='" + grupo.getText() + "'>" + grupo.getText() + "</td>");
            }
            out.println("</select>");
            out.println ("</div>");
            out.println("<a href='usuarios' class='btn pull-left btn-warning'>Regresar</a>"); //Alineamos el boton a la izquierda           
            out.println("<button class='btn pull-right btn-success'>REGISTRAR</button>");//Alineamos el boton a la derecha
            out.println("<div class='clearfix button-right'></div> ");
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
