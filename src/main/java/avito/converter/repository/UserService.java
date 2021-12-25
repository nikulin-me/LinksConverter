package avito.converter.repository;

import avito.converter.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{
    private UserRepository userRepository;

    public User getUserByAlias(String alias){
        Optional<User> userOptional = userRepository.findByAlias(alias);
        return userOptional.orElseGet(() -> createUser(alias));
    }

    private User createUser(String alias) {
        User user = new User();
        return null;
    }
}
