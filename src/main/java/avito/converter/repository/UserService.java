package avito.converter.repository;

import avito.converter.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;


    public void createNewUser(String alias){
        User user = new User();
        user.setAlias(alias);
        log.info("Created new user with name {}",alias);
        userRepository.save(user);
    }

    public List<String> getAllUsersByAlias(){
        return userRepository.findAllByAlias();
    }
}
