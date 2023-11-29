package com.github.jarmas97.jewelryshopspringbootrestapi.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class TokenProvider {
    static final String SECRET_KEY = "dadadefawinornvqq20vn0q2v0opeew";
    static final long EXPIRATION_TIME = 864_000_00;

    public static void addToken(HttpServletResponse response, Authentication authentication) {

        String username = authentication.getName();

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
            String userRole = authorities.get(0).getAuthority();

            String token = Jwts.builder()
                    .setSubject(username)
                    .claim("role", userRole)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();

            response.addHeader("Authorization", "Bearer " + token);
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
        }
    }

    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static boolean validateToken(String token) {
        boolean result = true;
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            Date now = new Date();

            if (claims.getExpiration() != null && now.after(claims.getExpiration())) {
                System.out.println("token expired");
                result = false;
            }

            if (claims.getIssuedAt() != null && now.before(claims.getIssuedAt())) {
                System.out.println("date problem");
                result = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
