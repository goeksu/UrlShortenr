package goksu.in.urlshortenr.repository;

import goksu.in.urlshortenr.model.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlMappingRepository extends MongoRepository<UrlMapping, String> {
    UrlMapping findByShortUrl(String shortUrl);
    UrlMapping findByOriginalUrl(String originalUrl);
}