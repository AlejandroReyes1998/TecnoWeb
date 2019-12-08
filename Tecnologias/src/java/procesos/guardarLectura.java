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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.jdom.input.SAXBuilder;

public class guardarLectura extends HttpServlet {

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
        String text = request.getParameter("text");
        String vid = request.getParameter("video");
        String nom = request.getParameter("nombre");
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            if (text != null && nom != null) {
                File fichero = new File(realpath + "lecturas.xml");
                if (fichero.isFile()) {
                    try {
                        SAXBuilder builder = new SAXBuilder();
                        Document doc = (Document) builder.build(fichero);
                        Element raiz = doc.getRootElement();
                        Element lectura = new Element("lectura");
                        Element nombre = new Element("nombre");
                        Element texto = new Element("texto");
                        Element video = new Element("video");
                        nombre.setText(nom);
                        texto.setText(text);
                        video.setText(vid);
                        lectura.addContent(nombre);
                        lectura.addContent(texto);
                        lectura.addContent (video);
                        lectura.setAttribute("grupo", grupo);
                        raiz.addContent(lectura);
                        //añadimos atributos y contenido al nuevo nodo
                        XMLOutputter xmlOutput = new XMLOutputter();
                        xmlOutput.setFormat(Format.getPrettyFormat());
                        //sobrescribimos el archivo de usuarios
                        xmlOutput.output(doc, new FileWriter(realpath + "lecturas.xml"));
                        out.println("<script>alert('Lectura Registrada!');");
                        out.println("location='inicioprofe'</script>'");
                    } catch (IOException | JDOMException e) {
                        out.println("<script>alert('Ha ocurrido un error: " + e + "');");
                        out.println("location='inicioprofe'</script>'");
                    }
                }

            } else {
                out.println("<script>alert('Has dejado algún campo vacío');");
                out.println("location='subirlectura?nom=" + nom + "?text=" + text + "'</script>'");
            }
        } else {
            response.sendRedirect("index.html");
        }
    }
}
