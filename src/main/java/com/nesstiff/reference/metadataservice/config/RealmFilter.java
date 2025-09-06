package com.nesstiff.reference.metadataservice.config;

import com.auth0.jwt.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.logging.Logger;

@Component
@Order(Integer.MIN_VALUE)
@Slf4j
public class RealmFilter extends OncePerRequestFilter {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final TokenConfig tokenConfig = new TokenConfig();
    private final RequestMatcherBinder requestMatcherBinder;
    @Value("${gateway.address}")
    private String gatewayAddress;
    @Value("${gateway.cluster}")
    private String gatewayCluster;
    @Value("${use-application-in-local}")
    private Boolean useLocalApplication;

    public RealmFilter(RequestMatcherBinder requestMatcherBinder) {
        this.requestMatcherBinder = requestMatcherBinder;
    }

    private String extractTokenFromRequest(ServletRequest request) throws Exception {

        // Assuming the token is passed in the "Authorization" header using the "Bearer" scheme
        String authorizationHeader = ((HttpServletRequest) request).getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return tokenConfig.decryptToken(authorizationHeader.substring(7));
        } else {
            throw new AuthenticationException("Invalid or missing Authorization header");

        }

    }


    private String retrieveRealmFromToken(String token) {


        String iss = JWT.decode(token).getClaim("iss").asString();


        String[] parts = iss.split("/");
        return parts[parts.length - 1];
    }

    private String retrieveClientFromToken(String token) {


        return JWT.decode(token).getClaim("azp").asString();


    }

    private String cleanUrl(String url) {
        if (url == null) return "";
        url = url.trim().toLowerCase();
        if (url.startsWith("http://")) {
            url = url.substring(7);
        } else if (url.startsWith("https://")) {
            url = url.substring(8);
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String forwardedForHeader = request.getHeader("X-Forwarded-Host");
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/actuator")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!useLocalApplication) {
            if (gatewayAddress != null) {
                if (forwardedForHeader == null) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access is denied.");
                    return;
                }

                String[] hosts = forwardedForHeader.split("\\s*,\\s*");
                String cleanGatewayAddress = cleanUrl(gatewayAddress);
                String cleanGatewayCluster = cleanUrl(gatewayCluster);
                boolean matchFound = false;
                for (String host : hosts) {
                    String cleanHost = cleanUrl(host);
                    if (cleanHost.equalsIgnoreCase(cleanGatewayAddress)
                            || cleanHost.equalsIgnoreCase(cleanGatewayCluster)) {
                        matchFound = true;
                        break;
                    }
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access is denied.");
                    return;


                }


            }
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }
        if (isInAuthWhitelist(requestURI)) {
            filterChain.doFilter(request, response);
        } else {
            String token = null;
            try {
                token = extractTokenFromRequest(request);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            filterChain.doFilter(request, response);

        }

    }

    private boolean isInAuthWhitelist(String requestURI) {
        for (String endpoint : requestMatcherBinder.getPermitsAllUrls()) {
            if (endpoint.endsWith("/**")) {
                String prefix = endpoint.substring(0, endpoint.length() - 3);
                if (requestURI.equals(prefix) || requestURI.startsWith(prefix + "/")) {
                    return true;
                }
            } else if (endpoint.equals(requestURI)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCorsAuthWhitelist(String requestURI) {

        final String[] witheList = {"/webjars/**",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/actuator/**"};
        for (String endpoint : witheList) {
            if (endpoint.endsWith("/**")) {
                String prefix = endpoint.substring(0, endpoint.length() - 3);
                if (requestURI.equals(prefix) || requestURI.startsWith(prefix + "/")) {
                    return true;
                }
            } else if (endpoint.equals(requestURI)) {
                return true;
            }
        }
        return false;
    }

}
