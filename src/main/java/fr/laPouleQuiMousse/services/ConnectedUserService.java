package fr.laPouleQuiMousse.services;

import fr.laPouleQuiMousse.configurations.CustomUser;
import fr.laPouleQuiMousse.models.Enums.ERoleName;
import fr.laPouleQuiMousse.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("connectedUser")
public class ConnectedUserService {

    @Autowired
    private UserService userService;

    public String getUsername() {
        return auth().getName();
    }

    private Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean hasRole(ERoleName roleName) {
        for (GrantedAuthority authority : auth().getAuthorities()) {
            if (authority.getAuthority().equals(roleName.name()))
                return true;
        }
        return false;
    }

    public boolean isAdmin() {
        return hasRole(ERoleName.ROLE_ADMIN);
    }

    public boolean isCustomer() {
        return hasRole(ERoleName.ROLE_CUSTOMER);
    }


    public User getUser() {
        return userService.findById(getId());
    }

    public Long getId() {
        return ((CustomUser) auth().getPrincipal()).getDatabaseId();
    }
}

