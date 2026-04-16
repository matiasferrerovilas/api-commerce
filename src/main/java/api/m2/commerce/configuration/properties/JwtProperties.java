package api.m2.commerce.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.resourceserver.jwt")
public record JwtProperties(String issuerUri) {
}
