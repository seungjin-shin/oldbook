package oldbook;

import gson.Member;
import gson.OldBookGson;
import gson.SaleArticle;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

public class GetAllArticle extends HttpServlet {
	private final int MAXARTICLENUM = 5000;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("sArticle").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXARTICLENUM));
		if (entities.isEmpty()) {
			resp.getWriter().print("null");
		} else {
			for (Entity entity : entities) {
				String jsonString;
				OldBookGson myGson = new OldBookGson();
				SaleArticle article = new SaleArticle();

				article.setID(entity.getProperty("ID").toString());
				article.setTitle(entity.getProperty("title").toString());
				article.setPublisher(entity.getProperty("publisher").toString());
				article.setPrice(entity.getProperty("price").toString());
				article.setCondition(entity.getProperty("condition").toString());
				article.setMethod(entity.getProperty("method").toString());

				jsonString = myGson.toJson(article);
				resp.setCharacterEncoding("euc-kr");
				resp.getWriter().print(jsonString);
				resp.getWriter().println("");
			}
		}
	}

}
