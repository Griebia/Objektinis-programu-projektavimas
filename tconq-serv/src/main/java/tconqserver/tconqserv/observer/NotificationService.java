package tconqserver.tconqserv.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.support.MessageHeaderInitializer;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate = new SimpMessagingTemplate(new MessageChannel() {
        @Override
        public boolean send(Message<?> message, long l) {
            if (message != null)
                return true;
            return false;
        }
    });

    private MessageHeaderInitializer headerInitializer;

    public void notifyClient(String message, String sessionId){
        messagingTemplate.setSendTimeout(600);
        messagingTemplate.convertAndSend(sessionId, message);
    }

    public MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
