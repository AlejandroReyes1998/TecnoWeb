package alumno;
//Despliega el ejemplo que el alumno seleccionó

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import procesos.lectorCE;

/**
 *
 * @author alejandro
 */
public class resolver extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String grupo = (String) session.getAttribute("grupo");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String ejercicio = request.getParameter("ejercicio");
        session.setAttribute("ejercicio", ejercicio);
        String id = (String) session.getAttribute("id");
        lectorCE archivoXML2 = new lectorCE(realpath + "ejercicioCanvas.xml");
        //Podrá ser que el canvas ya exista y sólo deseé modificarlo?...
        String can = archivoXML2.getCE(ejercicio);
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
            out.println("<div class='titulo'><h1>" + ejercicio + "</h1></div>");
            out.println("<canvas id='c' width=\"800\" height=\"800\" style=\"border:1px solid #000000;\"></canvas>");
            out.println("<script>");
            //declaramos un canvas y lo recuperamos con el método loadfromJson, el cual recibe un string 
            //que se encuentra almacenado en el xml y fue recuperado previamente.
            out.println("var canvas = new fabric.Canvas('c');");
            out.println("canvas.loadFromJSON(" + can + ");");
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
            out.println("function serializar() {\n"
                    + " var canvasstring = JSON.stringify(canvas);\n"
                    + "$.ajax({\n"
                    + "type: \"GET\",\n"
                    + "url:\"resolverCanvas\",\n"
                    + "data:{\"canv\":canvasstring},\n"
                    + "success: function () {\n" //con base al resultado le avisamos al usuario si su canvas se pudo subir o no
                    + "alert('Canvas guardado');\n"
                    + " }\n"
                    + ",error: function () {\n"
                    + "alert('canvas no guardado');\n"
                    + " }\n"
                    + "}); "
                    + "};");
            out.println("function salir() {\n"
                    + "    if (confirm(\"¿Estás seguro de haber terminado? Tus cambios no se podrán modificar más adeltante. Si desea guardar, presione el botón de guardado\")) {\n"
                    +"window.location.href = \"inicioalumno\";"
                    + "}\n"
                    + "};");
            out.println("</script>");
            out.println("<button class='btn btn-info' onClick='serializar()'>Guardar ejercicio</button>");
            out.println("<button class='btn mybutton btn-warning' onClick='salir()'>Enviar y salirr</button>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        } else {
            response.sendRedirect("index.html");
        }

    }
}
