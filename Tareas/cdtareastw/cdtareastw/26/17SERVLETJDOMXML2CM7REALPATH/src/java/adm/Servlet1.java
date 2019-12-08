package adm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import org.jdom.output.XMLOutputter;
import java.io.FileWriter;

public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String ruta=request.getRealPath("/");
            Element raiz = new Element("ROOT");
            Element hoja = new Element("hoja");
            hoja.setAttribute("numerodehojas", "4");
            hoja.setText("VALORDELNODO");
            raiz.addContent(hoja);
            Document newdoc = new Document(raiz);
            XMLOutputter fmt = new XMLOutputter();
            try (FileWriter writer = new FileWriter(ruta+"hola.xml")) {
                fmt.output(newdoc, writer);
                writer.flush();
                
            }
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }

    }

}
