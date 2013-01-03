package push;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

public class SendPush extends HttpServlet {
	private final int MAXNUM = 500;
	private final String OLDBOOKKEY = "AIzaSyC5hoQ5DzZ1Qtj-sBzrpc6NYEbE8b9KTBM";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Sender sender = new Sender(OLDBOOKKEY); // 구글 코드에서 발급받은 서버 키

		String GCMID = null;
		String sellerID = req.getParameter("sellerID");
		String myID = req.getParameter("myID");
		String title = req.getParameter("sellerTitle");
		String phone = req.getParameter("phone");
		
		Message msg = new Message.Builder()
						.addData("push", "제목:" + title + ", 구매자:" + myID + ", 번호:" + phone)
						.build();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Member").addSort("date",
				Query.SortDirection.DESCENDING);

		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(MAXNUM));
		if (entities.isEmpty()) {
			resp.getWriter().print("null");
		} else {
			for (Entity entity : entities) {
				if (sellerID.equals(entity.getProperty("ID").toString())) {
					GCMID = entity.getProperty("GCMID").toString();
					break;
				}
			}
		}
		
		// 푸시 전송. 파라미터는 푸시 내용, 보낼 단말의 id, 마지막은 잘 모르겠음

		Result result = sender.send(msg, GCMID, 5);

		// 결과 처리

		if (result.getMessageId() != null) {
			// 푸시 전송 성공
			resp.getWriter().print("succeed push");
		}

		else {

			String error = result.getErrorCodeName(); // 에러 내용 받기

			// 에러 처리

			if (Constants.ERROR_INTERNAL_SERVER_ERROR.equals(error)) {

				// 구글 푸시 서버 에러
				resp.getWriter().print("google server error");
			} else
				resp.getWriter().print(error);
		}
		// 정보 저장하기
	}
}