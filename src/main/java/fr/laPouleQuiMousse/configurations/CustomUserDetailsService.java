package fr.laPouleQuiMousse.configurations;

import fr.laPouleQuiMousse.models.Role;
import fr.laPouleQuiMousse.models.User;
import fr.laPouleQuiMousse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null)
            throw new UsernameNotFoundException("");

        User user = userRepository.findByEmail(username);

        if (user == null)
            throw new UsernameNotFoundException("");

        List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new CustomUser(user.getId(), user.getUsername(), user.getEncodedPassword(), user.isActive(), true, true, true, authorities);
    }
}
