package com.example.backend.oauth.service;

import com.example.backend.oauth.token.OAuthToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public interface OAuthService {
    OAuthToken getToken(String code);

}
