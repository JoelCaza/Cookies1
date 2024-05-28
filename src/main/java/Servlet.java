/*
 * Autor: Joel Caza
 * Fecha: 27/05/2024
 * Materia: Lenguajes de Programacion
 *
 * */

// Importaciones Necesarias
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    // Método para manejar las solicitudes GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Contador de visitas
        int visitCount = 0;
        Cookie[] cookies = req.getCookies();

        // Buscar la cookie de visitCount
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitCount")) {
                    visitCount = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }

        visitCount++;

        // Crear y agregar la cookie de visitCount
        Cookie visitCountCookie = new Cookie("visitCount", String.valueOf(visitCount));
        resp.addCookie(visitCountCookie);

        // Configurar la respuesta HTTP
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Generar la página HTML de respuesta
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Contador de Visitas</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background-color: #f0f0f0;");
        out.println("    text-align: center;");
        out.println("}");
        out.println(".container {");
        out.println("    max-width: 400px;");
        out.println("    margin: 100px auto;");
        out.println("    padding: 20px;");
        out.println("    background-color: #fff;");
        out.println("    border-radius: 5px;");
        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Bienvenido</h1>");
        out.println("<p>Has visitado esta página " + visitCount + " veces.</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

}
