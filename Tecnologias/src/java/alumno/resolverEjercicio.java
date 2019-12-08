package alumno;
//Desplegamos los ejercicios que el profesor del grupo del alumno ha subido

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import procesos.*;

/**
 *
 * @author alejandro
 */
public class resolverEjercicio extends HttpServlet {

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
        lectorE archivoXML = new lectorE(realpath + "ejercicios.xml");
        String nombreE = request.getParameter("ejercicio");
        session.setAttribute("nombreE", nombreE);
        //Recuperamos el nombre de los ejercicios del archivo xml
        if (userName != null && id != null) {
            if (nombreE!=null) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Resolver ejercicio</title>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/alumno.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println ("<div class='container'>");
                out.println("<div class='col-xs-4 col-xs-offset-4 formulario'>");
                out.println ("<div class='formulario-top'><h4>Resolver ejercicio</h4></div>");
                out.println("<form method='get' action='guardarRespuesta'>");
                out.println("<div class='formulario-campos'>");
                for (Element ejercicio : archivoXML.getEjercicios()) {
                    //Si existen ejercicios/lecturas asociados al grupo del alumno, estos se despliegan
                    if (ejercicio.getChildText("nombre").equals(nombreE)) {
                        session.setAttribute("numPreg", ejercicio.getAttributeValue("numpreguntas"));
                        session.setAttribute("numeroEjer", ejercicio.getAttributeValue("numeroejercicio"));
                        int i = 0;
                        List<Element> lista = ejercicio.getChildren("pregunta");
                        //session.setAttribute("numpreguntas", lista.size());
                        for (Element pregunta : lista) {
                            out.println ("<div class='row'>");
                            out.println("<h3>" + pregunta.getChildText("reactivo") + "</h3>");
                            out.println ("</div>");
                            out.println ("<div class='form-group'>");
                            out.println("<input type='radio' class='form-control' name='respuesta" + i + "' value='" + pregunta.getChildText("opcion1") + "'/>" + pregunta.getChildText("opcion1"));
                            out.println ("</div>");
                            out.println ("<div class='form-group'>");
                            out.println("<input type='radio' class='form-control' name='respuesta" + i + "' value='" + pregunta.getChildText("opcion2") + "'/>" + pregunta.getChildText("opcion2"));
                            out.println ("</div>");
                            out.println ("<div class='form-group'>");
                            out.println("<input type='radio' class='form-control' name='respuesta" + i + "' value='" + pregunta.getChildText("opcion3") + "'/>" + pregunta.getChildText("opcion3"));
                            out.println ("</div>");
                            session.setAttribute("correcta" + i, pregunta.getAttributeValue("correcta"));
                            i++;
                        }
                        break;
                    }
                }
                out.println ("<div class='form-group'>");
                out.println("<button class='btn form-control btn-info'>Enviar</button>");
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
                out.println("<script>alert('No has seleccionado una opción');");
                out.println("location='inicioalumno'</script>'");
            }

        } else {
            response.sendRedirect("index.html");
        }
    }
}
