package avito.converter.repository;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByAlias(String alias);

    @Query("select u.alias from User u")
    List<String> findAllByAlias();
}
