package org.kfokam48.cliniquemanagementbackend;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        for (WebSocketSession s : sessions) {
            try {
                s.sendMessage(new TextMessage("Message reçu : " + message.getPayload()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
