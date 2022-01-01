package avito.converter.repository;

import avito.converter.domain.PrettyUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PrettyUrlServiceTest {
    @Mock
    private PrettyUrlRepository repository;
    private PrettyUrlService service;
    private final String host="http://no.sky";


    @BeforeEach
    void setUp(){
        service=new PrettyUrlService(repository,host);
    }

    @Test
    void updateURL() throws MalformedURLException {
        //given
        URL url = new URL(host + "/" + "any");
        PrettyUrl prettyUrl = new PrettyUrl(1L,null,null,url);
        given(repository.findByNewUrl(any())).willReturn(prettyUrl);

        //when
        URL newUrl = new URL("https://github.com/temalead");
        URL result = service.updateURL(url, newUrl);
        System.out.println(result);

        //then
        then(result).isEqualTo(new URL(host+"/temalead"));


    }

    @Test
    void validateUrl() throws MalformedURLException {
        //given
        URL url = new URL("https://github.com/temalead");

        //when
        URL result = service.validateUrl(url);

        //then
        then(result).hasPath("/temalead").hasHost("no.sky");
    }

    @Test
    void getUrl() {
    }
}