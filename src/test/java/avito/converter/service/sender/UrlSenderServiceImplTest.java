package avito.converter.service.sender;

import avito.converter.domain.User;
import avito.converter.exception.InvalidURLException;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserService;
import avito.converter.service.convert.ConverterService;
import avito.converter.service.validator.ValidUrlCheckerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UrlSenderServiceImplTest {
    @Mock
    private PrettyUrlRepository prettyUrlRepository;
    @Mock
    private UserService userService;
    @Mock
    private ConverterService converterService;
    private final ValidUrlCheckerImpl validUrlChecker=new ValidUrlCheckerImpl();
    private UrlSenderServiceImpl sender;

    @BeforeEach
    void setUp(){
        sender=new UrlSenderServiceImpl(prettyUrlRepository,userService,converterService,validUrlChecker);
    }

    @Test
    void shouldReturnException() throws IOException {
        //given
        URL nonexistentUrl = new URL("https://github.com/temalsafasdfsadfead");
        User user = new User();
        user.setAlias("user_0");

        //then
        assertThrows(InvalidURLException.class,()->validUrlChecker.validateURl(nonexistentUrl));
    }

    @Test
    void shouldReturnURL() throws IOException {
        //given
        URL url = new URL("https://github.com/temalead");
        User user = new User();
        user.setAlias("user_0");
        //then
        assertDoesNotThrow(()->sender.createNewUrlFromOld(user.getAlias(),url));
    }
}