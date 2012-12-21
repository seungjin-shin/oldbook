package oldbook;
import gson.Member;
import gson.OldBookGson;
import gson.SaleArticle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import OldBookManager.OldBookManager;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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

public class GetArticle extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		String articleNum = req.getParameter("articleNum");
		
		String keyStr = "article";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    Key articleKey = KeyFactory.createKey("Article", keyStr);

	    Query query = new Query("Article", articleKey);
		
	    Entity entity = datastore.prepare(query).asSingleEntity();
		
	    if (entity != null) {
	    	String jsonString;
			OldBookGson myGson = new OldBookGson();
			SaleArticle article = new SaleArticle();
			
			article.setArticleNum(entity.getProperty("articleNum").toString());
			article.setID(entity.getProperty("ID").toString());
			article.setTitle(entity.getProperty("title").toString());
			article.setPublisher(entity.getProperty("publisher").toString());
			article.setPrice(entity.getProperty("price").toString());
			article.setStatus(entity.getProperty("status").toString());
			article.setMethod(entity.getProperty("method").toString());
			
			jsonString = myGson.toJson(article);
			resp.setCharacterEncoding("euc-kr");
			resp.getWriter().print(jsonString);
	    }
	    else
	    	resp.getWriter().print(articleNum + "null");
	    	
}
}