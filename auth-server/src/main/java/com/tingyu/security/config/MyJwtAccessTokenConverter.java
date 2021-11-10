package com.tingyu.security.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class MyJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalMap = new HashMap<>();
        Map<String, Object> info = new HashMap<>();
        info.put("author", "nameicc");
        info.put("age", "26");
        info.put("address", "Qingdao");
        additionalMap.put("info", info);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalMap);
        return super.enhance(accessToken, authentication);
    }
}
