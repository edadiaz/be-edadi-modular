package com.az.edadi.message.config;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.CloseStatus;
import org.springframework.stereotype.Component;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

     @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Yeni bağlantı kuruldu: " + session.getId());
    }

     @Override
    public void handleMessage(WebSocketSession session, org.springframework.web.socket.WebSocketMessage<?> message) throws Exception {
        System.out.println("payload"+message.getPayload());
        session.sendMessage(new TextMessage("Mesaj alındı: " + message.getPayload()));
    }

     @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Bağlantı kapatıldı: " + session.getId());
    }

    // WebSocket hatalarını ele alır
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Hata oluştu: " + exception.getMessage());
    }

    // WebSocket bağlantısının kapanıp kapanamayacağını belirler
    @Override
    public boolean supportsPartialMessages() {
        return false; // Tam mesajlar için 'false' döndür
    }
}