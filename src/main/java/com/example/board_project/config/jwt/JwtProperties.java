package com.example.board_project.config.jwt;

public interface JwtProperties {
    String SECRET = "κΉμν";
    int EXPIRATION_TIME = 864000000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}