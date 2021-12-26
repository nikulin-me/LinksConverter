package avito.converter.service;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserRepository;
import avito.converter.repository.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConverterServiceImpl implements ConverterService {
    private final String prettyHost="http://no.sky/";
    private final PrettyUrlRepository prettyUrlRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public URL getPrettyUrl(String alias, URL url) throws MalformedURLException {
        User user = authenticateOrCreateUser(alias);
        Set<PrettyUrl> prettyUrls=user.getUrls();
        for (PrettyUrl prettyUrl : prettyUrls) {
            if (prettyUrl.getOldUrl().equals(url)){
                return new URL(prettyUrl.getNewUrl());
            }
        }
        PrettyUrl prettyUrl = buildPrettyURL(url, user);
        addUrlToUser(prettyUrl,user);
        return new URL(prettyUrl.getNewUrl());
    }

    private User authenticateOrCreateUser(String alias){
        Optional<User> user = userService.getUserByAlias(alias);
        if (user.isEmpty()){
            return userService.createNewUser(alias);
        }
        else{
            return user.get();
        }
    }

    private PrettyUrl buildPrettyURL(URL oldUrl, User user) throws MalformedURLException {
        List<String> linkOfObject = List.of(new Object().toString().split("@"));
        String newPart = linkOfObject.get(1);
        String  newUrl = prettyHost + newPart;
        PrettyUrl prettyUrl = new PrettyUrl(null,user,oldUrl,newUrl);
        prettyUrlRepository.save(prettyUrl);
        return prettyUrl;
    }

    private boolean addUrlToUser(PrettyUrl newUrl,User user){
        Optional<User> userData = userRepository.findById(user.getId());
        if (userData.isPresent()){
            Set<PrettyUrl> urls = userData.get().getUrls();
            urls.add(newUrl);
            userRepository.save(user);
            return true;
        }
        else{
            return false;
        }
    }
}
