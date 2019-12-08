/*
 Servlet que da de alta usuarios
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

public class registro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Recuperamos los datos ṕroporcionados por el admin
        HttpSession session = request.getSession();
        String nom = request.getParameter("username");
        String pass = request.getParameter("pass");
        String tipo = request.getParameter("tipo");
        String grupo = request.getParameter("grupo");
        String id = (String) session.getAttribute("username");
        String nomx = (String) session.getAttribute("id");
        PrintWriter out = response.getWriter();
        int e = 0;
        if (id != null && nomx != null) {
            //Si los datos no están vacíos...
            if (!"".equals(nom) && !"".equals(pass) && !"".equals(tipo) && !"".equals(grupo)) {
                //Sobreescribiremos sobre el archivo xml de usuarios
                String realpath = (String) session.getAttribute("elcaminoreal");
                File fichero = new File(realpath + "usuarios.xml");
                if (fichero.isFile()) {
                    try {
                        lector archivoXML = new lector(realpath + "usuarios.xml");
                        SAXBuilder builder = new SAXBuilder();
                        Document doc = (Document) builder.build(fichero);
                        Element raiz = doc.getRootElement();
                        for (Element usuario : archivoXML.getUsuarios()) {
                            if (usuario.getChildText("nombre").equals(nom)) {
                                e++;
                            }
                        }
                        if (e == 0) {
                            //generamos un id para el nuevo usuario, y se lo asignamos, al igual que
                            //le llenamos el registro en el xml
                            int i = random();
                            Element usuario = new Element("usuario");
                            Element nombreU = new Element("nombre");
                            Element passU = new Element("password");
                            nombreU.setText(nom);
                            passU.setText(pass);
                            raiz.addContent(usuario);
                            usuario.setAttribute("id", Integer.toString(i));
                            usuario.setAttribute("tipo", tipo);
                            usuario.setAttribute("grupo", grupo);
                            usuario.addContent(nombreU);
                            usuario.addContent(passU);
                            //añadimos atributos y contenido al nuevo nodo
                            XMLOutputter xmlOutput = new XMLOutputter();
                            xmlOutput.setFormat(Format.getPrettyFormat());
                            //sobrescribimos el archivo de usuarios
                            xmlOutput.output(doc, new FileWriter(realpath + "usuarios.xml"));
                             out.println("<script>alert('Usuario registrado');");
                            out.println("location='inicioadmin'</script>'");
                        } else {
                            out.println("<script>alert('Este usuario ya existe');");
                            out.println("location='altaU'</script>'");
                        }

                    } catch (IOException | JDOMException io) {
                        out.println(io);
                    }
                } else {
                    //ocurrio un error con el archivo
                    out.println("<script>alert('Ocurrió un error con el archivo');");
                    out.println("location='inicioadmin'</script>'");
                }
            } else {
                //los datos estaban vaciós
                out.println("<script>alert('Un campo o más se encuentran vacíos');");
                out.println("location='inicioadmin'</script>'");
                //response.sendRedirect("index.html");
            }
            out.println("<a href='usuarios'>Regresar</a>");
        } else {
            response.sendRedirect("index.html");
        }
    }

    int random() {
        return 4 + (int) (Math.random() * (10000 - 4) + 1);
    }
}
