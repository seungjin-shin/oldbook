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

public class GetAllArticle extends HttpServlet {
	private final int MAXARTICLENUM = 500;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int entityLen = 0;
		int i = 0;

		resp.setCharacterEncoding("UTF-8");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("sArticle").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXARTICLENUM));

		resp.getWriter().print("{\"myBikeList\":{\"myBikeBoard\":[");

		entityLen = entities.size();
		if (entities.isEmpty()) {
			resp.getWriter().print("null");
		} else {
			for (Entity entity : entities) {
				i++;
				String jsonString;
				OldBookGson myGson = new OldBookGson();
				SaleArticle article = new SaleArticle();

				article.setNum(entity.getProperty("num").toString());
				article.setID(entity.getProperty("ID").toString());
				article.setTitle(entity.getProperty("title").toString());
				article.setAuthor(entity.getProperty("author").toString());
				article.setPublisher(entity.getProperty("publisher").toString());
				article.setPrice(entity.getProperty("price").toString());
				article.setCondition(entity.getProperty("condition").toString());
				article.setMethod(entity.getProperty("method").toString());
				article.setContents(entity.getProperty("contents").toString());

				jsonString = myGson.toJson(article);
				resp.getWriter().print(jsonString);
				if (i != entityLen)
					resp.getWriter().print(",");
			}
		}
		resp.getWriter().print("]}}");

	}
}
