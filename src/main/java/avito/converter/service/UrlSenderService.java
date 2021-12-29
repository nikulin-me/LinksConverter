package avito.converter.service;

import avito.converter.domain.PrettyUrl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface UrlSenderService {
    List<PrettyUrl> getAllUrls(String alias);

    URL createNewUrlFromOld(String alias, URL oldUrl) throws MalformedURLException;

    URL getOldUrl(URL newUrl);
}
