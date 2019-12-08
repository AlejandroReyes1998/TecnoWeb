package profesor;
//Asignamos el ejemplo de algún ejercicio previamente registrado
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorL;

public class ejercicioCanvas extends HttpServlet {

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
        //Recuperamos los ejercicios del archivo xml
        lectorL archivoXML = new lectorL(realpath + "lecturas.xml");
        if (userName != null && id != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Crear ejercicio</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/profesor.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='col-xs-4 col-xs-offset-4 form-prof'>");
            out.println("<div class='form-prof-top'><h4>Crear ejercicio</h4></div>");
            out.println("<form method='get' action='crearEjercicioCanvas'>");
            out.println ("<div class='form-prof-campos'>");
            out.println ("<div class='form-group'>");
            out.println("Instruccion de ejercicio <input type='text' name='instruccion' class='form-control' required/>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("Lectura: <select name='lectura' class='form-control'>");
            //por cada lectura generamos una opción
            for (Element lecturaX : archivoXML.getLecturas()) {
                if(lecturaX.getAttributeValue("grupo").equals(grupo))
                out.println("<option value='" + lecturaX.getChildText("nombre") + "'>" + lecturaX.getChildText("nombre") + "</option>");
            }
            out.println("</select>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<button class='btn form-control btn-info'>Editar canvas</button>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<a href='inicioprofe' class='btn form-control btn-warning'>Regresar</a>");
            out.println ("</div>");          
            out.println("</form>");
            out.println ("</div>");
            out.println ("</div>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
