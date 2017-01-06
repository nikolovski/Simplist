package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

/**
 * Project: Simplist
 *
 * @author d4k1d23
 * @date 1/4/17
 */
@Component
public class TokenResponse {
    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "expires_in")
    private long expiresIn;
    @JsonProperty(value = "refresh_token")
    private String refreshToken;
    @JsonProperty(value = "refresh_token_expires_in")
    private long refreshTokenExpiresIn;

    public TokenResponse() {}

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(long refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }
    @Override
    public String toString() {
        return "TokenResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiresIn='" + refreshTokenExpiresIn + '\'' +
                '}';
    }
}
