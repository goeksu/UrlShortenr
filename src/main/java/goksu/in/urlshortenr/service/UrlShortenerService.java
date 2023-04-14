package goksu.in.urlshortenr.service;

import goksu.in.urlshortenr.model.UrlMapping;
import goksu.in.urlshortenr.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Value("${urlshortener.domain}")
    private String domain;

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public UrlMapping createShortUrl(UrlMapping urlMapping) {
        UrlMapping existingMapping = urlMappingRepository.findByOriginalUrl(urlMapping.getOriginalUrl());
        if (existingMapping != null) {
            return existingMapping;
        }

        String shortUrl = generateShortUrl();
        UrlMapping newMapping = new UrlMapping(urlMapping.getOriginalUrl(), shortUrl);
        urlMappingRepository.save(newMapping);

        return newMapping;
    }

    public UrlMapping getOriginalUrl(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl);
    }

    private String generateShortUrl() {
        String shortUrl;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                int index = ThreadLocalRandom.current().nextInt(BASE);
                sb.append(ALPHABET.charAt(index));
            }
            shortUrl = domain + "/" + sb.toString();
        } while (urlMappingRepository.findByShortUrl(shortUrl) != null);

        return shortUrl;
    }
}
