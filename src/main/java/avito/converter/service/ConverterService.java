package avito.converter.service;

import java.net.MalformedURLException;
import java.net.URL;

public interface ConverterService {
    URL createNewUrlFromOld(String alias, URL url) throws MalformedURLException;
}
