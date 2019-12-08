package procesos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.jdom.input.SAXBuilder;

public class subirCalificacion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //recuperamos los datos de la persona que subió el ejercicio
        String userName = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String grupo = (String) session.getAttribute("grupo");
        //recuperamos los datos generales del ejercicio
        String alumno = (String) session.getAttribute("alumno");
        String ejercicio = (String) session.getAttribute("ejercicio");
        String calif = request.getParameter("calificacion");
        int i = 0;
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            if (calif != null) {
                File fichero = new File(realpath + "calificaciones.xml");
                if (fichero.isFile()) {
                    try {
                        SAXBuilder builder = new SAXBuilder();
                        Document doc = (Document) builder.build(fichero);
                        Element raiz = doc.getRootElement();
                        List list = raiz.getChildren("calificacion");
                        for (int ix = 0; ix < list.size(); ix++) {
                            //Si ya existía el ejemplo solo sobreescribiremos sobre lo que ya tenía
                            Element e = (Element) list.get(ix);
                            if (e.getAttributeValue("ejercicio").equals(ejercicio) && e.getAttributeValue("alumno").equals(alumno)) {
                                i = 1;
                                break;
                            }
                        }
                        if (i == 1) {
                            out.println("<script>alert('Este ejercicio ya estaba calificado!');");
                            out.println("location='inicioprofe'</script>'");
                        } else {
                            Element calificacion = new Element("calificacion");
                            calificacion.setText(calif);
                            calificacion.setAttribute("grupo", grupo);
                            calificacion.setAttribute("alumno", alumno);
                            calificacion.setAttribute("ejercicio", ejercicio);
                            raiz.addContent(calificacion);
                            //añadimos atributos y contenido al nuevo nodo
                            XMLOutputter xmlOutput = new XMLOutputter();
                            xmlOutput.setFormat(Format.getPrettyFormat());
                            //sobrescribimos el archivo de usuarios
                            xmlOutput.output(doc, new FileWriter(realpath + "calificaciones.xml"));
                            out.println("<script>alert('Calificación Registrada!');");
                            out.println("location='inicioprofe'</script>'");
                        }
                    } catch (IOException | JDOMException e) {
                        out.println("<script>alert('Ha ocurrido un error: " + e + "');");
                        out.println("location='inicioprofe'</script>'");
                    }
                }

            } else {
                out.println("<script>alert('Has dejado algún campo vacío');");
                out.println("location='inicioprofe'</script>'");
            }
        } else {
            response.sendRedirect("index.html");
        }
    }
}
