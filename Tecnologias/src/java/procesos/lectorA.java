package procesos;
//lector de ARCHIVOS (xml)
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
/**
 *
 * @author alejandro
 */
public class lectorA {
     //constructor
    public lectorA(String ruta) {
        builder = new SAXBuilder();
        rutaXML = ruta;
        try {
            bdXML = builder.build(rutaXML);
            raiz = bdXML.getRootElement();
            archivos = raiz.getChildren("archivo");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Element> getArchivos() {
        return archivos;
    }
    private SAXBuilder builder;
    private String rutaXML;
    private Document bdXML;
    private Element raiz;
    private List<Element> archivos;
}
