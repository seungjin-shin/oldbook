package MemberRestful;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

@Path("/member")
public class MemberRestful {
	private final int MAXARTICLENUM = 500;
	
	@PUT
	@Path("changePasswd/ID={ID}&passwd={passwd}")
	@Produces(MediaType.TEXT_HTML)
	public String changePasswd(@PathParam("ID") final String ID,@PathParam("passwd") final String passwd) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Member").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXARTICLENUM));

		if (entities.isEmpty()) {
			return null;
		} else {
			for (Entity entity : entities) {
				if (entity.getProperty("ID").toString().equals(ID)) {

					Entity tmp = entity.clone();
					tmp.setProperty("passwd", passwd);
					datastore.put(tmp);
					
					return "change succed";
				}
			}
		}
		return "change failed";
	}
	
	@GET
	@Path("login/ID={ID}&passwd={passwd}")
	@Produces(MediaType.TEXT_HTML)
	public String loginChk(@PathParam("ID") final String ID,@PathParam("passwd") final String passwd) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Member").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXARTICLENUM));

		if (entities.isEmpty()) {
			return null;
		} else {
			for (Entity entity : entities) {
				if (entity.getProperty("ID").toString().equals(ID)) {
					if(entity.getProperty("passwd").toString().equals(passwd))
						return "true";
					break; 
				}
			}
		}
		return "false";
	}
	
	@GET
	@Path("phoneInfo/ID={ID}")
	@Produces(MediaType.TEXT_HTML)
	public String loginInfo(@PathParam("ID") final String ID) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Member").addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXARTICLENUM));

		if (entities.isEmpty()) {
			return null;
		} else {
			for (Entity entity : entities) {
				if (entity.getProperty("ID").toString().equals(ID)) {
					return entity.getProperty("phone").toString();
				}
			}
		}
		return "false";
	}
}