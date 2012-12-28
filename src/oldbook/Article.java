package oldbook;

import gson.OldBookGson;
import gson.SaleArticle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
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
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class Article extends HttpServlet {
	private final int MAXNUM = 500;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String keyStr = "sArticle";
		Key articleKey = KeyFactory.createKey("sArticle", keyStr);
		
		String num = req.getParameter("num");
		String ID = req.getParameter("ID");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String publisher = req.getParameter("publisher");
		String price = req.getParameter("price");
		String condition = req.getParameter("condition");
		String method = req.getParameter("method");
		String contents = req.getParameter("contents");
		Date date = new Date();

		Entity entity = new Entity("sArticle", articleKey);
		entity.setProperty("num", num);
		entity.setProperty("ID", ID);
		entity.setProperty("title", title);
		entity.setProperty("author", author);
		entity.setProperty("publisher", publisher);
		entity.setProperty("price", price);
		entity.setProperty("condition", condition);
		entity.setProperty("method", method);
		entity.setProperty("date", date);
		entity.setProperty("contents", contents);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(entity);

		
		resp.getWriter().print("succeed save contents");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int count = 0;
		int i = 0;
		String ID = req.getParameter("ID");

		resp.setCharacterEncoding("UTF-8");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("sArticle").addSort("date",Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(MAXNUM));
		
		resp.getWriter().print("{\"myBikeList\":{\"myBikeBoard\":[");
		
		if (entities.isEmpty()) {
			resp.getWriter().print(ID + "null");
		} else {
			for (Entity entity : entities) {
				if (ID.equals(entity.getProperty("ID").toString())) {
					count++;
				}
			}
		}
		
		entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(MAXNUM));
		
		for (Entity entity : entities) {
			if (ID.equals(entity.getProperty("ID").toString())) {
				i++;
				String jsonString;
				OldBookGson myGson = new OldBookGson();
				SaleArticle article = new SaleArticle();
				
				article.setNum(entity.getProperty("num").toString());
				article.setID(entity.getProperty("ID").toString());
				article.setAuthor(entity.getProperty("author").toString());
				article.setTitle(entity.getProperty("title").toString());
				article.setPublisher(entity.getProperty("publisher")
						.toString());
				article.setPrice(entity.getProperty("price").toString());
				article.setCondition(entity.getProperty("condition")
						.toString());
				article.setMethod(entity.getProperty("method").toString());
				article.setContents(entity.getProperty("contents").toString());
				
				jsonString = myGson.toJson(article);
				resp.getWriter().print(jsonString);
				if(i != count)
					resp.getWriter().print(",");
			}
		}
		
		resp.getWriter().print("]}}");

	}
	
	
}
