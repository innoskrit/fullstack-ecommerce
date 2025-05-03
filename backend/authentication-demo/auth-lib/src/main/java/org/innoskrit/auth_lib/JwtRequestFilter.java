package org.innoskrit.auth_lib;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.innoskrit.auth_lib.exception.ServerException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final String[] permittedPaths;

    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, String[] permittedPaths) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.permittedPaths = permittedPaths;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Skip the filtering for the permitted paths
        String path = request.getRequestURI();
        for (String permittedPath : permittedPaths) {
            if (path.matches(permittedPath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);
            try {
                // Validate the token
                if (jwtTokenUtil.isTokenExpired(token)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token is expired!");
                    return;
                }

                Long userId = jwtTokenUtil.extractUserId(token);
                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    List<String> roles = jwtTokenUtil.extractRoles(token);
                    var authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    CustomUserPrincipal principal = new CustomUserPrincipal(userId, authorities);
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, null, authorities));
                }
            } catch (Exception ex) {
                System.out.println("Invalid token!" + ex);
                throw new ServerException("Invalid token!");
            }
        }

        filterChain.doFilter(request, response);
    }
}
