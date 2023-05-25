package ru.drdrapp.restjwt.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.drdrapp.restjwt.services.DgUserDetailsImpl;
import ru.drdrapp.restjwt.services.DgUserDetailsServiceImpl;
import ru.drdrapp.restjwt.services.interfaces.JwtService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final DgUserDetailsServiceImpl dgUserDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, DgUserDetailsServiceImpl dgUserDetailsService) {
        this.jwtService = jwtService;
        this.dgUserDetailsService = dgUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userLogin;

        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userLogin = jwtService.extractUserName(jwt);
        if (StringUtils.isNotEmpty(userLogin) && SecurityContextHolder.getContext().getAuthentication() == null) {
            DgUserDetailsImpl dgUserDetails = dgUserDetailsService.loadUserByUsername(userLogin);
            if (jwtService.isTokenValid(jwt, dgUserDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dgUserDetails, null, dgUserDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
