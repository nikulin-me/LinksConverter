package avito.converter.controller;

import avito.converter.domain.PrettyUrl;
import avito.converter.repository.PrettyUrlService;
import avito.converter.repository.UserService;
import avito.converter.service.CookiesHandler;
import avito.converter.service.sender.UrlSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/converter")
@RequiredArgsConstructor
public class ConverterController {
    private final CookiesHandler cookiesHandler;
    private final UrlSenderService urlSenderService;
    private final PrettyUrlService prettyUrlService;

    @GetMapping
    public ResponseEntity<URL> makePrettyUrl(HttpServletRequest request,
                                             @RequestParam("url") URL url,
                                             HttpServletResponse response) throws IOException {

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

    @PutMapping("/{old}")
    public ResponseEntity<URL> updateURL(@PathVariable("old") Long oldURL,
                                        @RequestParam("url") URL url){
        URL updatableUrl = prettyUrlService.getUrl(oldURL);
        URL updatedUrl = prettyUrlService.updateURL(updatableUrl, url);
        return ResponseEntity.ok(updatedUrl);
    }
}
