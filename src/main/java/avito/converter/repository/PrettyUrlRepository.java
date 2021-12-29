package avito.converter.repository;

import avito.converter.domain.PrettyUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.URL;
import java.util.List;
import java.util.Optional;

public interface PrettyUrlRepository extends JpaRepository<PrettyUrl,Long> {

    Optional<List<PrettyUrl>> findByUserId(Long userId);

    PrettyUrl findByNewUrl(URL newUrl);
}
