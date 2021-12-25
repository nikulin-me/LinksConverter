package avito.converter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConverterServiceImpl implements ConverterService {
    private UrlSplitter splitter;


    @Override
    public String getUrlPretty(URL url) {
        return null;
    }

}
