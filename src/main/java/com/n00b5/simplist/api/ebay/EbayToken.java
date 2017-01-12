package com.n00b5.simplist.api.ebay;

import com.fasterxml.jackson.annotation.JsonProperty;
import oracle.sql.CLOB;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/4/17
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "EBAY_TOKEN")
@Component
public class EbayToken {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @Column
    @JsonProperty(value = "token_type")
    private String tokenType;

    @Column
    @JsonProperty(value = "expires_in")
    private long expiresIn;

    @Column
    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @Id
    @JsonProperty(value = "refresh_token_expires_in")
    private long refreshTokenExpiresIn;

    public EbayToken() {}


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
