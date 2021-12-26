package avito.converter.repository;

import avito.converter.domain.PrettyUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PrettyUrlRepository extends JpaRepository<PrettyUrl,Long> {

    Optional<List<PrettyUrl>> findByUserId(Long userId);
}
