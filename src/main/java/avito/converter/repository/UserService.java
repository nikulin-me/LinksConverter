package avito.converter.repository;

import avito.converter.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;


    public User createNewUser(String alias){
        User user = new User();
        user.setAlias(alias);
        log.info("Created new user with name {}",alias);
        userRepository.save(user);
        return user;
    }

    public List<String> getAllUsersAlias(){
        return userRepository.findAllAlias();
    }

    public Optional<User> getUserByAlias(String alias){
        return userRepository.findByAlias(alias);
    }
}
