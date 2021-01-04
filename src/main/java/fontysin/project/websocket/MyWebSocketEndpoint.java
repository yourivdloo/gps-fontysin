package fontysin.project.websocket;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Indicates that this class is a websocket endpoint with URL "/server-endpoint"
 */
@CrossOrigin
@ServerEndpoint("/server-endpoint")
public class MyWebSocketEndpoint {

    /**
     * Container calls this method when browser connects to this endpoint.
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Session Open [" + session.getId() + "]");

        // Start a separate thread which will send messages back to browser.
        new Thread(new ServerToBrowserMessageSender(session)).start();
    }

    /**
     * Container calls this method when websocket connection is closed by browser or
     * server.
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("Session Close [" + session.getId() + "]");
    }

    /**
     * Process message received from browser.
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message received [" + session.getId() + "] Message=" + message);

    }

    /**
     * Container calls this method when there is an error in websocket
     * communication.
     */
    @OnError
    public void onError(Throwable t) {
        System.out.println("Error - " + t.getMessage());
    }
}
