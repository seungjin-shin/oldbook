package push;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.xmpp.Subscription;
import com.google.appengine.api.xmpp.SubscriptionType;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

@SuppressWarnings("serial")
public class SubscriptionServlet extends HttpServlet {
   
    XMPPService xmppService = XMPPServiceFactory.getXMPPService();
   
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Subscription subscription = xmppService.parseSubscription(request);
        
        if( subscription.getSubscriptionType() == SubscriptionType.SUBSCRIBE ) {

        }
        String from = subscription.getFromJid().getId();
        String to = subscription.getToJid().getId();
       
        System.out.println("From : " + from);
        System.out.println("To : " + to);
    }
}