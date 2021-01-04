package fontysin.project.websocket;

import javax.websocket.Session;

/**
 * This is a custom thread which sends message to browser every 2000
 * milliseconds.
 */
public class ServerToBrowserMessageSender extends Thread {

    private int count = 0;
    private Session session;

    public ServerToBrowserMessageSender(Session session) {
        this.session = session;
    }

    @Override
    public void run() {

        while (count < 5) {
            count++;
            try {
                // Send message to browser ever 2000 milliseconds.
                Thread.sleep(2000);
                session.getBasicRemote()
                        .sendText("[Server -> Browser] A Message from server to browser. Count = " + count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}