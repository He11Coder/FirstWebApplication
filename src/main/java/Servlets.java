import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlets extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, Object> pageVar = createVarMap(request);
        pageVar.put("message", "");

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVar));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<String, Object> pageVar = createVarMap(request);
        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");
        if (message == null || message.isEmpty())
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
            else
        {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        pageVar.put("message", message == null ? "" : message);
        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVar));

    }

    private static Map<String, Object> createVarMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
