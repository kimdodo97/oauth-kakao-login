package com.example.backend.controller;

import com.example.backend.oauth.service.KakaOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthController {
    private final KakaOAuthService kakaOAuthService;

    @PostMapping("login/oauth2/kakao")
    public ResponseEntity<String> postKakao(@RequestParam String code){
        String logined = kakaOAuthService.loginOAuth(code);
        return ResponseEntity.ok(logined);
    }
}
