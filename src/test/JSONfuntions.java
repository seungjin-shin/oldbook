//package oldbook;
//import gson.OldBookGson;
//import gson.SaleArticle;
//
//import java.io.IOException;
//import java.util.Date;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import OldBookManager.OldBookManager;
//
//import com.google.appengine.api.datastore.DatastoreService;
//import com.google.appengine.api.datastore.DatastoreServiceFactory;
//import com.google.appengine.api.datastore.Entity;
//import com.google.appengine.api.datastore.Key;
//import com.google.appengine.api.datastore.KeyFactory;
//import com.google.appengine.api.datastore.Query;
//import java.util.List;
//import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserService;
//import com.google.appengine.api.users.UserServiceFactory;
//import com.google.appengine.api.datastore.DatastoreServiceFactory;
//import com.google.appengine.api.datastore.DatastoreService;
//import com.google.appengine.api.datastore.EntityNotFoundException;
//import com.google.appengine.api.datastore.Query;
//import com.google.appengine.api.datastore.Entity;
//import com.google.appengine.api.datastore.FetchOptions;
//import com.google.appengine.api.datastore.Key;
//import com.google.appengine.api.datastore.KeyFactory;
//public class SaveArticle extends HttpServlet {
//	private String num;
//	
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//		
//		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//		
//		Query query = new Query("sArticle").addSort("date",Query.SortDirection.DESCENDING);
//		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1));
//		    if (entities.isEmpty()) {
//		    	resp.getWriter().print("empty");
//		    	num = "1";
//		    } else {
//		    	for (Entity entity : entities) {
//		        	String jsonString;
//					OldBookGson myGson = new OldBookGson();
//					SaleArticle article = new SaleArticle();
//					
//					article.setID(entity.getProperty("ID").toString());
//					article.setTitle(entity.getProperty("title").toString());
//					article.setPublisher(entity.getProperty("publisher")
//							.toString());
//					article.setPrice(entity.getProperty("price").toString());
//					article.setCondition(entity.getProperty("condition")
//							.toString());
//					article.setMethod(entity.getProperty("method").toString());
//					article.setContents(entity.getProperty("contents").toString());
//					article.setImage(entity.getProperty("image").toString());
//					
//					jsonString = myGson.toJson(article);
//					resp.setCharacterEncoding("UTF-8");
//					resp.getWriter().print(jsonString);
//					
//					String tmpStr;
//			    	int tmpInt;
//			    	tmpStr = entity.getProperty("num").toString();
//			    	tmpInt = Integer.parseInt(tmpStr) + 1;
//			    	num = tmpInt + "";
//		        }
//		    }
//	    
//		
//	String keyStr = "sArticle";
//	Key articleKey = KeyFactory.createKey("sArticle", keyStr);
//	    
//    String ID = req.getParameter("ID");
//    String title = req.getParameter("title");
//    String author = req.getParameter("author");
//    String publisher = req.getParameter("publisher");
//    String price = req.getParameter("price");
//    String condition = req.getParameter("condition");
//    String method = req.getParameter("method");
//    String image = req.getParameter("image");
//    String contents = req.getParameter("contents");
//    Date date = new Date();
//    
//    Entity entity = new Entity("sArticle", articleKey);
//    entity.setProperty("num", num);
//    entity.setProperty("ID", ID);
//    entity.setProperty("title", title);
//    entity.setProperty("author", author);
//    entity.setProperty("publisher", publisher);
//    entity.setProperty("price", price);
//    entity.setProperty("condition", condition);
//    entity.setProperty("method", method);
//    entity.setProperty("date", date);
//    entity.setProperty("image", image);
//    entity.setProperty("contents", contents);
//    
//    DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();
//    datastore2.put(entity);
//    
//    resp.setCharacterEncoding("euc-kr");
//	resp.getWriter().print("succeed save");
//}
//}
