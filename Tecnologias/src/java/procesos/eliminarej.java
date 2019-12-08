package procesos;
//serlvet que elimina un nodo del xml (usuario)

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;

public class eliminarej extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("username");
        String x = (String) request.getParameter("canvas");
        String nom = (String) session.getAttribute("id");
        if (id != null && nom != null) {
            String realpath = (String) session.getAttribute("elcaminoreal");
            //abrimos el archivo para eliminar los datos necesarios
            procesos.lectorEJ archivoXML = new procesos.lectorEJ(realpath + "ejemplos.xml");           
            out.println("<HTML>");
            out.println("<body>");
            if (archivoXML.eliminar(x, realpath + "grupos.xml")) {
                
                //exito eliminando
                out.println("EJEMPLO ELIMINADO");
            } else {
                //ocurrió algo...
                out.println("ERROR");
            }
            out.println("<a href='inicioprofe'>regresar</a>'");
            out.println("</body>");
            out.println("</HTML>");
        } else {
            response.sendRedirect("index.html");
        }
    }

}
