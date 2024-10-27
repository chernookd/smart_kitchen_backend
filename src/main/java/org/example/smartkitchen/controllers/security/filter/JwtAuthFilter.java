package org.example.smartkitchen.controllers.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartkitchen.security.services.jwt.JwtDecoder;
import org.example.smartkitchen.security.services.jwt.JwtToPrincipalConverter;
import org.example.smartkitchen.security.userPrincipal.UserPrincipalAuthToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;

    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";
    private static final int TOKEN_BEGIN_INDEX = 7;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        extractTokenFromRequest(request)
                .map(jwtDecoder::decode)
                .map(jwtToPrincipalConverter::convert)
                .map(UserPrincipalAuthToken::new)
                .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));

        filterChain.doFilter(request, response);

        log.info("filter");

    }

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasText(token) && token.startsWith(TOKEN_TYPE)) {
            return Optional.of(token.substring(TOKEN_BEGIN_INDEX));
        }

        return Optional.empty();
    }
}
