package com.az.edadi.message.socket.config;

import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.service.JwtService;
import com.az.edadi.message.model.StompPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticateSocketUser extends DefaultHandshakeHandler {

    private final JwtService jwtService;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {

        String token = getTokenFromRequest(request);
        var tokenBody = jwtService.getTokenBody(TokenType.ACCESS_TOKEN, token);
        System.out.println("User id: " + tokenBody.getUserId());
        return new StompPrincipal(tokenBody.getUserId(), tokenBody.getTokenId());
    }

    private String getTokenFromRequest(ServerHttpRequest request) {
        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromUri(request.getURI())
                .build()
                .getQueryParams();
        return Optional.ofNullable(queryParams.getFirst("access_token")).orElseThrow();
    }

}
