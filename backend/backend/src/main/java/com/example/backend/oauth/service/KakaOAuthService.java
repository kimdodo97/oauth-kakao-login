package com.example.backend.oauth.service;

import com.example.backend.oauth.req.TokenReq;
import com.example.backend.oauth.userinfo.KakaoOAuth2UserInfo;
import com.example.backend.oauth.userinfo.OAuth2UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaOAuthService {
    private final RestTemplate oauth2Client;
    private final ObjectMapper objectMapper;

    @Value("${oauth2.register.kakao.redirect_uri}")
    private String REDIRECT_URI;

    @Value("${oauth2.register.kakao.id}")
    private String CLIENT_ID;

    @Value("${oauth2.register.kakao.secret}")
    private String CLIENT_SECRET;

    @Value("${oauth2.provider.kakao.token-uri}")
    private String TOKEN_URL;

    @Value("${oauth2.provider.kakao.user-info-uri}")
    private String USER_INFO_URI;

    @PostConstruct
    public void init(){
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public String loginOAuth(String authorizationCode){
        try {
            String authToken = getOAuthToken(authorizationCode);
            OAuth2UserInfo userInfo = getUserInfo(authToken);
            return userInfo.getEmail();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public String getOAuthToken(String code) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", CLIENT_ID);
        body.add("redirect_uri", REDIRECT_URI);
        body.add("code", code);
        body.add("client_secret", CLIENT_SECRET); // 클라이언트 시크릿이 필요한 경우 추가

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = oauth2Client.exchange(TOKEN_URL, HttpMethod.POST, requestEntity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        return (String) responseBody.get("access_token");
    }

    public OAuth2UserInfo getUserInfo(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = oauth2Client.exchange(USER_INFO_URI, HttpMethod.GET, requestEntity, Map.class);

        Map<String, Object> responseBody = response.getBody();

        OAuth2UserInfo userInfo = new KakaoOAuth2UserInfo(responseBody);
        return userInfo;
    }
}
