package procesos;
//serlvet que elimina un nodo del xml (usuario)
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class eliminar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("username");
        String x = (String) request.getParameter("nom");
        String nom = (String)session.getAttribute("id");
        if (id != null && nom != null) {
            
            String realpath = (String) session.getAttribute("elcaminoreal");
            //abrimos el archivo para eliminar los datos necesarios
            procesos.lector archivoXML = new procesos.lector(realpath + "usuarios.xml");
            out.println("<HTML>");
            out.println("<body>");
            if (archivoXML.eliminar(x, realpath + "usuarios.xml")) {
                //exito eliminando
                out.println("USUARIO ELIMINADO");
            } else {
                //ocurrió algo...
                out.println("ERROR");
            }
            out.println("<a href='inicioadmin'>regresar</a>'");
            out.println("</body>");
            out.println("</HTML>");
        } else {
            response.sendRedirect("index.html");
        }
    }

}
