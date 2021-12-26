package avito.converter.service;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConverterServiceImpl implements ConverterService {
    private final String prettyHost="http://no.sky";
    private final PrettyUrlRepository prettyUrlRepository;
    private final UserRepository userRepository;

    @Override
    public String getPrettyUrl(String alias, URL url) {
        return null;
    }


    private void saveUrlToUser(User user, PrettyUrl prettyUrl){
        Set<PrettyUrl> prettyUrls = user.getUrls();
        prettyUrls.add(prettyUrl);
        userRepository.save(user);
    }

    private PrettyUrl saveUrl(URL url, User user) throws MalformedURLException {
        PrettyUrl prettyUrl = new PrettyUrl();
        prettyUrl.setNewUrl(generateResultUrl());
        prettyUrl.setOldUrl(url);
        prettyUrl.setUser(user);
        prettyUrlRepository.save(prettyUrl);
        return prettyUrl;
    }



    private URL generateResultUrl() throws MalformedURLException {
        return new URL(prettyHost +
                generatePrettyAfterHostName());
    }

    private String generatePrettyAfterHostName(){
        Object o = new Object();
        String[] seperatedObject = o.toString().split("@");
        return seperatedObject[1];
    }


}
