package procesos;
//Despliega el ejemplo que el alumno seleccionó

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import procesos.lectorEJ;
import procesos.lectorSA;
import procesos.lectorS;

/**
 *
 * @author alejandro
 */
public class calificarC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String alumno = request.getParameter("alumno");
        session.setAttribute("alumno", alumno);
        String ejercicio = (String) session.getAttribute("ejercicio");
        String id = (String) session.getAttribute("id");
        lectorS archivoXML2 = new lectorS(realpath + "solucionesCanvas.xml");
        lectorSA archivoXML3 = new lectorSA(realpath + "solucionesAlumnos.xml");
        //Recuperamos el nombre y el json del canvas del archivo xml de ejemplos
        String can2 = archivoXML2.getS(ejercicio);
        String can3 = archivoXML3.getS(alumno,ejercicio);
        if (userName != null && id != null) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calificar</title>");
            //llamamos a fabric, que es el framework que soporta los objetos subidos por el profesor
            out.println("<script type='text/javascript' src='js/fabric.min.js'></script>");
            out.println("<script type='text/javascript' src='js/jquery.min.js'></script>");
            out.println("<script type='text/javascript' src='js/responsivevoice.js'></script>");
            out.println("<script src='js/bootstrap.min.js'></script>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='css/profesor.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div align='center' id='canvas-container'>");
            out.println("<div class='titulo'><h1>" + ejercicio + ": Solucion</h1></div>");
            out.println("<canvas id='c' width=\"800\" height=\"800\" style=\"border:1px solid #000000;\"></canvas>");
            out.println("<script>");
            //declaramos un canvas y lo recuperamos con el método loadfromJson, el cual recibe un string 
            //que se encuentra almacenado en el xml y fue recuperado previamente.
            out.println("var canvas = new fabric.StaticCanvas('c');");
            out.println("canvas.loadFromJSON(" + can2 + ");");
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
            out.println("</div>");
            out.println("<div align='center' id='canvas-container'>");
            out.println("<div class='titulo'><h1>Ejercicio de: " + alumno+ "</h1></div>");
            out.println("<canvas id='d' width=\"800\" height=\"800\" style=\"border:1px solid #000000;\"></canvas>");
            out.println("<script>");
            //declaramos un canvas y lo recuperamos con el método loadfromJson, el cual recibe un string 
            //que se encuentra almacenado en el xml y fue recuperado previamente.
            out.println("var canvas = new fabric.StaticCanvas('d');");
            out.println("canvas.loadFromJSON(" + can3 + ");");
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
            out.println("</div>");
            out.println("<div class='container'>");
            out.println("<div class='col-xs-4 col-xs-offset-4 form-prof'>");
            out.println("<div class='form-prof-top'><h4>Calificar</h4></div>");
            out.println("<form method='get' action='subirCalificacion'>");
            out.println ("<div class='form-prof-campos'>");
            out.println ("<div class='form-group'>");
           out.println("Calificación: <select name='calificacion' required class='form-control'>");
                out.println("<option value='10'>10</option>");
                out.println("<option value='9.5'>9.5</option>");
                out.println("<option value='9'>9</option>");
                out.println("<option value='8.5'>8.5</option>");
                out.println("<option value='8'>8</option>");
                out.println("<option value='7.5'>7.5</option>");
                out.println("<option value='7'>7</option>");
                out.println("<option value='6.5'>6.5</option>");
                out.println("<option value='6'>6</option>");
                out.println("<option value='5.5'>5.5</option>");
                out.println("<option value='5'>5</option>");
                out.println("<option value='4.5'>4.5</option>");
                out.println("<option value='4'>4</option>");
                out.println("<option value='3.5'>3.5</option>");
                out.println("<option value='3'>3</option>");
                out.println("<option value='2.5'>2.5</option>");
                out.println("<option value='2'>2</option>");
                out.println("<option value='1.5'>1.5</option>");
                out.println("<option value='1'>1</option>");
                out.println("<option value='0'>0</option>");
            out.println("</select>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<button class='btn form-control btn-info'>Calificar</button>");
            out.println ("</div>");
            out.println ("<div class='form-group'>");
            out.println("<a href='inicioprofe' class='btn form-control btn-warning'>Regresar</a>");
            out.println ("</div>");          
            out.println("</form>");
            out.println ("</div>");
            out.println("</br></br></br>");
            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
/*
            out.println("Calificación: <select name='calificacion' required class='form-control'>");
                out.println("<option value='10'>10</option>");
                out.println("<option value='9.5'>9.5</option>");
                out.println("<option value='9'>9</option>");
                out.println("<option value='8.5'>8.5</option>");
                out.println("<option value='8'>8</option>");
                out.println("<option value='7.5'>7.5</option>");
                out.println("<option value='7'>7</option>");
                out.println("<option value='6.5'>6.5</option>");
                out.println("<option value='6'>6</option>");
                out.println("<option value='5.5'>5.5</option>");
                out.println("<option value='5'>5</option>");
                out.println("<option value='4.5'>4.5</option>");
                out.println("<option value='4'>4</option>");
                out.println("<option value='3.5'>3.5</option>");
                out.println("<option value='3'>3</option>");
                out.println("<option value='2.5'>2.5</option>");
                out.println("<option value='2'>2</option>");
                out.println("<option value='1.5'>1.5</option>");
                out.println("<option value='1'>1</option>");
                out.println("<option value='0'>0</option>");
            out.println("</select>");
*/