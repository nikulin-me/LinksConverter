package avito.converter.service;

import avito.converter.domain.PrettyUrl;
import avito.converter.domain.User;
import avito.converter.repository.PrettyUrlRepository;
import avito.converter.repository.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlSenderServiceImpl implements UrlSenderService {
    private final PrettyUrlRepository prettyUrlRepository;
    private final UserService userService;

    @Override
    public List<PrettyUrl> getAllUrls(String alias) {
        User user = userService.authenticateUser(alias);
        Optional<List<PrettyUrl>> urls = prettyUrlRepository.findByUserId(user.getId());
        return urls.orElse(null);

    }
}
