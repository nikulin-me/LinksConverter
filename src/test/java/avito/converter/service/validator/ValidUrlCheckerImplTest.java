package avito.converter.service.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
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
    void shouldReturnTrueCauseExistingURL() throws IOException {
        //given
        URL url = new URL("https://github.com/temalead");

        //when
        boolean isValid = checker.validateURl(url);

        //then
        then(isValid).isEqualTo(true);

    }
    @Test
    void shouldReturnFalseCauseNonExistingURL() throws IOException {
        //given
        URL url = new URL("https://github.com/temalsafasdfsadfead");

        //when
        boolean isValid = checker.validateURl(url);

        //then
        then(isValid).isEqualTo(false);
    }
}