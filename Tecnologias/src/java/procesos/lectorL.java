/*
 Lector de archivo xml de lecturas
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

public class lectorL {
    //constructor
    public lectorL(String ruta) {
        builder = new SAXBuilder();
        rutaXML = ruta;
        try {
            bdXML = builder.build(rutaXML);
            raiz = bdXML.getRootElement();
            lecturas = raiz.getChildren("lectura");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//lista de lecturas

    public List<Element> getLecturas() {
        System.out.println(lecturas);
        return lecturas;
    }
    //recuperamos el grupo (el elemento)
    public Element getGrupo(String nombre) {
        Element user = null;
        for (Element usuario : lecturas) {
            if (usuario.getChildText("nombre").matches(nombre)) {
                user = usuario;
                break;
            }
        }
        return user;
    }
    //recuperamos el grupo (el texto)
    public String getGrupoTexto(String nombre) {
        Element usuario = getGrupo(nombre);
        if (usuario == null || usuario.getAttributeValue("grupo") == null) {
            return "";
        } else {
            return usuario.getAttributeValue("grupo");
        }
    }
    public boolean cambioGrupo (String old, String newgrupo, String rutaGuardado) {
        for (Element usuario : lecturas) {
                if (usuario.getAttributeValue("grupo").matches(old)) {
                    usuario.getAttribute("grupo").setValue(newgrupo);
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
    private SAXBuilder builder;
    private String rutaXML;
    private Document bdXML;
    private Element raiz;
    private List<Element> lecturas;
}
