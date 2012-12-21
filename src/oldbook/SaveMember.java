package oldbook;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveMember extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		// tmp, not completed.

	UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    String ID = req.getParameter("ID");
    String name = req.getParameter("name");
    String phone = req.getParameter("phone");
    String passwd = req.getParameter("passwd");
    
    Key guestbookKey = KeyFactory.createKey("Guestbook", ID);
    String content = req.getParameter("content");
    Date date = new Date();
    Entity greeting = new Entity("Greeting", guestbookKey);
    greeting.setProperty("user", user);
    greeting.setProperty("date", date);
    greeting.setProperty("content", content);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(greeting);

    resp.sendRedirect("/guestbook.jsp?guestbookName=" + ID);
}
}
