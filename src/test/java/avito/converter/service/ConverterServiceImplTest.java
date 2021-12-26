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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        service=new ConverterServiceImpl(prettyUrlRepository,userRepository,userService);
    }

    @Test
    void shouldReturnExistingUrl() throws MalformedURLException {
        //given
        User user = new User();
        user.setAlias("user_0");
        user.setId(1L);
        URL existingUrl = new URL("https://docs.oracle.com/javase/tutorial/networking/urls/creatingUrls.html");
        PrettyUrl expected = new PrettyUrl(1L, user, existingUrl, any());
        user.setUrls(List.of(expected));
        System.out.println(user.getUrls());
        given(userService.getUserByAlias("user_0")).willReturn(Optional.of(user));

        //when
        URL result = service.getPrettyUrl("user_0", existingUrl);

        //then
        then(result).isEqualTo(new URL("http://no.sky"+expected.getNewUrl()));
    }

    @Test
    void shouldReturnNewUrl(){
        //given
        User user = new User();
        given(userService.getUserByAlias(null)).willReturn(Optional.of(user));

        //when
    }
}