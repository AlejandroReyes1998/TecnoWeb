/*
 lector de archivo xml de grupos
 */
package procesos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class lectorG {

    //Constructor
    public lectorG(String ruta) {
        builder = new SAXBuilder();
        rutaXML = ruta;
        try {
            bdXML = builder.build(rutaXML);
            raiz = bdXML.getRootElement();
            grupos = raiz.getChildren("grupo");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean cambio(String old, String newgrupo, String rutaGuardado) {
        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).getText().equals(old)){
                grupos.get(i).setText(newgrupo);
            }
        }        
        XMLOutputter xmlOutput = new XMLOutputter();

        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(bdXML, new FileWriter(rutaGuardado));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
//lista de grupos

    public List<Element> getGrupos() {
        System.out.println(grupos);
        return grupos;
    }
    public boolean eliminar(String id, String rutaGuardado) {
        Element e = getNOMBRE(id);
        raiz.removeContent(e);

        XMLOutputter xmlOutput = new XMLOutputter();

        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(bdXML, new FileWriter(rutaGuardado));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    private SAXBuilder builder;
    private String rutaXML;
    private Document bdXML;
    private Element raiz;
    private List<Element> grupos;

    private Element getNOMBRE(String id) {
        Element user = null;
        for (Element grupo : grupos) {
            if (grupo.getText().matches(id)) {
                user = grupo;
                break;
            }
        }
        return user;
    }

    
}
