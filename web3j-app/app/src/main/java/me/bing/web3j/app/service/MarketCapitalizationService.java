package me.bing.web3j.app.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import me.bing.web3j.app.common.Constant;
import me.bing.web3j.app.service.http.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author bing on 2018/7/6
 */
@Component
public class MarketCapitalizationService {

    private static Logger log = LoggerFactory.getLogger(MarketCapitalizationService.class);

    private final HttpService httpService = new HttpService(Constant.CapAddress);

    @Cacheable("cap")
    public Capitalization get() throws IOException {
        Capitalization[] response = httpService.get(Capitalization[].class);
        if (null == response) {
            return null;
        }
        return response[0];
    }

    @Scheduled(fixedRate = 10*60*1000)
    @CacheEvict("cap")
    public void cacheEvict() {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Capitalization {

        private String id;
        private String name;
        private String symbol;
        private String rank;
        @JsonProperty("price_usd")
        private String priceUsd;
        @JsonProperty("price_btc")
        private String priceBtc;
        @JsonProperty("24h_volume_usd")
        private String volumeUsd24h;
        @JsonProperty("market_cap_usd")
        private String capUsd;
        @JsonProperty("percent_change_24h")
        private String percentChange24h;
        @JsonProperty("last_updated")
        private String lastUpdated;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getPriceUsd() {
            return priceUsd;
        }

        public void setPriceUsd(String priceUsd) {
            this.priceUsd = priceUsd;
        }

        public String getPriceBtc() {
            return priceBtc;
        }

        public void setPriceBtc(String priceBtc) {
            this.priceBtc = priceBtc;
        }

        public String getVolumeUsd24h() {
            return volumeUsd24h;
        }

        public void setVolumeUsd24h(String volumeUsd24h) {
            this.volumeUsd24h = volumeUsd24h;
        }

        public String getCapUsd() {
            return capUsd;
        }

        public void setCapUsd(String capUsd) {
            this.capUsd = capUsd;
        }

        public String getPercentChange24h() {
            return percentChange24h;
        }

        public void setPercentChange24h(String percentChange24h) {
            this.percentChange24h = percentChange24h;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }
    }
}
