package org.example.smartkitchen.security.services.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.example.smartkitchen.config.SecurityPropertiesConfig;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final SecurityPropertiesConfig securityPropertiesConfig;

    public String generateToken(Long userId, String username, String email, List<String> roles) {


        String usernameClaimName = securityPropertiesConfig.getUsernameClaimName();
        String authoritiesClaimName = securityPropertiesConfig.getAuthoritiesClaimName();
        String emailClaimName = securityPropertiesConfig.getEmailClaimName();

        Algorithm algorithm = Algorithm.HMAC256(securityPropertiesConfig.getSecretKey());
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + securityPropertiesConfig.getTokenLifetime().toMillis());

        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withClaim(usernameClaimName, username)
                .withClaim(authoritiesClaimName, roles)
                .withClaim(emailClaimName, email)
                .withIssuedAt(now)
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }
}
