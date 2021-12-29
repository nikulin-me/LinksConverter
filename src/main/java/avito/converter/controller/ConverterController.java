package avito.converter.controller;

import avito.converter.domain.PrettyUrl;
import avito.converter.service.ConverterService;
import avito.converter.service.CookiesHandler;
import avito.converter.service.UrlSenderService;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

@RestController
@RequestMapping("/converter")
@RequiredArgsConstructor
public class ConverterController {
    private final CookiesHandler cookiesHandler;
    private final  ConverterService converterService;
    private final UrlSenderService urlSenderService;

    @GetMapping
    public ResponseEntity<URL> makePrettyUrl(HttpServletRequest request,
                                             @RequestParam("url") URL url,
                                             HttpServletResponse response) throws MalformedURLException {

        Cookie cookie = cookiesHandler.getAliasCookie(request);
        response.addCookie(cookie);
        URL prettyUrl = converterService.createNewUrlFromOld(cookie.getValue(), url);

        return ResponseEntity.ok()
                .body(prettyUrl);
    }

    @GetMapping("/urls")
    public ResponseEntity<List<PrettyUrl>> getAllUrlFromUser(HttpServletRequest request){
        Cookie cookie=cookiesHandler.getAliasCookie(request);
        List<PrettyUrl> urls = urlSenderService.getAllUrls(cookie.getValue());
        return ResponseEntity.ok()
                .body(urls);
    }
}
