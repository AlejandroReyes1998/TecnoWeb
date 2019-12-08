
package profesor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorA;

public class subirLectura extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Datos de la persona que subirá el ejercicio
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String id = (String) session.getAttribute("id");
        lectorA archivoXML = new lectorA(realpath + "archivos.xml");
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Profesor</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/profesor.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='col-xs-6 col-xs-offset-3 form-prof'>");
            out.println("<div class='form-prof-top'>");
            out.println("<h4>Registro de lectura</h4>");
            out.println("</div>");
            out.println("<div class='form-prof-campos'>");
            out.println("<form action='guardarLectura' method='get'>");
            //Nombre del ejercicio
            out.println ("<div class='form-group'>");
            out.println("Nombre <input type='text' name='nombre' class='form-control' required/>");
            out.println ("</div>");
            //Lectura recomendada para el ejercicio
            out.println ("<div class='form-group'>");
            out.println("Texto <textarea name='text' class='form-control' rows='8'></textarea>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("Video: <select name='video' class='form-control'/>");
            out.println ("<option value='Ninguno'>Ninguno</option>");
            for (Element e : archivoXML.getArchivos()) {
                if (e.getAttributeValue("idprofe").equals(id)) {
                    if (e.getText().contains(".mp4")) {
                        out.println ("<option value='"+e.getText()+"'>"+e.getText()+"</option>");
                    }
                    }
            }
            out.println("</select>");
            out.println ("</div>");
            out.println("<div class='mybutton'><button class='btn form-control btn-info'>Registrar</button></div>");
            out.println("</form>");
            out.println("<a href='inicioprofe' class='btn form-control btn-warning'>Regresar</a>");
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