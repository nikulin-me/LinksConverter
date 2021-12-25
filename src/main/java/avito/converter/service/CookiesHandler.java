package avito.converter.service;

import avito.converter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CookiesHandler {
    private final UserRepository userRepository;
    private final String COOKIE_NAME="alias";
    private final  int COOKIE_AGE=60*60*24;


    public ResponseCookie getCookieByAlias(String alias){
        String value = getAliasValueFromCookie(alias);
        return  ResponseCookie.from(COOKIE_NAME,value)
                .maxAge(COOKIE_AGE)
                .build();
    }

    private String getAliasValueFromCookie(String alias){
        if (alias!=null){
            return alias;
        }
        else{
            return addingAliasValueCookie();
        }
    }


    private String addingAliasValueCookie() {
        List<String> aliasList = userRepository.findAllByAlias();
        int size = aliasList.size();
        Cookie alias = new Cookie(COOKIE_NAME, "user_" + size);
        return alias.getValue();
    }
}
