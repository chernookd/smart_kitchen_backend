package org.example.smartkitchen.security.services.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.example.smartkitchen.config.SecurityPropertiesConfig;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtDecoder {
    private final SecurityPropertiesConfig securityPropertiesConfig;

    public DecodedJWT decode(String token) {
        System.out.println(token);
        return JWT.require(Algorithm.HMAC256(securityPropertiesConfig.getSecretKey()))
                .build()
                .verify(token);
    }
}
