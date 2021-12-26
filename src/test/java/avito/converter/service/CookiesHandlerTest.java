package avito.converter.service;

import avito.converter.repository.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseCookie;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CookiesHandlerTest {
    private CookiesHandler handler;

    @Mock
    private UserService service;

    @BeforeEach
    void setUp(){
        handler=new CookiesHandler(service);
    }

    @Test
    void shouldReturnNewCookie() {
        //given
        given(service.getAllUsersAlias()).willReturn(List.of());


        //when
        ResponseCookie cookie = handler.getCookieByAlias(null);

        //then
        ResponseCookie expectedCookie=ResponseCookie.from("alias","user_0").maxAge(60*60*24).build();
        then(cookie).isEqualTo(expectedCookie);
        verify(service).createNewUser("user_0");
    }

    @Test
    void shouldReturnOldCookie(){
        //given
        lenient().when(service.getAllUsersAlias()).thenReturn(List.of("user_1"));

        ResponseCookie cookie=handler.getCookieByAlias("user_1");
        ResponseCookie expectedCookie=ResponseCookie.from("alias","user_1").maxAge(60*60*24).build();
        then(cookie).isEqualTo(expectedCookie);
    }
}