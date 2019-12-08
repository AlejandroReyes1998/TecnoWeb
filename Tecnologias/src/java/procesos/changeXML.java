/*
Servlet que cambia el contenido de un nodo (usuario)xml
 */
package procesos;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;

public class changeXML extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int n = 0;
        //recibimos los nuevos datos a sobreescribir
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String old = (String) session.getAttribute("old");
        String id = (String) session.getAttribute("id");
        String realpath = (String) session.getAttribute("elcaminoreal");
        String idx = (String) session.getAttribute("iduser");
        if (userName != null && id != null) {

            //subimos los nuevos datos
            procesos.lector archivoXML = new procesos.lector(realpath + "usuarios.xml");
            String nombre = request.getParameter("usernamenew");
            String password = request.getParameter("pass");
            String tipo = request.getParameter("tipo");
            String grupo = request.getParameter("grupo");
            if (nombre != null && password != null) {
                for (Element usuario : archivoXML.getUsuarios()) {
                    if (usuario.getChildText("nombre").equals(nombre) && !old.equals(nombre)) {
                        n++;
                    }
                }
                if (n == 0){
                out.println("<HTML>");
                out.println("<body>");
                //eliminamos los viejos datos
                if (archivoXML.cambio(nombre, password, tipo, idx, grupo, realpath + "usuarios.xml")) {
                    //los sustituimos por los nuevos
                    out.println("<script>alert('Se ha cambiado el usuario correctamente');");
                    out.println("location='inicioadmin'</script>'");
                } else {
                    out.println("<script>alert('Ha ocurrido un error');");
                    out.println("location='inicioadmin'</script>'");
                }
                out.println("<a href='inicioadmin'>Regresar</a>");
                out.println("</body>");
                out.println("</HTML>");
                }
                else{                            
                    out.println("<script>alert('Este usuario ya existe');");
                    out.println("location='usuarios'</script>'");
                }
            }else{
                out.println("<script>alert('Uno o más campos se encuentran vacíos');");
                out.println("location='inicioadmin'</script>'");
            }

        } else {
            response.sendRedirect("index.html");
        }

    }

}
