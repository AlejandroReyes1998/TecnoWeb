/*
 Servlet que da de alta un grupo
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

public class registroGrupo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //recuperamos los datos del formulario
        String grupo = request.getParameter("grupo");
        String id = (String) session.getAttribute("username");
        String nomx = (String) session.getAttribute("id");
        PrintWriter out = response.getWriter();
        int e = 0;
        if (id != null && nomx != null) {
            if (!"".equals(grupo)) {
                String realpath = (String) session.getAttribute("elcaminoreal");
                //sobreescribiremos sobre el archivo de grupos
                File fichero = new File(realpath + "grupos.xml");
                if (fichero.isFile()) {
                    try {
                        //generamos un nuevo nodo que será llenado con el nombre del grupo
                        lectorG archivoXML = new lectorG(realpath + "grupos.xml");
                        SAXBuilder builder = new SAXBuilder();
                        Document doc = (Document) builder.build(fichero);
                        Element raiz = doc.getRootElement();
                        for (Element grupoz : archivoXML.getGrupos()) {
                            if (grupoz.getText().equals(grupo)) {
                                e++;
                            }
                        }//si el grupo no ha sido registrado previamente se da de alta
                        if (e == 0) {
                            Element grupox = new Element("grupo");
                            grupox.setText(grupo);
                            raiz.addContent(grupox);
                            XMLOutputter xmlOutput = new XMLOutputter();
                            xmlOutput.setFormat(Format.getPrettyFormat());
                            //sobreescribimos el archivo de grupos
                            xmlOutput.output(doc, new FileWriter(realpath + "grupos.xml"));
                            out.println("<script>alert('Nuevo grupo registrado');");
                            out.println("location='inicioadmin'</script>'");
                        } else {
                            out.println("<script>alert('Este grupo ya existe');");
                            out.println("location='altaG'</script>'");
                        }

                    } catch (IOException | JDOMException io) {
                        out.println(io);
                    }
                } else {
                    out.println("error");
                    //response.sendRedirect("index.html");
                }
            } else {
                out.println("<script>alert('Has dejado el campo vacío');");
                out.println("location='altaG'</script>'");
            }
            out.println("<a href='inicioadmin'>regresar</a>");
        } else {
            response.sendRedirect("index.html");
        }
    }
}
