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
	private final int MAXARTICLENUM = 50;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String ID = req.getParameter("ID");

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("sArticle").addSort("date",Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(MAXARTICLENUM));
		if (entities.isEmpty()) {
			resp.getWriter().print(ID + "null");
		} else {
			for (Entity entity : entities) {
				if (ID.equals(entity.getProperty("ID").toString())) {
					String jsonString;
					OldBookGson myGson = new OldBookGson();
					SaleArticle article = new SaleArticle();

					article.setID(entity.getProperty("ID").toString());
					article.setTitle(entity.getProperty("title").toString());
					article.setPublisher(entity.getProperty("publisher")
							.toString());
					article.setPrice(entity.getProperty("price").toString());
					article.setCondition(entity.getProperty("condition")
							.toString());
					article.setMethod(entity.getProperty("method").toString());

					jsonString = myGson.toJson(article);
					resp.setCharacterEncoding("euc-kr");
					resp.getWriter().print(jsonString);
					resp.getWriter().println("");
				}
			}
		}

	}
}
