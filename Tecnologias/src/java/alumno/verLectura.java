/*
 Despliega la lectura solicitada
 */
package alumno;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorL;
public class verLectura extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String lectura = request.getParameter("lectura");
        String id = (String) session.getAttribute("id");
        lectorL archivoXML = new lectorL(realpath + "lecturas.xml");
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lectura</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/alumno.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println ("<div class='container'>");
            out.println("<div class='col-xs-8 col-xs-offset-2 formulario'>");
            for (Element ejercicio : archivoXML.getLecturas()) {
                //busca lectura y título
                if(ejercicio.getChildText("nombre").equals(lectura)){
                    out.println ("<div class='formulario-top'><h1>"+ejercicio.getChildText("nombre")+"</h1></div>");
                    out.println("<div class='lectura'><p>"+ejercicio.getChildText("texto")+"</p>");
                    if (!ejercicio.getChildText ("video").equals("Ninguno")) {
                    out.println("<video src='files/" + ejercicio.getChildText ("video") + "' width=\"500\" height=\"400\" controls></video>");
                    }
                    out.println ("</div>");
                    break;
                }
            }
            out.println("<a href='inicioalumno' class='btn btn-warning'>Regresar</a>");
            out.println("</div>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
