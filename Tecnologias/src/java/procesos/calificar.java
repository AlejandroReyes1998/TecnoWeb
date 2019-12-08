package procesos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class calificar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        String grupo = (String) session.getAttribute("grupo");
        String ejercicio = (String) session.getAttribute("ejercicio");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String alumno =  request.getParameter("usuario");
        lectorE archivoXML = new lectorE(realpath + "ejercicios.xml");
        lectorR archivoXML2 = new lectorR(realpath + "respuestas.xml");
        lectorC archivoXML3 = new lectorC(realpath + "calificaciones.xml");
        List respuestas = archivoXML2.getRespuestas();
        List ex = archivoXML.getEjercicios();
        List<String> correctas = new ArrayList<>();
        List<String> ress = new ArrayList<>();
        int numpreg = 0, calif = 0;
        float fin=0;
        //Guardamos las respuestas de las preguntas        
        if (userName != null && id != null) {
            for (int e = 0; e < ex.size(); e++) {
                Element el = (Element) ex.get(e);
                if (el.getChildText("nombre").equals(ejercicio)) {
                    List aux = el.getChildren("pregunta");
                    for (int a = 0; a < aux.size(); a++) {
                        Element cor = (Element) aux.get(a);
                        correctas.add(cor.getAttributeValue("correcta"));
                    }
                    numpreg = Integer.parseInt(el.getAttributeValue("numpreguntas"));
                }
                break;
            }
            //Comparamos las respuestas con lo que puso el usuario y con base a eso calificamos
            for (Element el: archivoXML2.getRespuestas()) {               
                if (el.getAttributeValue("nombre").equals(ejercicio) && el.getAttributeValue("alumno").equals(alumno)) {
                    ress = el.getChildren("respuesta");
                    for (int k = 0; k < numpreg; k++) {
                        if (ress.get(k).equals(correctas.get(k))) {
                            calif++;
                        }
                    }
                    break;
                }
            }
            fin=(float)(calif/numpreg)*10; //Ponderación
            File fichero = new File(realpath + "calificaciones.xml");
            if (fichero.isFile()) {
                //Llenamos con los datos de la califcación
                try {
                    SAXBuilder builder = new SAXBuilder();
                    Document doc = (Document) builder.build(fichero);
                    Element raiz = doc.getRootElement();
                    Element calificacion = new Element("calificacion");
                    calificacion.setAttribute("grupo",grupo);
                    calificacion.setAttribute("alumno",alumno);
                    calificacion.setAttribute("ejercicio",ejercicio);
                    calificacion.setText(Float.toString(fin));
                    raiz.addContent(calificacion);
                    XMLOutputter xmlOutput = new XMLOutputter();
                    xmlOutput.setFormat(Format.getPrettyFormat());
                    xmlOutput.output(doc, new FileWriter(realpath + "calificaciones.xml"));
                    out.println("<script>alert('Calificado correctamente');");
                    out.println("location='inicioprofe'</script>'");
                } catch (IOException | JDOMException io) {
                    //exception al llenar el doc
                    out.println(io);
                }

        } else {
            response.sendRedirect("index.html");
        }
    }

}
}
