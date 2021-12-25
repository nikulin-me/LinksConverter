package avito.converter.service;

import avito.converter.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CookiesHandlerTest {
    private CookiesHandler handler;

    @Mock
    private UserRepository repository;

    @BeforeEach
    void setUp(){
        handler=new CookiesHandler(repository);
    }

    @Test
    void shouldReturnNewCookie() {
        //given
        given(repository.findAllByAlias()).willReturn(List.of());


        //when
        ResponseCookie cookie = handler.getCookieByAlias(null);

        //then
        ResponseCookie expectedCookie=ResponseCookie.from("alias","user_0").maxAge(60*60*24).build();
        then(cookie).isEqualTo(expectedCookie);
    }

    @Test
    void shouldReturnOldCookie(){
        //given
        lenient().when(repository.findAllByAlias()).thenReturn(List.of("user_1"));

        ResponseCookie cookie=handler.getCookieByAlias("user_1");
        ResponseCookie expectedCookie=ResponseCookie.from("alias","user_1").maxAge(60*60*24).build();
        then(cookie).isEqualTo(expectedCookie);
    }
}