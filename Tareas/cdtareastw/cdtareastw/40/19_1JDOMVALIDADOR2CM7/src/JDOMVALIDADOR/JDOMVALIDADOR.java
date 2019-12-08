package JDOMVALIDADOR;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.io.IOException;

public class JDOMVALIDADOR {
    // Método que comprueba la conformidad con XML

    public void checkConforme(String sURL) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.build(sURL);
    }
    // Método que comprueba la validez con el DTD

    public void checkValido(String sURL) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(true);
        builder.build(sURL);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Utilizacion: java  JDOMVALIDADOR URL");
        } else {
            JDOMVALIDADOR validador = new JDOMVALIDADOR(); // El propio validador
            try {
                try {
                    // Documento conforme??
                    validador.checkConforme(args[0]);
                } // Indicacion de un fichero mal formado
                catch (JDOMException e) {
                    System.out.println(args[0] + " NO esta bien formado");
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
                // Si el fichero esta bien formado no saltará ninguna excepcion
                System.out.println(args[0] + " Esta bien formado");
                try {
                    // Documento Válido??
                    validador.checkValido(args[0]);
                } // Indicacion de un fichero no válido
                catch (JDOMException e) {
                    System.out.println(args[0] + " NO es valido");
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
                // Si el fichero es válido no saltará ninguna excepcion
                System.out.println(args[0] + " Es valido");
            } // Indicación de que el fichero no es accesible
            catch (IOException e) {
                System.out.println("No se puede chequear " + args[0]);
                System.out.println("porque: " + e.getMessage());
            }
        }
    }
}