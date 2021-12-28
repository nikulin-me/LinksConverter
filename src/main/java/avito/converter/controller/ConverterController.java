package avito.converter.controller;

import avito.converter.service.ConverterService;
import avito.converter.service.CookiesHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/converter")
@RequiredArgsConstructor
public class ConverterController {
    private final CookiesHandler cookiesHandler;
    private final  ConverterService converterService;

    @GetMapping
    public ResponseEntity<URL> makePrettyUrl(HttpServletRequest request,
                                             @RequestParam("url") URL url,
                                             HttpServletResponse response) throws MalformedURLException {

        Cookie cookie = cookiesHandler.buildCookie(request);
        response.addCookie(cookie);
        URL prettyUrl = converterService.createNewUrlFromOld(cookie.getValue(), url);

        return ResponseEntity.ok()
                .body(prettyUrl);
    }
}
