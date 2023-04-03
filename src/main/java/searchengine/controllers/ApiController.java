package searchengine.controllers;

import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.services.StatisticsService;

import java.io.ObjectInputFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final StatisticsService statisticsService;

    public ApiController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public boolean startIndexing(){
            SitesList siteList = new SitesList();
            siteList.getSites().forEach(site -> {
                getChildUrls(site.getUrl());
            });
            return true; 
    }

    private Set<String> getChildUrls(String pageUrl) {
        Set<String> urls = new HashSet<>();
        try {
            Document doc = Jsoup
                    .connect(pageUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36")
                    .ignoreHttpErrors(true)
                    .get();
            URL baseUrl = new URL(pageUrl);
            urls = doc.select("a").stream()
                    .map(e -> getChildUrl(baseUrl, e.attr("href")))
                    .filter(u -> u.startsWith(pageUrl))
                    .collect(Collectors.toSet());

        } catch (Exception ex) {
            //Maybe some logging
            System.out.printf("Ошибка парсинга страницы '%s': %s%n", pageUrl, ex.getMessage());
        }
        return urls;
    }

    private String getChildUrl(URL baseUrl, String href) {
        try {
            String childUrl = new URL(baseUrl, href).toString();
            int anchorIndex = childUrl.indexOf('#');
            if (anchorIndex > 0) {
                childUrl = childUrl.substring(0, anchorIndex);
            }
            return childUrl;
        } catch (MalformedURLException ex) {
            //Maybe some logging
            System.out.printf("Некорректная ссылка '%s': %s%n", href, ex.getMessage());
            return "";
        }
    }
}