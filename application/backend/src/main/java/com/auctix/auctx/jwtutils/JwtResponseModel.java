package com.auctix.auctx.jwtutils;

import java.io.Serial;
import java.io.Serializable;

public class JwtResponseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String token;

    public JwtResponseModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
