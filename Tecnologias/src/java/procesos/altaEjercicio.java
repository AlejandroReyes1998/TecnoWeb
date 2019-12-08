/*
 servlet que sube el ejercicio
 */
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
import javax.servlet.http.HttpSession;
import org.jdom.input.SAXBuilder;

public class altaEjercicio extends HttpServlet {

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
        String npreg = (String) session.getAttribute("npreg");
        String text = (String) session.getAttribute("text");
        String nom = (String) session.getAttribute("nom");

        if (userName != null && id != null) {
            //variables auxiliares
            int pi = Integer.parseInt(npreg);
            int x = 1;
            int j = 0;
            int k = 0;
            PrintWriter out = response.getWriter();
            //sobreescribiremos en el archivo de ejercicios
            File fichero = new File(realpath + "ejercicios.xml");
            procesos.lectorE archivoXML = new procesos.lectorE(realpath + "ejercicios.xml");
            if (fichero.isFile()) {
                //ciclo for que compara que las opciones contengan la respuesta correcta
                for (int i = 0; i < pi; i++) {
                    if (request.getParameter("correcta" + i).equals(request.getParameter("opc1" + i)) || request.getParameter("correcta" + i).equals(request.getParameter("opc2" + i)) || request.getParameter("correcta" + i).equals(request.getParameter("opc3" + i))) {
                        j++;
                    } else {
                        j = 0;
                    }
                }
                //la respuesta correcta está en las opciones
                if (j != 0) {
                    try {
                        //definimos la estructura del xml y su contenido/atributos
                        SAXBuilder builder = new SAXBuilder();
                        Document doc = (Document) builder.build(fichero);
                        Element raiz = doc.getRootElement();
                        Element ejercicio = new Element("ejercicio");
                        Element nombre = new Element("nombre");
                        nombre.setText(nom);
                        ejercicio.addContent(nombre);
                        Element texto = new Element("texto");
                        //ciclo for que cuenta cuántos ejericiccios asignados al grupo existen
                        for (Element ejerciciox : archivoXML.getEjercicios()) {
                            if (archivoXML.getGrupoTexto(ejerciciox.getChildText("nombre")).equals(grupo)) {
                                x++;
                            }
                        }
                        //asignación de datos (se llena el numejercicio con base el número de ejercicios 
                        //previamente registrados)
                        ejercicio.setAttribute("numejercicio", Integer.toString(x));
                        ejercicio.setAttribute("grupo", grupo);
                        ejercicio.setAttribute("numpreguntas", npreg);
                        ejercicio.addContent(texto);
                        texto.setText(text);
                        //ciclo for que llena el xml con base al número de preguntas específicadas
                        for (int i = 0; i < pi; i++) {
                            if (!request.getParameter("preg" + i).equals("") && !request.getParameter("opc1" + i).equals("") && !request.getParameter("opc2" + i).equals("") && !request.getParameter("opc3" + i).equals("") && !request.getParameter("correcta" + i).equals("")) {
                                Element pregunta = new Element("pregunta");
                                Element opcion1 = new Element("opcion1");
                                Element opcion2 = new Element("opcion2");
                                Element opcion3 = new Element("opcion3");
                                Element reactivo = new Element("reactivo");                              
                                reactivo.setText(request.getParameter("preg" + i));
                                opcion1.setText(request.getParameter("opc1" + i));
                                opcion2.setText(request.getParameter("opc2" + i));
                                opcion3.setText(request.getParameter("opc3" + i));
                                pregunta.setAttribute("numero", Integer.toString(i));
                                pregunta.setAttribute("correcta", request.getParameter("correcta" + i));
                                ejercicio.addContent(pregunta);
                                pregunta.addContent(reactivo);
                                pregunta.addContent(opcion1);
                                pregunta.addContent(opcion2);
                                pregunta.addContent(opcion3);
                                k++;
                            } else {
                                break;
                            }
                        }
                        //si los campos estaban completps
                        if (k == (pi)) {
                            //asigna el contenido al nodo raíz
                            raiz.addContent(ejercicio);
                            //sobreescribimos el archivo xml con los nuevos datos
                            XMLOutputter xmlOutput = new XMLOutputter();
                            xmlOutput.setFormat(Format.getPrettyFormat());
                            xmlOutput.output(doc, new FileWriter(realpath + "ejercicios.xml"));
                            out.println("<script>alert('Has subido un ejercicio de manera correcta');");
                            out.println("location='inicioprofe'</script>'");
                        } else {
                            //EL usuario no llenó todos los campos
                            out.println("<script>alert('Has dejado algún campo vacío');");
                            out.println("location='inicioprofe'</script>'");
                        }

                    } catch (IOException | JDOMException io) {
                        //exception al llenar el doc
                        out.println(io);
                    }
                } else {
                    //el usuario no puso la respuesta correcta entre las opciones
                    out.println("<script>alert('No puso la respuesta entre las opciones de alguna pregunta! ');");
                    out.println("location='inicioprofe'</script>'");
                }
            } else {
                out.println("<script>alert('ocurrió un error al leer el archivo de ejercicios ');");
                out.println("location='inicioprofe'</script>'");
            }
            out.println("<a href='inicioprofe'>regresar</a>");
        } else {
            response.sendRedirect("index.html");
        }
    }
}
