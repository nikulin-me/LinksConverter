package avito.converter.service.convert;

import avito.converter.domain.PrettyUrl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface ConverterService {
    URL createNewUrlFromOld(String alias,URL url) throws MalformedURLException;
}
