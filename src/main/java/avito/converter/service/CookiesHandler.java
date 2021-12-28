package avito.converter.service;

import avito.converter.repository.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CookiesHandler {
    private final UserService userService;
    private final String COOKIE_NAME = "alias";
    private final int COOKIE_AGE = 60 * 60 * 24 * 7;

    private Optional<Cookie> getCookieByAlias(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        log.info("Catch all cookies");
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(COOKIE_NAME)) {
                log.info("Cathced cookie with {}", cookie.getValue());
                return Optional.of(cookie);
            }
        }
        return Optional.empty();
    }

    public Cookie buildCookie(HttpServletRequest request) {
        Optional<Cookie> cookie = getCookieByAlias(request);
        if (cookie.isEmpty()) {
            String usernameCookie = addingAliasValueCookie();
            log.info("Null cooking. Creating with username {}", usernameCookie);
            Cookie cookieBuilder = new Cookie(COOKIE_NAME, usernameCookie);
            cookieBuilder.setMaxAge(COOKIE_AGE);
            cookieBuilder.setPath("/");
            System.out.println(cookieBuilder.getValue());
            return cookieBuilder;
        }
        return cookie.get();
    }


    private String addingAliasValueCookie() {
        List<String> aliasList = userService.getAllUsersAlias();
        int size = aliasList.size();

        log.info("User not found. Creating new cookie");
        String aliasUser = "user_" + size;
        userService.createNewUser(aliasUser);
        Cookie alias = new Cookie(COOKIE_NAME, aliasUser);

        return alias.getValue();
    }
}
