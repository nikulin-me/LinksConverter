package avito.converter.repository;

import avito.converter.domain.PrettyUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PrettyUrlRepository extends JpaRepository<PrettyUrl,Long> {

    Set<PrettyUrl> findByUserId(Long userId);
}
