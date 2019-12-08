package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.lectorG;

public class cambiosG extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        lectorG archivoXML = new lectorG (realpath + "grupos.xml");
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
            out.println ("<div class='form-alta-top'><h3>Buscar grupo...</h3></div>");
            out.println ("<div class='form-alta-campos'>");
            out.println("<div class='row'>");
            out.println("<div class='col-xs-9'>");
            out.println("<form action='cambioG' method='get'>");            
            out.println("<div class='form-group'>");
            out.println("<select name='namegrupo' class='form-control'>");
            //Recurperamos los grupos
            for (Element grupos : archivoXML.getGrupos()) {
                    out.println("<option value=" + grupos.getText() + ">" + grupos.getText() + "</option>");
            }
            out.println("</select>");
            out.println ("</div>");
            out.println ("</div>");
            out.println("<button class='btn pull-right btn-primary'>Change</button>");
            out.println("<a href='grupos' class='btn btn-warning'>Regresar</a>");           
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
