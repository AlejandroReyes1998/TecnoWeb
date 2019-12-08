/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class lectorSA {

    //Constructor
    public lectorSA(String ruta) {
        builder = new SAXBuilder();
        rutaXML = ruta;
        try {
            bdXML = builder.build(rutaXML);
            raiz = bdXML.getRootElement();
            respuestas = raiz.getChildren("solucion");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//lista de respuestas

    public List<Element> getRespuestas() {
        System.out.println(respuestas);
        return respuestas;
    }
    //recupera ciertas respuestas de un cierto alumno del xml
    public Element getRespuesta(String nombre) {
        Element user = null;
        for (Element usuario : respuestas) {
            if (usuario.getAttributeValue("alumno").matches(nombre)) {
                user = usuario;
                break;
            }
        }
        return user;
    }
    public Element getAlumnoEjercicio(String nombre,String ejercicio) {
        Element user = null;
        for (Element usuario : respuestas) {
            if (usuario.getAttributeValue("alumno").matches(nombre)||usuario.getAttributeValue("pregunta").matches(ejercicio)) {
                user = usuario;
                break;
            }
        }
        return user;
    }
    //recuperamos el nombre del usuario que respondió
    public String getTipo(String nombre) {
        Element usuario = getRespuesta(nombre);
        if (usuario == null || usuario.getAttributeValue("alumno") == null) {
            return "";
        } else {
            return usuario.getAttributeValue("alumno");
        }
    }
    public String getS(String nombre,String ejercicio) {
        Element ej = getAlumnoEjercicio(nombre,ejercicio);

        if (ej == null || ej.getAttributeValue("pregunta") == null|| ej.getAttributeValue("alumno") == null) {
            return "";
        } else {
            return ej.getChildText("canvas");
        }
    }
    private SAXBuilder builder;
    private String rutaXML;
    private Document bdXML;
    private Element raiz;
    private List<Element> respuestas;

    
    
}
