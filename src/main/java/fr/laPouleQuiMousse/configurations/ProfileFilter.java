package fr.laPouleQuiMousse.configurations;

import fr.laPouleQuiMousse.models.User;
import fr.laPouleQuiMousse.services.ConnectedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ProfileFilter extends GenericFilterBean {

    @Autowired
    public ConnectedUserService connectedUserService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (auth() != null && isAuthenticated() && !((HttpServletRequest) request).getRequestURI().equals("/")) {
            User connectedUser = connectedUserService.getUser();
            if (connectedUser != null) {
                if (connectedUser.isFirstConnecting() && !connectedUserService.isAdmin()) {
                    ((HttpServletResponse) response).sendRedirect("/");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    private Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private boolean isAuthenticated() {
        return auth().isAuthenticated() && !auth().getName().equals("anonymousUser");
    }
}
