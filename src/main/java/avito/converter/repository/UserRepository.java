package avito.converter.repository;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByAlias(String alias);
}
