package avito.converter.service.sender;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import avito.converter.exception.NotValidURL;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserService;
import avito.converter.service.convert.ConverterService;
import avito.converter.service.validator.ValidUrlChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlSenderServiceImpl implements UrlSenderService {
    private final PrettyUrlRepository prettyUrlRepository;
    private final UserService userService;
    private final ConverterService converterService;
    private final ValidUrlChecker validUrlChecker;

    @Override
    public List<PrettyUrl> getAllUrls(String alias) {
        User user = userService.authenticateUser(alias);
        log.info("Got all urls from {}",alias);
        Optional<List<PrettyUrl>> urls = prettyUrlRepository.findByUserId(user.getId());
        return urls.orElse(null);

    }

    @Override
    public URL createNewUrlFromOld(String alias, URL oldUrl) throws IOException {
        log.info("Creating newURL for {}",alias);
        if (validUrlChecker.validateURl(oldUrl)){
            throw new NotValidURL();
        }
        return converterService.createNewUrlFromOld(alias,oldUrl);
    }

    @Override
    public URL getOldUrl(URL newUrl) {
        PrettyUrl url = prettyUrlRepository.findByNewUrl(newUrl);
        log.info("Redirecting to {} from {}",url,newUrl);
        return url.getOldUrl();
    }
}
