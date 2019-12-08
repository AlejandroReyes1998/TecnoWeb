package procesos;
//Servlet que registrará las preguntas y respuestas del ejercicio

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class registroPreg extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        //datos de la persona que subirá el ejercicio
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            String realpath = (String) session.getAttribute("elcaminoreal");
            String text = request.getParameter("text");
            String nom = request.getParameter("nombre");
            if (!"".equals(nom) && !"".equals(text) && !"".equals(request.getParameter("npreg"))) {
                //Datos generales del ejercicio
                session.setAttribute("text", text);
                session.setAttribute("nom", nom);
                session.setAttribute("npreg", request.getParameter("npreg"));
                int pi = Integer.parseInt(request.getParameter("npreg"));
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Profesor</title>");
                out.println("<script src='js/bootstrap.min.js'></script>");
                out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
                out.println("<link rel='stylesheet' href='css/profesor.css'>");
                out.println("</head>");
                out.println("<body>");
                //Con base al número de ṕreguntas solicitadas, generamos las preguntas y sus opciones
                out.println("<div class='container'>");
                out.println("<div class='col-xs-6 col-xs-offset-3 form-prof'>");
                out.println("<div class='form-prof-top'>");
                out.println("<h4>Registro de preguntas</h4>");
                out.println("</div>");
                out.println("<div class='form-prof-campos'>");
                out.println("<form action='altaEjercicio' method='get'>");
                for (int i = 0; i < pi; i++) {
                    out.println ("<div class='col-xs-4'>");
                    out.println("Pregunta " + (i + 1));
                    out.println ("</div>");
                    out.println ("<div class='col-xs-8 form-group'>");
                    out.println ("<input type='text' name='preg" + i + "'/>");
                    out.println ("</div>");
                    
                    out.println ("<div class='col-xs-4'>");
                    out.println("Opcion 1:");
                    out.println ("</div>");
                    out.println ("<div class='col-xs-8 form-group'>");
                    out.println ("<input type='text' name='opc1" + i + "'/>");
                    out.println ("</div>");

                    out.println ("<div class='col-xs-4'>");
                    out.println("Opcion 2:");
                    out.println ("</div>");
                    out.println ("<div class='col-xs-8 form-group'>");
                    out.println ("<input type='text' name='opc2" + i + "'/>");
                    out.println ("</div>"); 
                    
                    out.println ("<div class='col-xs-4'>");
                    out.println("Opcion 3:");
                    out.println ("</div>");
                    out.println ("<div class='col-xs-8 form-group'>");
                    out.println ("<input type='text' name='opc3" + i + "'/>");
                    out.println ("</div>"); 
                    
                    out.println ("<div class='col-xs-4'>");
                    out.println("Respuesta: ");
                    out.println ("</div>");
                    out.println ("<div class='col-xs-8 form-group'>");
                    out.println ("<input type='text' name='correcta" + i + "'/>");
                    out.println ("</div>");                    
                }
                out.println ("<div class='form-group'>");
                out.println("<button class='btn form-control btn-info'>Enviar</button>");
                out.println ("</div>");
                out.println ("</form>");
                out.println ("<div class='form-group'>");
                out.println("<a href='inicioprofe' class='btn form-control btn-warning'>Regresar</a>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<script>alert('Uno o más campos se encuentran vacíos');");
                out.println("location='subirejercicio'</script>'");
            }

        } else {
            response.sendRedirect("index.html");
        }

    }
}
