package com.tingyu.security.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class TokenUtil {

    private String accessToken = "";

    private String refreshToken = "";

    @Resource
    private RestTemplate restTemplate;

    public String getData(String code) {
        if (StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(code)) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("code", code);
            map.add("client_id", "client");
            map.add("client_secret", "client");
            map.add("redirect_uri", "http://localhost:8082/index.html");
            map.add("grant_type", "authorization_code");
            Map<String,String> resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
            accessToken = resp.get("access_token");
            refreshToken = resp.get("refresh_token");
        }
        return loadDataFromResourceServer();
    }

    private String loadDataFromResourceServer() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> entity = restTemplate.exchange("http://localhost:8081/admin/hello", HttpMethod.GET, httpEntity, String.class);
            return entity.getBody();
        } catch (Exception e) {
            return "未加载";
        }
    }

    @Scheduled(cron = "0 55 0/1 * * ？")
    public void refresh() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "client");
        map.add("client_secret", "client");
        map.add("refresh_token", refreshToken);
        map.add("grant_type", "refresh_token");
        Map<String,String> resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
        accessToken = resp.get("access_token");
        refreshToken = resp.get("refresh_token");
    }

}
