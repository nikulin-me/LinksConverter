package avito.converter.service;

import avito.converter.domain.PrettyUrl;

import java.util.List;

public interface UrlSenderService {
    List<PrettyUrl> getAllUrls(String alias);

}
