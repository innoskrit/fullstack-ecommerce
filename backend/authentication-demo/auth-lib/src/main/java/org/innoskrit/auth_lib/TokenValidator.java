package org.innoskrit.auth_lib;

public class TokenValidator {

    private final JwtTokenUtil jwtTokenUtil;

    public TokenValidator(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public boolean isValid(String token) {
        try {
            return !jwtTokenUtil.isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return jwtTokenUtil.extractUsername(token);
    }
}

