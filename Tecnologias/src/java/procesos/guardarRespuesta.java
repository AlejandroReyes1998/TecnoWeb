package procesos;
//Desplegamos los ejercicios que el profesor del grupo del alumno ha subido

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class guardarRespuesta extends HttpServlet {

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
        String nombreE = (String) session.getAttribute("nombreE");
        String numEjer = (String) session.getAttribute("numeroEjer");
        String numero = (String) session.getAttribute("numPreg");
        int c = 0;
        float calif = 0;
        if (userName != null && id != null) {

            File fichero = new File(realpath + "respuestas.xml");
            //lectorR archivoXML = new lectorR(realpath + "respuestas.xml");
            if (fichero.isFile()) {
                try {
                    SAXBuilder builder = new SAXBuilder();
                    Document doc = (Document) builder.build(fichero);
                    Element raiz = doc.getRootElement();
                    Element ejercicio = new Element("ejercicio");
                    ejercicio.setAttribute("grupo", grupo);
                    ejercicio.setAttribute("nombre", nombreE);
                    ejercicio.setAttribute("alumno", userName);
                    for (int i = 0; i < Integer.parseInt(numero); i++) {
                        //guardamos las respuestas en el xml
                        String correcta = (String) session.getAttribute("correcta" + i);
                        Element respuesta = new Element("respuesta");
                        //respuesta.setAttribute("correcta", ejercicios.respuestaCorrecta(grupo, numEjer, i));
                        respuesta.setAttribute("numero", Integer.toString(i));
                        respuesta.setText(request.getParameter("respuesta" + i));
                        ejercicio.addContent(respuesta);
                        if (request.getParameter("respuesta" + i).equals(correcta)) {
                            c++;
                        }
                    }
                    calif = (float) (c / Integer.parseInt(numero))*10;
                    raiz.addContent(ejercicio);
                    XMLOutputter xmlOutput = new XMLOutputter();
                    xmlOutput.setFormat(Format.getPrettyFormat());
                    xmlOutput.output(doc, new FileWriter(realpath + "respuestas.xml"));
                    File fichero2 = new File(realpath + "calificaciones.xml");
                    if (fichero2.isFile()) {
                        try {
                            SAXBuilder builder2 = new SAXBuilder();
                            Document doc2 = (Document) builder2.build(fichero2);
                            Element raiz2 = doc2.getRootElement();
                            Element calificacion = new Element("calificacion");
                            calificacion.setAttribute("grupo", grupo);
                            calificacion.setAttribute("alumno", userName);
                            calificacion.setAttribute("ejercicio", nombreE);
                            calificacion.setText(Float.toString(calif));
                            raiz2.addContent(calificacion);
                            XMLOutputter xmlOutput2= new XMLOutputter();
                            xmlOutput2.setFormat(Format.getPrettyFormat());
                            xmlOutput2.output(doc2, new FileWriter(realpath + "calificaciones.xml"));
                            out.println("<script>alert('Calificacion: "+calif+"');");
                            out.println("location='inicioalumno'</script>'");
                        } catch (IOException | JDOMException io) {
                            //exception al llenar el doc
                            out.println(io);
                        }
                    }
                } catch (IOException | JDOMException io) {
                    //exception al llenar el doc
                    out.println(io);
                }
            } else {
                out.println("Error");
            }
            out.println("<a href='inicioalumno'> Inicio </a>");
        } else {
            response.sendRedirect("index.html");
        }
    }

}
