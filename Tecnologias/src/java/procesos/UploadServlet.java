/*
 Servlet que sube archivos
 */
package procesos;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class UploadServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024 * 1024;
    private int maxMemSize = 4 * 1024 * 1024;
    private File file;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        // Verificamos que exista un archivo a enviar
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        String realpath = (String) session.getAttribute("elcaminoreal");
        filePath = request.getRealPath("/") + "files/";
        //Si es multiparte se puede subir al servidor
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        //Si no lo es no se sube
        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>alert('Ha ocurrido un error con el archivo');");
            out.println("location='inicioprofe'</script>'");
            out.println("<a href='inicioprofe'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Tamaño máximo alocado en memoria
        factory.setSizeThreshold(maxMemSize);

        // EStablecemos el repositorio donde se subirá el archivo
        factory.setRepository(new File(filePath));

        //Creamos un manejador de archivo
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Alocamos el tamaño máximo en servidor
        upload.setSizeMax(maxFileSize);

        try {
            // Vemos el archivo a subir 
            List fileItems = upload.parseRequest(request);

            // Lo añadiremos al proceso de subida
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Datos del archivo
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // EScribimos el archivo en el repositorio alocado
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    //Registramos el nombre del archivo subido, aligual de los datos de quién lo subió
                    //y lo guardamos en el archivo xml
                    File fichero = new File(realpath + "archivos.xml");
                    if (fichero.isFile()) {
                        try {
                            SAXBuilder builder = new SAXBuilder();
                            Document doc = (Document) builder.build(fichero);
                            Element raiz = doc.getRootElement();
                            Element archivo = new Element("archivo");
                            archivo.setAttribute("idprofe", id);
                            archivo.setText(fileName);
                            raiz.addContent(archivo);
                            XMLOutputter xmlOutput = new XMLOutputter();
                            xmlOutput.setFormat(Format.getPrettyFormat());
                            xmlOutput.output(doc, new FileWriter(realpath + "archivos.xml"));
                            out.println("<script>alert('Has subido: "+fileName+" de manera correcta!');");
                            out.println("location='inicioprofe'</script>'");
                        } catch (IOException | JDOMException io) {
                            out.println(io);
                        }
                    } else {
                        out.println("error");
                        //response.sendRedirect("index.html");
                    }
                }
            }
            out.println("<a href='inicioprofe'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}
