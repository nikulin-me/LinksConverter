package avito.converter.service;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserRepository;
import avito.converter.repository.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConverterServiceImplTest {
    @Mock
    private User user;

    @Mock
    private PrettyUrlRepository prettyUrlRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;

    private ConverterServiceImpl service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldReturnExistingUrl() {
        //given
        User user = new User();
        user.setAlias("tema");
        given(userService.getUserByAlias(any())).willReturn(any());
        //when

        //then
    }

    @Test
    void shouldReturnNewUrl(){

    }
}