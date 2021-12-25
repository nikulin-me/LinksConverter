package avito.converter.repository;

import avito.converter.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;


    public boolean createNewUser(User user){
        userRepository.save(user);
        return true;
    }

    public List<String> getAllUsersByAlias(){
        return userRepository.findAllByAlias();
    }
}
