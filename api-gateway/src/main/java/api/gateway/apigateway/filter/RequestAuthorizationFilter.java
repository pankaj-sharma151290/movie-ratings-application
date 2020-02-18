package api.gateway.apigateway.filter;

import api.gateway.apigateway.config.SecurityConfig;
import api.gateway.apigateway.utils.AuthorizationTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class RequestAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private AuthorizationTokenUtils authorizationTokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    Logger logger;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(securityConfig.getHeader());
        String username = null;
        String authorizationToken = null;
        if (!StringUtils.isBlank(authorizationHeader) && authorizationHeader.startsWith(securityConfig.getPrefix())) {
            authorizationToken = authorizationHeader.replace(securityConfig.getPrefix(), "");
            try {
                username = authorizationTokenUtils.getUsernameFromToken(authorizationToken);
            } catch (IllegalArgumentException | ExpiredJwtException e) {
                logger.error("Either Authentication token is expired or provided illegal argument", e);
            }
        } else
            logger.warn("JWT Token does not begin with Bearer String or not provided");

        //validate token
        if (!StringUtils.isBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (authorizationTokenUtils.validateToken(authorizationToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
