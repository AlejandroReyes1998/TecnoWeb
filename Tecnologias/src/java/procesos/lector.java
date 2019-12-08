package procesos;
//lector de usuarios (xml)
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class lector {
    //constructor
    public lector(String ruta) {
        builder = new SAXBuilder();
        rutaXML = ruta;

        try {
            bdXML = builder.build(rutaXML);
            raiz = bdXML.getRootElement();
            usuarios = raiz.getChildren("usuario");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//lista de usuarios

    public List<Element> getUsuarios() {
        return usuarios;
    }
 
    //da de alta un usuario
    public boolean alta(String nombre, String password, String tipo,String grupo,String id, String rutaGuardado) {
        Element e = new Element("usuario");
        e.setAttribute(new Attribute("id", id));
        e.setAttribute(new Attribute("tipo", tipo));
        e.setAttribute(new Attribute("grupo", grupo));
        e.addContent(new Element("nombre").setText(nombre));
        e.addContent(new Element("password").setText(password));
        
        raiz.addContent(e);

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
    public boolean cambioGrupo (String old, String newgrupo, String rutaGuardado) {
        for (Element usuario : usuarios) {
            if(!usuario.getAttributeValue("tipo").equals("admin")){
                if (usuario.getAttributeValue("grupo").matches(old)) {
                    usuario.getAttribute("grupo").setValue(newgrupo);
                }
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

    //recuperamos los usuarios por su tipo
    public List<Element> usuariosTipo(String rango) {
        ArrayList<Element> rangoX = new ArrayList<>();

        for (Element usuario : usuarios) {
            if (usuario.getChild("nombre").getAttribute("tipo").getValue().matches(rango));
        }

        return rangoX;
    }

    //recupera el usuario del xml
    public Element getUsuario(String nombre) {
        Element user = null;

        for (Element usuario : usuarios) {
            if (usuario.getChildText("nombre").matches(nombre)) {
                user = usuario;
                break;
            }
        }
        return user;
    }
    //recuperamos el tipo del usuario
    public String getTipo(String nombre) {
        Element usuario = getUsuario(nombre);

        if (usuario == null || usuario.getAttributeValue("tipo") == null) {
            return "";
        } else {
            return usuario.getAttributeValue("tipo");
        }
    }
    //recuperamos el id del usuario

    public String getID(String nombre) {
        Element usuario = getUsuario(nombre);

        if (usuario == null || usuario.getAttributeValue("id") == null) {
            return "";
        } else {
            return usuario.getAttributeValue("id");
        }
    }

    //recuperamos un nombre del xml
    public Element getNOMBRE(String id) {
        Element user = null;

        for (Element usuario : usuarios) {
            if (usuario.getChildText("nombre").matches(id)) {
                user = usuario;
                break;
            }
        }

        return user;
    }

    //eliminiar xml
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

    //nos dice el tipo de usuario
    public String getTipoTexto(String nombre) {
        String opc = getTipo(nombre);

        switch (opc) {
            case "alumno":
                return "alumno";
            case "profesor":
                return "profesor";
            case "admin":
                return "admin";
            default:
                return "error";
        }
    }

    //genera un nuevo nodo en el xml con los nuevos datos
    public boolean cambio(String nombre, String password, String tipo, String id,String grupo, String rutaGuardado) {
        for (Element usuario : usuarios) {
            if (usuario.getAttributeValue("id").matches(id)) {
                usuario.getAttribute("grupo").setValue(grupo);
                usuario.getChild("nombre").setText(nombre);
                usuario.getChild("password").setText(password);
                usuario.getAttribute("tipo").setValue(tipo);
                break;
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
    private List<Element> usuarios;
    //recuperamos el valor en string del grupo requerido
     public String getGrupoTexto(String nombre) {
        Element usuario = getUsuario(nombre);
        if (usuario == null || usuario.getAttributeValue("grupo") == null) {
            return "";
        } else {
            return usuario.getAttributeValue("grupo");
        }
    }
}
