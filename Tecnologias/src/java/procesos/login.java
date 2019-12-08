/*
 Servlet que se encarga de redirigir o negar el acceso con base a las
credenciales presentadas en el formulario de inicio
 */
package procesos;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        //recuperamos los parámetros del formualrio
        String nom = request.getParameter("username");
        String pass = request.getParameter("pass");
        String tipo = "";
        String id = "";
        String grupo = "";
        PrintWriter out = response.getWriter();
        int flag = 0;
        //si los campos no están vacíos....
        if (!"".equals(nom) && !"".equals(pass)) {
            //buscaremos en el archivo xml los datos que recibimos
            String ruta = request.getRealPath("/") + "xml/";
            lector archivoXML = new lector(ruta + "usuarios.xml");
            for (Element usuario : archivoXML.getUsuarios()) {
                if (usuario.getChildText("nombre").equals(nom) && usuario.getChildText("password").equals(pass)) {
                    //existe el registro, por lo que recuperamos el ID y el tipo de usuario
                    tipo = archivoXML.getTipoTexto(usuario.getChildText("nombre"));
                    id = archivoXML.getID(usuario.getChildText("nombre"));
                    //si no es admin, recuperamos el grupo
                    if (!archivoXML.getTipoTexto(usuario.getChildText("nombre")).equals("admin")) {
                        grupo = archivoXML.getGrupoTexto(usuario.getChildText("nombre"));
                    }
                    //dejamos de buscar en el archivo
                    flag = 1;
                    break;
                }
            }
            //los datos recuperados los asignamos a la sesión
            if (flag == 1) {
                sesion.setAttribute("username", nom);
                sesion.setAttribute("elcaminoreal", ruta);
                sesion.setAttribute("id", id);
                switch (tipo) {
                    //es administrador, por lo que va al menú de administrador
                    case "admin":
                        response.sendRedirect("inicioadmin");
                        break;
                    case "profesor":
                        //es profesor, por lo que va al menú de profesor
                        //al ser profesor, este debe de tener un grupo también
                        sesion.setAttribute("grupo", grupo);
                        response.sendRedirect("inicioprofe");
                        break;
                    case "alumno":
                        //es alumno, por lo que va al menú de alumno
                        sesion.setAttribute("grupo", grupo);
                        response.sendRedirect("inicioalumno");
                        break;
                    default:
                        //no debería de llegaar aquí :v
                        out.println("error");
                        break;
                }
                //redirigimos no está rtegistrado
            } else {
                out.println("<script>alert('Usted no está registrado');");
                out.println("location='index.html'</script>'");
            }
            //redirigimos porque se le olvidó llenar
        } else {
            out.println("<script>alert('Uno o más campos se encuentran vacíos');");
             out.println("location='index.html'</script>'");       
            //out.println("usuario registradp");
        }
        out.println("<a href='index.html>regresar</a>'");
    }
}
