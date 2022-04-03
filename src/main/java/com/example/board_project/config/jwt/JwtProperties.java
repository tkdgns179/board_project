package com.example.board_project.config.jwt;

public interface JwtProperties {
    String SECRET = "김상훈";
    int EXPIRATION_TIME = 864000000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}