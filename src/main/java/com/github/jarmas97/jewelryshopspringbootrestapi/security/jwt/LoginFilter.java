package com.github.jarmas97.jewelryshopspringbootrestapi.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.user.User;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url, HttpMethod.POST.name()));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        // Parsing data from the request body
        InputStream requestBody = request.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(requestBody, User.class);

        // Creating an AuthenticationToken object based on the request data
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());

        // Passing the authentication request to the AuthenticationManager
        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authentication) throws IOException, ServletException
    {
        TokenProvider.addToken(response, authentication);
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException
    {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication failed: " + failed.getMessage());
    }
}
