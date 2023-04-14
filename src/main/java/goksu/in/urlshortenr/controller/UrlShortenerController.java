package goksu.in.urlshortenr.controller;

import goksu.in.urlshortenr.model.UrlMapping;
import goksu.in.urlshortenr.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/api/shorten")
    public ResponseEntity<UrlMapping> createShortUrl(@RequestBody UrlMapping urlMapping) {
        UrlMapping createdUrlMapping = urlShortenerService.createShortUrl(urlMapping);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUrlMapping);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getOriginalUrl(@PathVariable String shortUrl) {
        UrlMapping urlMapping = urlShortenerService.getOriginalUrl("http://localhost:8080/" + shortUrl);
        if (urlMapping != null) {
            return new RedirectView(urlMapping.getOriginalUrl());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found");
        }
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
