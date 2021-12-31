package avito.converter.service.sender;

import avito.converter.domain.PrettyUrl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface UrlSenderService {
    List<PrettyUrl> getAllUrls(String alias);

    URL createNewUrlFromOld(String alias, URL oldUrl) throws IOException;

    URL getOldUrl(URL newUrl);
}
