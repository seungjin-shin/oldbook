package oldbook;

import gson.Member;
import gson.OldBookGson;
import gson.SaleArticle;
import gson.SaleImg;

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

public class GetImg extends HttpServlet {
	private final int MAXARTICLENUM = 50;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int count = 0;
		int i = 0;
		String ID = req.getParameter("ID");

		resp.setCharacterEncoding("UTF-8");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Image").addSort("date",Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(MAXARTICLENUM));
		
		resp.getWriter().print("{\"myBookList\":{\"myBikeBoard\":[");
		
		if (entities.isEmpty()) {
			resp.getWriter().print(ID + "null");
		} else {
			for (Entity entity : entities) {
				if (ID.equals(entity.getProperty("ID").toString())) {
					count++;
				}
			}
		}
		
		entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(MAXARTICLENUM));
		
		for (Entity entity : entities) {
			if (ID.equals(entity.getProperty("ID").toString())) {
				i++;
				String jsonString;
				OldBookGson myGson = new OldBookGson();
				SaleImg img = new SaleImg();

				img.setNum(entity.getProperty("num").toString());
				img.setImage(entity.getProperty("image").toString());

				jsonString = myGson.toJson(img);
				resp.getWriter().print(jsonString);
				if (i != count)
					resp.getWriter().print(",");
			}
		}
		
		resp.getWriter().print("]}}");

	}
}