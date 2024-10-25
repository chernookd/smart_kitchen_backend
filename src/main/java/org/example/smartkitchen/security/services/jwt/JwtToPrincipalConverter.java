package org.example.smartkitchen.security.services.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.example.smartkitchen.config.SecurityPropertiesConfig;
import org.example.smartkitchen.security.userPrincipal.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtToPrincipalConverter {
    private final SecurityPropertiesConfig securityPropertiesConfig;

    public UserPrincipal convert(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .email(jwt.getClaim(securityPropertiesConfig.getEmailClaimName()).asString())
                .username(jwt.getClaim(securityPropertiesConfig.getUsernameClaimName()).asString())
                .authorities(extractAuthorities(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT jwt) {
        Claim claim = jwt.getClaim(securityPropertiesConfig.getAuthoritiesClaimName());

        if (claim.isNull() || claim.isMissing()) {
            return List.of();
        }

        return claim.asList(SimpleGrantedAuthority.class);
    }
}
