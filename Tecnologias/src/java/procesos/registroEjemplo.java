/*
 Servlet que sube el ejemplo al xml
 */
package procesos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author alejandro
 */
public class registroEjemplo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String ejercicio = (String) session.getAttribute("ejercicio");
        String userName = (String) session.getAttribute("username");
        //obtenemos los datos del canvas y de la persona que lo hizo
        String id = (String) session.getAttribute("id");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String canvas = request.getParameter("canv");
        String grupo = (String) session.getAttribute("grupo");
        int i = 0;
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            //sobreescribiremos sobre el archivo donde estarán contenidos los ejemplos
            File fichero = new File(realpath + "ejemplos.xml");
            
            if (fichero.isFile()) {
                try {
                    SAXBuilder builder = new SAXBuilder();
                    Document doc = (Document) builder.build(fichero);
                    Element raiz = doc.getRootElement();
                    List list = raiz.getChildren("ejemplo");                    
                    for (int ix = 0; ix < list.size(); ix++) {
                        //Si ya existía el ejemplo solo sobreescribiremos sobre lo que ya tenía
                        Element e = (Element) list.get(ix);
                        if (e.getAttributeValue("ejercicio").equals(ejercicio)) {
                            i = 1;
                            e.getChild("canvas").setText(canvas);
                            XMLOutputter xmlOutput = new XMLOutputter();
                            xmlOutput.setFormat(Format.getPrettyFormat());
                            //sobreescribimos el archivo con los nuevos datos
                            xmlOutput.output(doc, new FileWriter(realpath + "ejemplos.xml"));
                            break;
                        }
                    }
                    if (i == 0) {
                        //No existe un canvas para ejemplo
                        Element ejemplo = new Element("ejemplo");
                        Element can = new Element("canvas");
                        //llenamos el documento con los datos del canvas (extraidos del json)
                        can.setText(canvas);
                        ejemplo.addContent(can);
                        raiz.addContent(ejemplo);
                        //datos de la persona que subió el ejemplo
                        ejemplo.setAttribute("grupo", grupo);
                        ejemplo.setAttribute("ejercicio", ejercicio);
                        XMLOutputter xmlOutput = new XMLOutputter();
                        xmlOutput.setFormat(Format.getPrettyFormat());
                        //sobreescribimos el archivo con los nuevos datos
                        xmlOutput.output(doc, new FileWriter(realpath + "ejemplos.xml"));
                    }

                } catch (IOException | JDOMException io) {
                    out.println(io);
                }
            } else {
                out.println("error");
                //response.sendRedirect("index.html");
            }
            out.println("<a href='inicioprofe'>regresar</a>");
        } else {
            response.sendRedirect("index.html");
        }
    }
}