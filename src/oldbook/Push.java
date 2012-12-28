package oldbook;

import gson.OldBookGson;
import gson.PushInfo;
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

public class Push extends HttpServlet {
	private final int MAXNUM = 500;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String keyStr = "PushInfo";
		Key pushKey = KeyFactory.createKey("PushInfo", keyStr);
		
		String ID = req.getParameter("ID");
		String title = req.getParameter("title");
		String phone = req.getParameter("phone");
		Date date = new Date();

		Entity entity = new Entity("PushInfo", pushKey);
		entity.setProperty("ID", ID);
		entity.setProperty("title", title);
		entity.setProperty("date", date);
		entity.setProperty("phone", phone);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(entity);

		
		resp.getWriter().print("succeed save pushInfo");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int count = 0;
		int i = 0;
		String ID = req.getParameter("ID");

		resp.setCharacterEncoding("UTF-8");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("PushInfo").addSort("date",Query.SortDirection.DESCENDING);
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
				PushInfo pushInfo = new PushInfo();

				pushInfo.setID(entity.getProperty("ID").toString());
				pushInfo.setTitle(entity.getProperty("title").toString());
				pushInfo.setPhone(entity.getProperty("phone").toString());
				
				jsonString = myGson.toJson(pushInfo);
				resp.getWriter().print(jsonString);
				if(i != count)
					resp.getWriter().print(",");
			}
		}
		
		resp.getWriter().print("]}}");

	}
	
	
}
