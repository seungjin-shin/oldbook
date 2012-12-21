package oldbook;

import gson.Member;
import gson.OldBookGson;
import gson.SaleArticle;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;

public class GetAllArticle extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException{
		 // test, not completed
		String jsonString;
		OldBookGson myGson = new OldBookGson();
		Member myMember = new Member();
		myMember.setID("dewlit");
		myMember.setName("싱싱");
		myMember.setPasswd("dd");
		myMember.setPhone("01037662141");
		
//		 List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
// 	    if (greetings.isEmpty()) {
// 	    	resp.getWriter().print("empty");
// 	    } else {
// 	        for (Entity greeting : greetings) {
// 	        	String jsonString;
// 				OldBookGson myGson = new OldBookGson();
// 				SaleArticle article = new SaleArticle();
// 				
// 				article.setArticleNum(entity.getProperty("articleNum").toString());
// 				article.setID(entity.getProperty("ID").toString());
// 				article.setTitle(entity.getProperty("title").toString());
// 				article.setPublisher(entity.getProperty("publisher").toString());
// 				article.setPrice(entity.getProperty("price").toString());
// 				article.setStatus(entity.getProperty("status").toString());
// 				article.setMethod(entity.getProperty("method").toString());
// 				
// 				jsonString = myGson.toJson(article);
// 				resp.setCharacterEncoding("euc-kr");
// 				resp.getWriter().print(jsonString);
// 	        	
// 	        }
// 	    }
		
		jsonString = myGson.toJson(myMember);
		resp.setCharacterEncoding("euc-kr");
		resp.getWriter().print(jsonString);
	}

}
