package com.example.backend.controller;

import com.example.backend.oauth.KakaoRes;
import com.example.backend.oauth.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @PostMapping("login/oauth2/callback/kakao")
    public ResponseEntity<KakaoRes> postKakao(@RequestParam String code){
        KakaoRes kakaoRes = oAuthService.loginKakaoToklen(code);
        return ResponseEntity.ok(kakaoRes);
    }
}
