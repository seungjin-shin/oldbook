package oldbook;

import gson.Member;
import gson.OldBookGson;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllArticle extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException{
		String jsonString;
		OldBookGson myGson = new OldBookGson();
		Member myMember = new Member();
		myMember.setID("dewlit");
		myMember.setName("싱싱");
		myMember.setPasswd("dd");
		myMember.setPhone("01037662141");
		
		jsonString = myGson.toJson(myMember);
		resp.setCharacterEncoding("euc-kr");
		resp.getWriter().print(jsonString);
	}

}
