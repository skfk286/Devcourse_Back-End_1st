package com.grepp.boot.jwt;

import java.util.Date;

public class Jwt {
    private final String accessToken;
    private final String refreshToken;
    private final Date accessTokenExp;
    private final Date refreshTokenExp;
    private final String loginId;
    private final String nickname;
    private final String socialAccessToken; // google, facebook, twitter 등

    // Private constructor to enforce the use of the builder
    private Jwt(Builder builder) {
        this.accessToken = builder.accessToken;
        this.refreshToken = builder.refreshToken;
        this.accessTokenExp = builder.accessTokenExp;
        this.refreshTokenExp = builder.refreshTokenExp;
        this.loginId = builder.loginId;
        this.nickname = builder.nickname;
        this.socialAccessToken = builder.socialAccessToken;
    }

    // Getters
    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Date getAccessTokenExp() {
        return accessTokenExp != null ? new Date(accessTokenExp.getTime()) : null;
    }

    public Date getRefreshTokenExp() {
        return refreshTokenExp != null ? new Date(refreshTokenExp.getTime()) : null;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getNickname() {return nickname;}

    public String getSocialAccessToken() {
        return socialAccessToken;
    }

    // Builder class
    public static class Builder {
        private String accessToken;
        private String refreshToken;
        private Date accessTokenExp;
        private Date refreshTokenExp;
        private String loginId;
        private String nickname;
        private String socialAccessToken;

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder accessTokenExp(Date accessTokenExp) {
            this.accessTokenExp = accessTokenExp != null ? new Date(accessTokenExp.getTime()) : null;
            return this;
        }

        public Builder refreshTokenExp(Date refreshTokenExp) {
            this.refreshTokenExp = refreshTokenExp != null ? new Date(refreshTokenExp.getTime()) : null;
            return this;
        }

        public Builder loginId(String loginId) {
            this.loginId = loginId;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder socialAccessToken(String socialAccessToken) {
            this.socialAccessToken = socialAccessToken;
            return this;
        }

        public Jwt build() {
            return new Jwt(this);
        }
    }

    // Static method to access the builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Jwt{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", accessTokenExp=" + accessTokenExp +
                ", refreshTokenExp=" + refreshTokenExp +
                ", loginId=" + loginId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", socialAccessToken='" + socialAccessToken + '\'' +
                '}';
    }
}
