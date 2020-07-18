package fr.laPouleQuiMousse.configurations;

import fr.laPouleQuiMousse.models.User;
import fr.laPouleQuiMousse.services.ConnectedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Autowired
    private ConnectedUserService connectedUser;

    @Bean
    public AuditorAware<User> auditorProvider() {

        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
         */
        return () -> Optional.ofNullable(connectedUser.getUser());
    }
}

