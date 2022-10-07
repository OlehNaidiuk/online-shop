package com.naidiuk.onlineshop.service.cache;

import com.naidiuk.onlineshop.dto.NbuExchangeDto;
import com.naidiuk.onlineshop.dto.NbuRateDto;
import com.naidiuk.onlineshop.error.NbuException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("nbuServiceCacheFromXml")
@EnableScheduling
public class NbuServiceCacheFromXml implements NbuServiceCache {
    private final String nbuRatesXmlUrl;
    private final RestTemplate restTemplate;
    private final Map<String, NbuRateDto> nbuRatesCache = new HashMap<>();

    public NbuServiceCacheFromXml(@Value("${nbu.rates.xml-url}") String nbuRatesXmlUrl, RestTemplate restTemplate) {
        this.nbuRatesXmlUrl = nbuRatesXmlUrl;
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
            ResponseEntity<NbuExchangeDto> nbuResponse = restTemplate.getForEntity(nbuRatesXmlUrl, NbuExchangeDto.class);
            if (nbuResponse.getBody() != null) {
                NbuExchangeDto nbuExchangeDto = nbuResponse.getBody();
                List<NbuRateDto> nbuRatesDto = nbuExchangeDto.getCurrencies();
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
