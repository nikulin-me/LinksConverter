package avito.converter.service;

import avito.converter.domain.User;

import java.net.MalformedURLException;
import java.net.URL;

public interface ConverterService {
    URL getPrettyUrl(String alias, URL url) throws MalformedURLException;
}
