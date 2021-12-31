package avito.converter.service.validator;

import avito.converter.exception.InvalidURLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidUrlCheckerImpl implements ValidUrlChecker {


    @Override
    public boolean validateURl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        if (urlConnection.getResponseCode() == 404) {
            log.error("{} doesn`t exist", url);
            throw  new InvalidURLException();
        } else {
            return true;
        }
    }
}
