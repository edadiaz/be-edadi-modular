package com.az.edadi.message.socket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final AuthenticateSocketUser authenticateSocketUser;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins(
                        "http://localhost:4200",
                        "http://127.0.0.1:8080",
                        "https://develop.edadi.az"
                )
                .setHandshakeHandler(authenticateSocketUser)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // where clients will subscribe
        registry.setApplicationDestinationPrefixes("/app"); // where client will send

    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
//                String token = stompHeaderAccessor.getFirstNativeHeader("Authorization");
//
//                if (token != null) {
//                    // Extract the token value (e.g., "Bearer <token>")
//                    String bearerToken = token.startsWith("Bearer ") ? token.substring(7) : token;
//                    System.out.println("Received token: " + bearerToken);
//                    // Token validation logic goes here
//                }
//                return message;
//            }
//        });
//    }


}
