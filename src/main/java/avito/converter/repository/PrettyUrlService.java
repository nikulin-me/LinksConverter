package avito.converter.repository;

import avito.converter.domain.PrettyUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrettyUrlService {
    private final PrettyUrlRepository repository;
    @Value("${hostname}")
    private final String hostname;


    public URL updateURL(URL oldURL, URL url) throws MalformedURLException {
        PrettyUrl updatableUrl = repository.findByNewUrl(oldURL);
        URL validatedUrl = validateUrl(url);

        updatableUrl.setNewUrl(validatedUrl);
        repository.save(updatableUrl);

        log.info("{} was changed to {}",oldURL,validatedUrl);
        return updatableUrl.getNewUrl();
    }

    public URL validateUrl(URL url) throws MalformedURLException {
        log.info("Validating {} to convention",url);
        return new URL(hostname+url.getPath());
    }


    public URL getUrl(Long oldURLId) {
        Optional<PrettyUrl> url = repository.findById(oldURLId);
        return url.map(PrettyUrl::getNewUrl).orElse(null);
    }
}
