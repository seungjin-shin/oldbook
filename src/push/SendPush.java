package push;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class SendPush extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.getWriter().print("4debug");
		Sender sender = new Sender("696231272879");  //구글 코드에서 발급받은 서버 키
		//resp.getWriter().print("4debug");
//		   Message msg = new Message.Builder()
//		   					.addData("dd", "dd")
//                            .build();
//
//
//		   //푸시 전송. 파라미터는 푸시 내용, 보낼 단말의 id, 마지막은 잘 모르겠음
//
//		   Result result = sender.send(msg, "APA91bHzd3eOAbp2Ye3vZpwxk0U8qCcfHRECla_OGJeiWK4jcxA-1lvIBEH26TrC5bVs3ulRMYhiXHGCLMOY8N0W2FhdQDK7InykGRidODvFSN-0vbjd7pm1KjZdUmSSIQzP-sK7aPVX", 5);
//
//
//		   //결과 처리
//
//		   if(result.getMessageId() != null) {
//
//		      //푸시 전송 성공
//				resp.getWriter().print("succeed push");
//
//		   }
//
//		   else {
//
//		      String error = result.getErrorCodeName();   //에러 내용 받기
//
//
//		      //에러 처리
//
//		      if(Constants.ERROR_INTERNAL_SERVER_ERROR.equals(error)) {
//
//		         //구글 푸시 서버 에러
//		    	  resp.getWriter().print("google server error");
//		      }
//		      else
//		    	  resp.getWriter().print(error);
//		    	  
//
//		   }
	}
}