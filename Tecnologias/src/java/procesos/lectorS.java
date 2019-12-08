/*
Lector de archivo xml de SOLUCIONES
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

/**
 *
 * @author alejandro
 */
public class lectorS {
    public lectorS(String ruta) {
        builder = new SAXBuilder();
        rutaXML = ruta;
        try {
            bdXML = builder.build(rutaXML);
            raiz = bdXML.getRootElement();
            soluciones = raiz.getChildren("solucion");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  public List<Element> getSoluciones() {
        System.out.println(soluciones);
        return soluciones;
    }
  
  //recupera el ejemplo (el elemento)
    public Element getSolucion(String nombre) {
        Element ejemplo = null;

        for (Element ej : soluciones) {
            if (ej.getAttributeValue("pregunta").equals(nombre)) {
                ejemplo = ej;
                break;
            }
        }
        return ejemplo;
    }
    //recupera el texto del ejemplo
    public String getS(String nombre) {
        Element ej = getSolucion(nombre);

        if (ej == null || ej.getAttributeValue("pregunta") == null) {
            return "";
        } else {
            return ej.getChildText("canvas");
        }
    }
     
    private SAXBuilder builder;
    private String rutaXML;
    private Document bdXML;
    private Element raiz;
    private List<Element> soluciones;
    //elimina un elemento del archivo de ejemplos
    public boolean eliminar(String id, String rutaGuardado) {
        Element e = getSolucion(id);
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

    
}