package com.nesstiff.reference.metadataservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {


    private final String principalAttribute = "preferred_Username";
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    private String resourceId;

    private boolean isTokenExpired(Jwt token) {
        Instant expirationTime = token.getExpiresAt();
        Instant currentTime = Instant.now();

        // Compare the expiration time with the current time
        return expirationTime != null && expirationTime.isBefore(currentTime);
    }


    @Override
    public AbstractAuthenticationToken convert(Jwt source) {

        claimResourceId(source);
        Set<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(source).stream(), extractRoles(source).stream())
                .collect(Collectors.toSet());

        return new JwtAuthenticationToken(source, authorities);
    }

    private void claimResourceId(Jwt token) {
        resourceId =
                token.getClaim("azp").toString();
    }

    private String getPrincipalName(Jwt jwt) {
        String name = JwtClaimNames.SUB;
        if (principalAttribute != null) {
            name = principalAttribute;
        }
        return jwt.getClaim(name);
    }

    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaims();
        Map<String, Object> resource;
        Map<String, Object> resources;
        Collection<String> resourceRoles;

        if (resourceAccess == null
                || (resources = (Map<String, Object>) resourceAccess.get("resource_access")) == null
                || (resource = (Map<String, Object>) resources.get(resourceId)) == null
                || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
            return Set.of();
        }
        return resourceRoles.stream().
                map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toSet());
    }


}
