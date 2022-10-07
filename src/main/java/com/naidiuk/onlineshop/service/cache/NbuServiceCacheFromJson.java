package com.naidiuk.onlineshop.service.cache;

import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.error.NbuException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component("nbuServiceCacheFromJson")
@EnableScheduling
public class NbuServiceCacheFromJson implements NbuServiceCache {
    private final String nbuRatesJsonUrl;
    private final RestTemplate restTemplate;
    private final Map<String, NbuRateDto> nbuRatesCache = new HashMap<>();

    public NbuServiceCacheFromJson(@Value("${nbu.rates.json-url}") String nbuRatesJsonUrl, RestTemplate restTemplate) {
        this.nbuRatesJsonUrl = nbuRatesJsonUrl;
        this.restTemplate = restTemplate;
        updateNbuRatesCache();
    }

    @Override
    public Map<String, NbuRateDto> getRates() {
        return nbuRatesCache;
    }

    @Scheduled(cron = "0 31 15 ? * MON-FRI")
    private void updateNbuRatesCache() {
        try {
            ResponseEntity<NbuRateDto[]> nbuResponse = restTemplate.getForEntity(nbuRatesJsonUrl, NbuRateDto[].class);
            if (nbuResponse.getBody() != null) {
                NbuRateDto[] nbuRatesDto = nbuResponse.getBody();
                nbuRatesCache.clear();
                for (NbuRateDto nbuRateDto : nbuRatesDto) {
                    nbuRatesCache.put(nbuRateDto.getCurrencyCode(), nbuRateDto);
                }
            }
        } catch (Exception e) {
            throw new NbuException("NBU error.", e);
        }
    }
}
