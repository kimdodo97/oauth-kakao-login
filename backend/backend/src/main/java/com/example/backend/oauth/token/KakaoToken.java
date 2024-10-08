package com.example.backend.oauth.token;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Getter
public class KakaoToken {
    private String toeknType;
    private String accessToken;
    private String idToken;
    private String expiresIn;
    private String refreshToken;
    private String refreshExpiresIn;
    private String scope;

    @Builder
    public KakaoToken(String toeknType, String accessToken, String idToken, String expiresIn, String refreshToken, String refreshExpiresIn, String scope) {
        this.toeknType = toeknType;
        this.accessToken = accessToken;
        this.idToken = idToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshExpiresIn = refreshExpiresIn;
        this.scope = scope;
    }
}
