package org.example.smartkitchen.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityPropertiesConfig {

    private String secretKey;
    private Duration tokenLifetime;
    private String usernameClaimName;
    private String authoritiesClaimName;
    private String emailClaimName;
    private List<String> permitAllRequests;
    private String securityUrlPattern;
}
