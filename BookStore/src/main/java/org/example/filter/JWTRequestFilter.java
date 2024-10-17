package org.example.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JWTUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = null;

        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/a/css/") || requestURI.startsWith("/a/script/") || requestURI.startsWith("/a/image/") || "/".equals(requestURI) || "/register".equals(requestURI) || "/register-user".equals(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("access_token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
           try {
                DecodedJWT decodedJWT = jwtUtils.validateToken(token);
               PreAuthenticatedAuthenticationToken authentication = jwtUtils.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
           } catch (JWTVerificationException e) {

                log.error("Invalid JWT token: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"error\": \"Invalid JWT token\"}");
               return;
          }
       }

        chain.doFilter(request, response);
    }
}
