package avito.converter.service;

import avito.converter.domain.User;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConverterServiceImplTest {
    @Mock
    private User user;

    @Mock
    private PrettyUrlRepository prettyUrlRepository;
    @Mock
    private UserRepository userRepository;

    private ConverterServiceImpl service;



    @BeforeEach
    void setUp(){
        service=new ConverterServiceImpl(prettyUrlRepository,userRepository);
    }

    @Test
    void getUrlPretty() {
        //given
    }
}