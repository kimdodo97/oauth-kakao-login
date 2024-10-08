package com.example.backend.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";

    @Value("${kakao.id}")
    private String clientId;

    @Value("${kakao.secret}")
    private String clientSecret;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    @PostConstruct
    private void init(){
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public KakaoRes loginKakaoToklen(String code){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String requestBody = String.format("grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                clientId, redirectUri, code);

        if (clientSecret != null && !clientSecret.isEmpty()) {
            requestBody += String.format("&client_secret=%s", clientSecret);
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, entity, String.class);
        try {
            return objectMapper.readValue(response.getBody(), KakaoRes.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to TokenResponse", e);
        }
    }
}
