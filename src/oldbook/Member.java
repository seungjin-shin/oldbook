package oldbook;

import gson.MemberInfo;
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

public class Member extends HttpServlet {
	private final int MAXNUM = 500;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");

		String keyStr = "member";
		Key memberKey = KeyFactory.createKey("member", keyStr);

		String ID = req.getParameter("ID");
		String passwd = req.getParameter("passwd");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String GCMID = req.getParameter("GCMID");
		Date date = new Date();

		Entity entity = new Entity("Member", memberKey);
		entity.setProperty("ID", ID);
		entity.setProperty("name", name);
		entity.setProperty("passwd", passwd);
		entity.setProperty("phone", phone);
		entity.setProperty("GCMID", GCMID);
		entity.setProperty("date", date);

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(entity);

		resp.getWriter().print("succeed save Member");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// ID 비교할때 모든 멤버와 비교해서 체크

		String ID = req.getParameter("ID");

		resp.setCharacterEncoding("UTF-8");
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Member").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXNUM));

		if (entities.isEmpty()) {
			resp.getWriter().print("ture");
			return;
		} else {
			for (Entity entity : entities) {
				if (ID.equals(entity.getProperty("ID").toString())) {
					resp.getWriter().print("false");
					return; // 같은 ID 존재하면 
				}
			}
		}
		resp.getWriter().print("true");
	}
}
