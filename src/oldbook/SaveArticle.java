package oldbook;

import gson.OldBookGson;
import gson.SaleArticle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import OldBookManager.OldBookManager;

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

public class SaveArticle extends HttpServlet {
	private String num;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();



	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		BlobKey blobKey = blobs.get("image");
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("sArticle").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(1));
		if (entities.isEmpty()) {
			resp.getWriter().print("empty");
			num = "1";
		} else {
			for (Entity entity : entities) {
				String tmpStr;
				int tmpInt;
				tmpStr = entity.getProperty("num").toString();
				tmpInt = Integer.parseInt(tmpStr) + 1;
				num = tmpInt + "";
				resp.getWriter().print("now is " + num);
			}
		}

		String keyStr = "sArticle";
		Key articleKey = KeyFactory.createKey("sArticle", keyStr);

		String ID = req.getParameter("ID");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String publisher = req.getParameter("publisher");
		String price = req.getParameter("price");
		String condition = req.getParameter("condition");
		String method = req.getParameter("method");
		String image = blobKey.getKeyString();
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
		entity.setProperty("image", image);
		entity.setProperty("contents", contents);

		DatastoreService datastore2 = DatastoreServiceFactory
				.getDatastoreService();
		datastore2.put(entity);

		resp.setCharacterEncoding("euc-kr");
		resp.getWriter().print("succeed save");
	}
}
