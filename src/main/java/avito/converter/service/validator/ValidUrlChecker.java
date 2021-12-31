package avito.converter.service.validator;

import avito.converter.exception.InvalidURLException;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

public interface ValidUrlChecker {
    boolean validateURl(URL url) throws InvalidURLException, IOException;
}
