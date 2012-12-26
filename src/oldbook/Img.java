package oldbook;

import gson.OldBookGson;
import gson.SaleArticle;
import gson.SaleImg;

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

public class Img extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private final int MAXARTICLENUM = 50;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("euc-kr");
		
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		BlobKey blobKey = blobs.get("image");
		
		String keyStr = "Image";
		Key articleKey = KeyFactory.createKey("Image", keyStr);
		
		String num = req.getParameter("num");
		String ID = req.getParameter("ID");
		String image = blobKey.getKeyString();
		Date date = new Date();

		Entity entity = new Entity("Image", articleKey);
		entity.setProperty("num", num);
		entity.setProperty("ID", ID);
		entity.setProperty("image", image);
		entity.setProperty("date", date);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(entity);
		
		resp.getWriter().print("succeed save Img");
	}
	
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
		
		entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(MAXARTICLENUM));
		
		for (Entity entity : entities) {
			if (ID.equals(entity.getProperty("ID").toString())) {
				i++;
				String jsonString;
				OldBookGson myGson = new OldBookGson();
				SaleImg img = new SaleImg();

				img.setNum(entity.getProperty("num").toString());
				img.setImage(entity.getProperty("image").toString());
				img.setID(entity.getProperty("ID").toString());
				jsonString = myGson.toJson(img);
				resp.getWriter().print(jsonString);
				if (i != count)
					resp.getWriter().print(",");
			}
		}
		
		resp.getWriter().print("]}}");

	}
}
