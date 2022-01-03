package avito.converter.service;

import avito.converter.domain.User;
import avito.converter.repository.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CookiesHandlerTest {

    @Mock
    private UserService userService;
    private CookiesHandler cookiesHandler;
    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        cookiesHandler = new CookiesHandler(userService);
    }

    @Test
    void shouldReturnNewCookie() {
        //given
        User user = new User();
        user.setAlias("user_0");
        given(userService.authenticateUser(any())).willReturn(user);
        Cookie[] cookies=new Cookie[0];
        given(request.getCookies()).willReturn(cookies);

        //when
        Cookie result = cookiesHandler.getAliasCookie(request);

        //then
        then(result.getName()).isEqualTo("alias");
    }
}