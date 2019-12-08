package alumno;
//Despliega el ejemplo que el alumno seleccionó

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import procesos.lectorEJ;

/**
 *
 * @author alejandro
 */
public class verEjemplo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String ejemplo = request.getParameter("ejemplo");
        String id = (String) session.getAttribute("id");
        lectorEJ archivoXML = new lectorEJ(realpath + "ejemplos.xml");
        //Recuperamos el nombre y el json del canvas del archivo xml de ejemplos
        String can = archivoXML.getEJ(ejemplo);
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Alumno</title>");
            //llamamos a fabric, que es el framework que soporta los objetos subidos por el profesor
            out.println("<script type='text/javascript' src='js/fabric.min.js'></script>");
            out.println("<script type='text/javascript' src='js/jquery.min.js'></script>");
            out.println("<script type='text/javascript' src='js/responsivevoice.js'></script>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/alumno.css'>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div align='center' id='canvas-container'>");
            out.println("<div class='titulo'><h1>"+ejemplo+"</h1></div>");
            out.println("<canvas id='c' width=\"800\" height=\"800\" style=\"border:1px solid #000000;\"></canvas>");
            out.println("<script>");
            //declaramos un canvas y lo recuperamos con el método loadfromJson, el cual recibe un string 
            //que se encuentra almacenado en el xml y fue recuperado previamente.
            out.println("var canvas = new fabric.Canvas('c');");
            out.println("canvas.loadFromJSON(" + can + ");");
            out.println("canvas.selection = false;\n");
            out.println("$(\"canvas\").click(function(e){\n"
                    + "			  var txt = canvas.getActiveObject().text;\n"
                    + "			  Decir(txt);\n"
                    + "			});\n"
                    + "\n"
                    + "			function Decir(say){\n"
                    + "				var voicelist = responsiveVoice.getVoices();\n"
                    + "				responsiveVoice.speak(say,\"Spanish Latin American Female\");\n"
                    + "			}");
            out.println("canvas.remove(img2);");
            out.println("</script>");
            out.println("<a href='inicioalumno' class='btn mybutton btn-warning'>Regresar</a>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
