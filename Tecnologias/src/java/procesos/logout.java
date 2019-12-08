package procesos;
//servlet que se encarga de hacer el logout
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alejandro
 */
public class logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /*quitamos los atributos de sesión, todos los servlets contienen un if
        que valida que en caso de que los atributos de sesión sean nulos haga un 
        redireccionamiento automático a la página de inicio
        */
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("id");
        response.sendRedirect("index.html");
    }
}
