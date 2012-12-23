package oldbook;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

public class GetNum extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
	String num="";
	
		DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	Query query = new Query("Number").addSort("date",
			Query.SortDirection.DESCENDING);
	
	List<Entity> entities = datastore.prepare(query).asList(
			FetchOptions.Builder.withLimit(1));
	if (entities.isEmpty()) {
		num = "1";
		resp.getWriter().print(num);
	} else {
		for (Entity entity : entities) {
			String tmpStr;
			int tmpInt;
			tmpStr = entity.getProperty("num").toString();
			tmpInt = Integer.parseInt(tmpStr) + 1;
			num = tmpInt + "";
			resp.getWriter().print(num);
		}
	}
	Date date = new Date();
	
	String keyStr = "Number";
	Key articleKey = KeyFactory.createKey("Number", keyStr);
	Entity entity = new Entity("Number", articleKey);
	entity.setProperty("num", num);
	entity.setProperty("date", date);

	DatastoreService datastore2 = DatastoreServiceFactory
			.getDatastoreService();
	datastore2.put(entity);
	
	
}
}
