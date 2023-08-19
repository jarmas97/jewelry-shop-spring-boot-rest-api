package com.github.jarmas97.jewelryshopspringbootrestapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

@Service
public class AuthenticationService {
    static final long EXPIRATIONTIME = 86_400_000; // 1 day in milliseconds
    public static final String SIGNINKEY = "SecretKey39049the3i03of020to2003get3004";
    public static final String PREFIX = "Bearer";

    /** Add token to Authorization header **/
    static public void addToken(HttpServletResponse response, String username) {

        String JwtToken = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SIGNINKEY)
                .compact();
        response.addHeader("Authorization", PREFIX + " " + JwtToken);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    static public String extractUsernameFromToken(String token) throws SignatureException {
        return Jwts.parser()
                .setSigningKey(SIGNINKEY)
                .parseClaimsJws(token.replace(PREFIX, ""))
                .getBody()
                .getSubject();
    }

    /** Get token from Authorization header **/
    static public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String username = extractUsernameFromToken(token);
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            }
        }
        return null;
    }
}
