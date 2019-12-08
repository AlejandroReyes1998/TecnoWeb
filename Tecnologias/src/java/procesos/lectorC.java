/*
 Lector de calificaciones
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
public class lectorC {
    public lectorC(String ruta) {
        builder = new SAXBuilder();
        rutaXML = ruta;
        try {
            bdXML = builder.build(rutaXML);
            raiz = bdXML.getRootElement();
            calificaciones = raiz.getChildren("calificacion");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Element> getCalificaciones() {
        return calificaciones;
    }
        
    public boolean esDelAlumno(Element calf, String alumno, String grupo){
        return calf.getAttributeValue("alumno").matches(alumno) && calf.getAttributeValue("grupo").matches(grupo);
    }
    
    public boolean esDelGrupo(Element calf, String grupo){
        return calf.getAttributeValue("grupo").matches(grupo);
    }
    
    public List<Element> califGrupo(String grupo){
        List<Element> calf=null;
        for(Element elemento : calificaciones){
            if(elemento.getAttributeValue("grupo").equals(grupo)) 
                calf.add(elemento);
        }
        return calf;
    }
    
    
    public List<Element> calfAlumno(String nombre, List<Element> calf){
        List<Element> calA = null;
        for(Element elemento : calf){
            if(elemento.getAttributeValue("alumno").matches(nombre)) calA.add(elemento);
        }
        return calA;
    }
    public boolean cambioGrupo (String old, String newgrupo, String rutaGuardado) {
        for (Element ejercicio : calificaciones) {
                if (ejercicio.getAttributeValue("grupo").matches(old)) {
                    ejercicio.getAttribute("grupo").setValue(newgrupo);
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
    private List<Element> calificaciones;
}
