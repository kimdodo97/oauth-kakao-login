package com.example.backend.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoReq {
    private String grantType;
    private String clientId;
    private String redirectUri;
    private String code;
    private String clientSecret;

    @Builder
    public KakaoReq(String grantType, String clientId, String redirectUri, String code, String clientSecret) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.code = code;
        this.clientSecret = clientSecret;
    }
}
