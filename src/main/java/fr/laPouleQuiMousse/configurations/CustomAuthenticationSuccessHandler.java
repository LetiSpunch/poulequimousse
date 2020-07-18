package fr.laPouleQuiMousse.configurations;

import fr.laPouleQuiMousse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomUser cUser = (CustomUser)authentication.getPrincipal();
        userService.refreshLastLoginDate(cUser.getDatabaseId());
        response.sendRedirect("/tableau-de-bord");
    }
}
