package avito.converter.service.convert;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConverterServiceImpl implements ConverterService {
    private final String prettyHost="http://no.sky/";
    private final PrettyUrlRepository prettyUrlRepository;
    private final UserService userService;

    @Override
    public URL createNewUrlFromOld(String alias, URL oldUrl) throws MalformedURLException {
        User user = userService.authenticateUser(alias);
        Optional<List<PrettyUrl>> prettyUrls = prettyUrlRepository.findByUserId(user.getId());

        log.info("Trying to upload new URL");
        if (prettyUrls.isEmpty()){
            PrettyUrl prettyUrl = buildPrettyURL(oldUrl, user);
            addUrlToUser(prettyUrl,alias);
            log.info("It`s newbie. Putting url {}", prettyUrl.getNewUrl());
            return prettyUrl.getNewUrl();
        }
        else{
            for (PrettyUrl prettyUrl : prettyUrls.get()) {
                if (prettyUrl.getOldUrl().equals(oldUrl)) {
                    log.info("Getting pretty from old {}", oldUrl);
                    return prettyUrl.getNewUrl();
                }
            }
            //if url is not found and user urls is not empty
            log.info("URL is not found. Creating new Pretty oldUrl with old {}",oldUrl);
            PrettyUrl prettyUrl = buildPrettyURL(oldUrl, user);
            addUrlToUser(prettyUrl,alias);
            return prettyUrl.getNewUrl();
        }
    }
    private PrettyUrl buildPrettyURL(URL oldUrl, User user) throws MalformedURLException {
        List<String> linkOfObject = List.of(new Object().toString().split("@"));
        String newPart = linkOfObject.get(1);
        URL  newUrl =new URL( prettyHost + newPart);

        PrettyUrl prettyUrl = new PrettyUrl();
        prettyUrl.setOldUrl(oldUrl);
        prettyUrl.setNewUrl(newUrl);
        prettyUrl.setUser(user);
        prettyUrlRepository.save(prettyUrl);
        log.info("Saved prettyUrl with {}",prettyUrl.getNewUrl());

        return prettyUrl;
    }

    public void addUrlToUser(PrettyUrl newUrl,String username){
        Optional<User> user = Optional.ofNullable(userService.authenticateUser(username));
        if (user.isPresent()){
            log.info("Trying add new url for {}",username );
            user.get().getUrls().add(newUrl);
            userService.updateUserData(user.get());
        }
        log.info("Added new url to user");
    }
}
