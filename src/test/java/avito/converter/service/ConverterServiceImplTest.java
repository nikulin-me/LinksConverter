package avito.converter.service;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserService;
import avito.converter.service.convert.ConverterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ConverterServiceImplTest {
    @Mock
    private PrettyUrlRepository prettyUrlRepository;
    @Mock
    private UserService userService;

    private ConverterServiceImpl converterService;

    @BeforeEach
    void setUp(){
        converterService=new ConverterServiceImpl(prettyUrlRepository,userService);
    }

    @Test
    void shouldReturnExistingURL() throws MalformedURLException {
        //given
        User user = new User();
        user.setId(1L);
        user.setAlias("user_0");
        URL oldUrl = new URL("http://old");
        PrettyUrl expectedUrl = new PrettyUrl(1L, user, oldUrl, new URL("http://ssf"));
        user.getUrls().add(expectedUrl);
        given(userService.authenticateUser(user.getAlias())).willReturn(user);
        given(prettyUrlRepository.findByUserId(user.getId())).willReturn(Optional.of(user.getUrls()));

        //when
        converterService.createNewUrlFromOld(user.getAlias(),oldUrl);

        //then
        verify(userService,never()).updateUserData(user);
    }
    @Test
    void shouldReturnNewURL() throws MalformedURLException {
        //given
        User user = new User();
        user.setId(1L);
        user.setAlias("user_0");
        URL oldUrl = new URL("http://old.ru");
        given(userService.authenticateUser(user.getAlias())).willReturn(user);

        //when
        converterService.createNewUrlFromOld(user.getAlias(),oldUrl);


        //then
        then(user.getUrls().size()).isEqualTo(1);
    }

    @Test
    void shouldAddNewUrlToUserUrlList(){
        //given
        User user = new User();
        PrettyUrl prettyUrl = new PrettyUrl();
        PrettyUrl prettyUrl1 = new PrettyUrl();
        user.getUrls().add(prettyUrl);
        user.getUrls().add(prettyUrl1);
        user.setId(1L);
        user.setAlias("user_0");
        User expectedUser=new User();
        expectedUser.setUrls(List.of(new PrettyUrl(),new PrettyUrl(),new PrettyUrl()));
        when(userService.authenticateUser(any())).thenReturn(user);

        //when
        converterService.addUrlToUser(new PrettyUrl(), user.getAlias());

        //then
        then(user.getUrls().size()).isEqualTo(expectedUser.getUrls().size());
    }

}