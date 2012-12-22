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

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	String keyStr = "article";
	Key articleKey = KeyFactory.createKey("Article", keyStr);
	
//	Query query = new Query("Article", articleKey).addSort("date", Query.SortDirection.DESCENDING);
//	List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
//	    if (greetings.isEmpty()) {
//	    	resp.getWriter().print("empty");
//	    } else {
//	        for (Entity greeting : greetings) {
//	        	String jsonString;
//				OldBookGson myGson = new OldBookGson();
//				SaleArticle article = new SaleArticle();
//				
//				article.setArticleNum(greeting.getProperty("articleNum").toString());
//				article.setID(greeting.getProperty("ID").toString());
//				article.setTitle(greeting.getProperty("title").toString());
//				article.setPublisher(greeting.getProperty("publisher").toString());
//				article.setPrice(greeting.getProperty("price").toString());
//				article.setStatus(greeting.getProperty("status").toString());
//				article.setMethod(greeting.getProperty("method").toString());
//				
//				jsonString = myGson.toJson(article);
//				resp.setCharacterEncoding("euc-kr");
//				resp.getWriter().print(jsonString);
//	        	
//	        }
//	    }
    
//    if (tmpEntity == null) {
//    	articleNum = "1";
//    }
//    else{
//    	String tmpStr;
//    	int tmpInt;
//    	tmpStr = tmpEntity.getProperty("articleNum").toString();
//    	tmpInt = Integer.parseInt(tmpStr) + 1;
//    	articleNum = tmpInt + "";
//    }
	    
    String ID = req.getParameter("ID");
    String title = req.getParameter("title");
    String author = req.getParameter("author");
    String publisher = req.getParameter("publisher");
    String price = req.getParameter("price");
    String status = req.getParameter("status");
    String method = req.getParameter("method");
    Date date = new Date();
    
    Entity entity = new Entity("Article", articleKey);
    //entity.setProperty("articleNum", articleNum);
    entity.setProperty("ID", ID);
    entity.setProperty("title", title);
    entity.setProperty("author", author);
    entity.setProperty("publisher", publisher);
    entity.setProperty("price", price);
    entity.setProperty("status", status);
    entity.setProperty("method", method);
    entity.setProperty("date", date);
    
    DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();
    datastore2.put(entity);
    
    resp.setCharacterEncoding("euc-kr");
	resp.getWriter().print("succeed save");
}
}
