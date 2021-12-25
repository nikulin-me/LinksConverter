package avito.converter.service;

import avito.converter.repository.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CookiesHandler {
    private final UserService userService;
    private final String COOKIE_NAME="alias";
    private final  int COOKIE_AGE=60*60*24;

    public ResponseCookie getCookieByAlias(String alias){
        String value = getAliasValueFromCookie(alias);
        log.info("Sending cookie with {} value",value);

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
        List<String> aliasList = userService.getAllUsersByAlias();
        int size = aliasList.size();
        log.info("User not found. Creating new cookie");
        Cookie alias = new Cookie(COOKIE_NAME, "user_" + size);
        return alias.getValue();
    }
}
