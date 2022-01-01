package avito.converter.repository;

import avito.converter.domain.PrettyUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrettyUrlService {
    private final PrettyUrlRepository repository;




    public URL updateURL(URL oldURL, URL url) {
        PrettyUrl updatableUrl = repository.findByNewUrl(oldURL);
        updatableUrl.setNewUrl(url);
        repository.save(updatableUrl);
        return updatableUrl.getNewUrl();
    }

    public URL getUrl(Long oldURLId) {
        Optional<PrettyUrl> url = repository.findById(oldURLId);
        return url.map(PrettyUrl::getNewUrl).orElse(null);
    }
}
