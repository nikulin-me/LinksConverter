package avito.converter.service.validator;

import avito.converter.exception.InvalidURLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class ValidUrlCheckerImplTest {

    private ValidUrlCheckerImpl checker;

    @BeforeEach
    void setUp(){
        checker=new ValidUrlCheckerImpl();
    }

    @Test
    void shouldReturnTrueCauseExistentURL() throws IOException {
        //given
        URL url = new URL("https://github.com/temalead");

        //when
        Serializable isValid = checker.validateURl(url);
        System.out.println(isValid);

        //then
        then(isValid).isEqualTo(true);

    }
    @Test
    void shouldReturnExceptionCauseNonexistentURL() throws IOException {
        //given
        URL url = new URL("https://github.com/temalsafasdfsadfead");

        //throws
        assertThrows(InvalidURLException.class,()->checker.validateURl(url));
    }
}