package avito.converter.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookiesHandler {
    public Cookie getUserAliasFromCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("alias"))
                return cookie;
        }
        return addingAliasCookie();
    }

    private Cookie addingAliasCookie() {
        return null;
    }

}
