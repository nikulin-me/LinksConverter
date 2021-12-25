package avito.converter.service;

import avito.converter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CookiesHandlerTest {
    private CookiesHandler handler;

    @MockBean
    private UserRepository repository;

    @Test
    void getUserAliasFromCookies() {
        //given
        List<String> listAlias=List.of("user_1","user_2");
        given(repository.findAllByAlias()).willReturn(listAlias);

        //when

        //then
    }
}