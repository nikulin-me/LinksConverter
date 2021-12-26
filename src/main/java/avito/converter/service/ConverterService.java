package avito.converter.service;

import avito.converter.domain.User;

import java.net.URL;

public interface ConverterService {
    String getPrettyUrl(String alias, URL url);
}
