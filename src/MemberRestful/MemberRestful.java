package MemberRestful;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	@GET
	@Path("info/ID={ID}&passwd={passwd}")
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
					
					return "succed"; // 같은 ID 존재하면 
				}
			}
		}
		return "failed";
		
	}
	
}