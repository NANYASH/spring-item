
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {
    private ItemController controller = new ItemController();
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println(controller.findById(Long.parseLong(req.getParameter("param"))).toString());
        } catch (InternalServerError internalServerError) {
            resp.getWriter().println("Internal Server Error");
            internalServerError.printStackTrace();

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println("Save is done. "+controller.save(mapToItem(req)));
        } catch (InternalServerError internalServerError) {
            resp.getWriter().println("Internal Server Error");
            internalServerError.printStackTrace();
        }

    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println("Update is done. "+controller.update(mapToItem(req)));
        } catch (BadRequestException e) {
            resp.getWriter().println("Bad Request Error");
            e.printStackTrace();
        } catch (InternalServerError e) {
            resp.getWriter().println("Internal Server Error");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            controller.delete(Long.parseLong(req.getParameter("itemId")));
            resp.getWriter().println("Item is deleted.");
        } catch (InternalServerError e) {
            resp.getWriter().println("Internal Server Error");
            e.printStackTrace();
        } catch (BadRequestException e) {
            resp.getWriter().println("Bad Request Error");
            e.printStackTrace();
        }
    }

    private Item mapToItem(HttpServletRequest req)throws IOException {
        JsonParser jsonParser = mapper.getFactory().createParser(req.getInputStream());
        Item item = mapper.readValue(jsonParser,Item.class);
        System.out.println(item);
        return item;
    }


}
