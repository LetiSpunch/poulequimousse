package fr.laPouleQuiMousse.services;
import fr.laPouleQuiMousse.models.User;
import fr.laPouleQuiMousse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("userService")
public class UserService {
    @Autowired
    private ConnectedUserService connectedUserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findById(Long id) {
        Optional<User> option = userRepository.findById(id);
        if (option.isPresent())
            return option.get();
        return null;
    }

    public boolean isHimself(User user) {
        return connectedUserService.getId().equals(user.getId());
    }

    public void refreshLastLoginDate(Long id) {

        userRepository.updateLastLoginDate(id, new Date());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveToken(User user) {
        userRepository.saveToken(user.getId(), user.getToken(), user.getTokenExpirationDate());
    }

    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    public void savePassword(User user) {
        user.setEncodedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.savePassword(user.getId(), user.getEncodedPassword());
    }
}
