package avito.converter.repository;

import avito.converter.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{
    private UserRepository userRepository;

}
