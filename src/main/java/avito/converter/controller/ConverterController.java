package avito.converter.controller;

import avito.converter.service.ConverterService;
import avito.converter.service.CookiesHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class ConverterController {
    private final CookiesHandler cookiesHandler;
    private final  ConverterService converterService;

    @GetMapping
    public ResponseEntity<String> getPrettyUrl(@RequestParam("url") URL url,
                                                  @CookieValue(name = "alias") String alias){
        ResponseCookie cookie=cookiesHandler.getCookieByAlias(alias);
        String prettyUrl = converterService.getPrettyUrl(alias,url);


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,cookie.toString())
                .body(prettyUrl);
    }
}
