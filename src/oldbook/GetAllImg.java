package oldbook;

import gson.OldBookGson;
import gson.SaleImg;

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

public class GetAllImg extends HttpServlet {
	private final int MAXARTICLENUM = 500;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int entityLen = 0;
		int i = 0;

		resp.setCharacterEncoding("UTF-8");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Image").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXARTICLENUM));

		resp.getWriter().print("{\"myBookList\":{\"myBikeBoard\":[");

		entityLen = entities.size();
		if (entities.isEmpty()) {
			resp.getWriter().print("null");
		} else {
			for (Entity entity : entities) {
				i++;
				String jsonString;
				OldBookGson myGson = new OldBookGson();
				SaleImg img = new SaleImg();

				img.setNum(entity.getProperty("num").toString());
				img.setImage(entity.getProperty("image").toString());

				jsonString = myGson.toJson(img);
				resp.getWriter().print(jsonString);
				if (i != entityLen)
					resp.getWriter().print(",");
			}
		}
		resp.getWriter().print("]}}");

	}
}
