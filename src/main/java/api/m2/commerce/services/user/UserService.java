package api.m2.commerce.services.user;

import api.m2.commerce.entities.User;
import api.m2.commerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = this.getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public User getOrCreateUser() {
        String email = this.getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElseGet(() -> this.createUser(email));
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private User createUser(String email) {
        User user = User.builder()
                .email(email)
                .build();
        return userRepository.save(user);
    }
}
