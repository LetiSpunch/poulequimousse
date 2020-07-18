package fr.laPouleQuiMousse.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * cf. http://www.baeldung.com/spring-security-method-security
 */
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true, // active la sécurisation avec les annotations @PreAuthorize et @PostAuthorize
        securedEnabled = true,  // active la sécurisation avec l'annotation @Secured
        jsr250Enabled = true   // active la sécurisation avec l'annotation @RoleAllowed
)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
}