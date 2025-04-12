package br.com.yugiapp.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
//        if (checkIfEndpointIsNotPublic(request)) {
//            String token = recoveryToken(request);
//            if (token != null) {
//                Authentication authentication =
//                        new UsernamePasswordAuthenticationToken(null, null, new ArrayList<>());
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                throw new RuntimeException("O token está ausente.");
//            }
//        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (Objects.nonNull(authorizationHeader)) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        return !request.getRequestURI().startsWith(JwtSecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED);
    }

}