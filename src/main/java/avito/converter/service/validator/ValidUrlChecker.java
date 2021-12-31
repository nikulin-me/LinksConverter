package avito.converter.service.validator;

import java.io.IOException;
import java.net.URL;

public interface ValidUrlChecker {
    boolean validateURl(URL url) throws IOException;
}
