package alumno;
//Desplegamos las lecturas que el profesor del grupo del alumno ha subido
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorL;

/**
 *
 * @author alejandro
 */
public class lecturas extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        lectorL archivoXML = new lectorL(realpath + "lecturas.xml");
        //Recuperamos el nombre de las lecturas del archivo xml
        if (userName != null && id != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Seleccion de lectura</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/alumno.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println ("<div class='container'>");
            out.println("<div class='col-xs-4 col-xs-offset-4 formulario'>");
            out.println ("<div class='formulario-top'><h4>Lecturas</h4></div>");
            out.println("<form method='get' action='verLectura'>");
            out.println("<div class='formulario-campos'>");
            out.println("<div class='form-group'>");
            out.println("Ejercicio: <select name='lectura' class='form-control'>");
            for (Element ejercicio : archivoXML.getLecturas()) {
                //Si existen ejercicios/lecturas asociados al grupo del alumno, estos se despliegan
                if(ejercicio.getAttributeValue("grupo").equals(grupo))
                out.println("<option value='" + ejercicio.getChildText("nombre") + "'>" + ejercicio.getChildText("nombre")+ "</option>");
            }
            out.println("</select>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<button class='btn form-control btn-success'>Enviar</button>");
            out.println ("</div>");
            out.println("</form>");
            out.println ("<div class='form-group'>");
            out.println("<a href='inicioalumno' class='btn form-control btn-warning'>Regresar</a>");
            out.println ("</div>");
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
