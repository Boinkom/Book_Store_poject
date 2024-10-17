    package org.example.filter;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletInputStream;
    import jakarta.servlet.http.Cookie;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.example.dto.LoginPasswordDto;
    import org.example.models.UserDetailsEntity;
    import org.example.utils.JWTUtils;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.AuthenticationException;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    import java.io.IOException;
    import java.time.Duration;
    import java.util.HashMap;
    import java.util.Map;

    import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

    @Slf4j
    @RequiredArgsConstructor
    public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

        private final JWTUtils jwtUtils;

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

            String username = "";
            String password = "";

            try (ServletInputStream inputStream = request.getInputStream()) {
                LoginPasswordDto loginPasswordDTO = new ObjectMapper().readValue(inputStream, LoginPasswordDto.class);
                username = loginPasswordDTO.getUsername();
                password = loginPasswordDTO.getPassword();
            } catch (IOException ex) {
                log.error("Invalid login request", ex);
            }
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            return this.getAuthenticationManager()
                    .authenticate(token);

        }

        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                FilterChain chain, Authentication authResult) throws IOException {

            String jwtToken = jwtUtils.generateAccessToken((UserDetailsEntity) authResult.getPrincipal());


            Cookie jwtCookie = new Cookie("access_token", jwtToken);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(24 * 60 * 60);

            response.addCookie(jwtCookie);


            response.sendRedirect("/main-form");
        }

        @Override
        protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(APPLICATION_JSON_VALUE);
            Map<String, String> error = new HashMap<>();

            error.put("error_message", failed.getMessage());

            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

    }
