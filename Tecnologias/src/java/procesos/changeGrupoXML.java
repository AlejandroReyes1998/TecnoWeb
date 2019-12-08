/*
Servlet que cambia el contenido de un nodo de grupos.xml usuarios.xml y ejercicios.xml
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

public class changeGrupoXML extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int n = 0;
        //recibimos los nuevos datos a sobreescribir
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String old = (String) session.getAttribute("oldGrupo");
        String id = (String) session.getAttribute("id");
        String realpath = (String) session.getAttribute("elcaminoreal");
        if (userName != null) {
            
            String nameGrupo = request.getParameter("namegruponew");
            procesos.lectorG archivoGrupoXML = new procesos.lectorG (realpath + "grupos.xml");
            procesos.lector archivoUsuarioXML = new procesos.lector (realpath + "usuarios.xml");
            procesos.lectorE archivoEjercicioXML = new procesos.lectorE (realpath + "ejercicios.xml");
            procesos.lectorR archivoRespuestaXML = new procesos.lectorR (realpath + "respuestas.xml");
            procesos.lectorC archivoCalificacionXML = new procesos.lectorC (realpath + "calificaciones.xml");
            procesos.lectorL archivoLecturasXML = new procesos.lectorL (realpath + "lecturas.xml");
                for (Element grupo : archivoGrupoXML.getGrupos()) {
                    if (grupo.getText().equals(nameGrupo) && !nameGrupo.equals(old)) {
                        n++;
                    }
                }
                if (n == 0){
                out.println("<HTML>");
                out.println("<body>");
                //eliminamos los viejos datos
                if (archivoGrupoXML.cambio(old, nameGrupo, realpath + "grupos.xml") && archivoUsuarioXML.cambioGrupo(old, nameGrupo, realpath + "usuarios.xml") && archivoRespuestaXML.cambioGrupo(old, nameGrupo, realpath + "respuestas.xml")
                         && archivoLecturasXML.cambioGrupo(old, nameGrupo, realpath + "lecturas.xml") && archivoCalificacionXML.cambioGrupo(old, nameGrupo, realpath + "calificaciones.xml") && archivoEjercicioXML.cambioGrupo(old, nameGrupo, realpath + "ejercicios.xml")) {
                    //los sustituimos por los nuevos
                    out.println("<script>alert('Se ha cambiado el grupo correctamente');");
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
                    out.println("<script>alert('Este grupo ya existe');");
                    out.println("location='grupos'</script>'");
                }

        } else {
            response.sendRedirect("index.html");
        }

    }

}
