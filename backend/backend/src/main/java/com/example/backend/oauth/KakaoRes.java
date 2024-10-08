package com.example.backend.oauth;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoRes {
    private String tokenType;              // 토큰 타입
    private String accessToken;            // 사용자 액세스 토큰 값
    private String idToken;                // ID 토큰 값
    private Integer expiresIn;              // 액세스 토큰과 ID 토큰의 만료 시간(초)
    private String refreshToken;           // 사용자 리프레시 토큰 값
    private Integer refreshTokenExpiresIn; // 리프레시 토큰 만료 시간(초)
    private String scope;                  // 인증된 사용자의 정보 조회 권한 범위

    @Builder
    public KakaoRes(String tokenType, String accessToken, String idToken, Integer expiresIn,
                         String refreshToken, Integer refreshTokenExpiresIn, String scope) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.idToken = idToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        this.scope = scope;
    }
}

