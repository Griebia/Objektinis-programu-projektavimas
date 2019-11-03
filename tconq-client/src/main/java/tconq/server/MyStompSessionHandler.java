package tconq.server;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import tconq.entity.Entity;

import java.util.List;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        ServerHandler.sessionId = session.getSessionId();

        session.subscribe("/user/" + session.getSessionId() + "/Entities", this);
//        session.send("/app/ent", entities);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
//        logger.info("Received : " + msg.getText()+ " from : " + msg.getFrom());
    }
}
