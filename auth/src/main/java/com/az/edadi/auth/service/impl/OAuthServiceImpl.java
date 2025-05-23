package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.model.response.OAuth2CustomUser;
import com.az.edadi.auth.service.OAuthService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
@Service
public class OAuthServiceImpl implements OAuthService {
    @Override
    public OAuth2CustomUser getGoogleUser(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://www.googleapis.com/oauth2/v2/userinfo";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<OAuth2CustomUser> result = restTemplate.exchange(uri, HttpMethod.GET, entity, OAuth2CustomUser.class);
        return result.getBody();
    }

    @Override
    public OAuth2CustomUser getFacebookUser(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token=" + token;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<OAuth2CustomUser> result = restTemplate.exchange(uri, HttpMethod.GET, entity, OAuth2CustomUser.class);
        return result.getBody();
    }


}
