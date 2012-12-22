package oldbook;
import gson.OldBookGson;
import gson.SaleArticle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import OldBookManager.OldBookManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import java.util.List;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
public class SaveArticle extends HttpServlet {
	private OldBookManager obManager;
	private String articleNum;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

	String keyStr = "sArticle";
	Key articleKey = KeyFactory.createKey("sArticle", keyStr);
	    
    String ID = req.getParameter("ID");
    String title = req.getParameter("title");
    String author = req.getParameter("author");
    String publisher = req.getParameter("publisher");
    String price = req.getParameter("price");
    String condition = req.getParameter("condition");
    String method = req.getParameter("method");
    Date date = new Date();
    
    Entity entity = new Entity("sArticle", articleKey);
    entity.setProperty("ID", ID);
    entity.setProperty("title", title);
    entity.setProperty("author", author);
    entity.setProperty("publisher", publisher);
    entity.setProperty("price", price);
    entity.setProperty("condition", condition);
    entity.setProperty("method", method);
    entity.setProperty("date", date);
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(entity);
    
    resp.setCharacterEncoding("euc-kr");
	resp.getWriter().print("succeed save");
}
}
