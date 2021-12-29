package avito.converter.controller;

import avito.converter.domain.PrettyUrl;
import avito.converter.service.CookiesHandler;
import avito.converter.service.UrlSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/converter")
@RequiredArgsConstructor
public class ConverterController {
    private final CookiesHandler cookiesHandler;
    private final UrlSenderService urlSenderService;

    @GetMapping
    public ResponseEntity<URL> makePrettyUrl(HttpServletRequest request,
                                             @RequestParam("url") URL url,
                                             HttpServletResponse response) throws MalformedURLException {

        Cookie cookie = cookiesHandler.getAliasCookie(request);
        response.addCookie(cookie);
        URL prettyUrl = urlSenderService.createNewUrlFromOld(cookie.getValue(), url);

        return ResponseEntity.ok(prettyUrl);
    }

    @GetMapping("/urls")
    public ResponseEntity<List<PrettyUrl>> getAllUrlFromUser(HttpServletRequest request) {
        Cookie cookie = cookiesHandler.getAliasCookie(request);
        List<PrettyUrl> urls = urlSenderService.getAllUrls(cookie.getValue());
        System.out.println(ResponseEntity.ok(urls).getClass());
        return ResponseEntity.ok()
                .body(urls);
    }

    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectToUglyUrl(@RequestParam("url") URL newUrl) {
        URL oldUrl = urlSenderService.getOldUrl(newUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(String.valueOf(oldUrl))).build();
    }
}
