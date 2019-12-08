package profesor;
//Servlet que permitirá seleccionar un archivo para subirlo a servidor
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class subirArchivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Datos de la persona que subirá el ejercicio
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String id = (String) session.getAttribute("id");
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
            out.println("<div class='form-prof-top'><h4>Selecciona el archivo a subir:</h4></div>");
            //Le pedimos un archivo, y este será procesado en el servlet de subida de archivos
            out.println("<form action = \"UploadServlet\" method = \"post\" enctype = \"multipart/form-data\">");
            out.println("<div class='form-prof-campos'>");
            out.println("<div class='form-group'>");
            out.println ("</div>");
            out.println("<div class='form-group'>");
            out.println("<input type = \"file\" name = \"file\" size = \"45\" />");            
            out.println ("</div>");
            out.println("<div class='form-group'>");
            out.println ("<button class='btn form-control btn-info'>Upload File</button>");
            out.println ("</div>");
            out.println("<div class='form-group'>");
            out.println("<a href='inicioprofe' class='btn form-control btn-warning'>Regresar</a>");
            out.println ("</div>");            
            out.println ("</form>");
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
