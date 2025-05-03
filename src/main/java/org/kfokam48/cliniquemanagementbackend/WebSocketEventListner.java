package org.kfokam48.cliniquemanagementbackend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListner {
    @EventListener
    public void handleWebSocketDisconnectListner(SessionDisconnectEvent event) {
        log.info("Client disconnected: " + event.getSessionId());
        // Handle the disconnection event here, if needed
        // For example, you can remove the session from a list of active sessions
        // or perform any other necessary cleanup.
    }
}
