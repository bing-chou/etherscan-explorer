package me.bing.web3j.app.web.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bing on 2018/7/6
 */
public class CapitalizationVo extends ResponseVo<CapitalizationVo.Capitalization> {

    @Override
    public void setData(Capitalization data) {
        super.setData(data);
    }

    public static class Capitalization {

        private Long id;
        private BigDecimal marketCap;
        private BigDecimal volume24h;
        private BigDecimal change24h;
        private BigDecimal price;
        private String priceUnit;
        private Integer trends;
        private Date createdAt;

        private Capitalization(Builder builder) {
            setId(builder.id);
            setMarketCap(builder.marketCap);
            setVolume24h(builder.volume24h);
            setChange24h(builder.change24h);
            setPrice(builder.price);
            setPriceUnit(builder.priceUnit);
            setTrends(builder.trends);
            setCreatedAt(builder.createdAt);
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public BigDecimal getMarketCap() {
            return marketCap;
        }

        public void setMarketCap(BigDecimal marketCap) {
            this.marketCap = marketCap;
        }

        public BigDecimal getVolume24h() {
            return volume24h;
        }

        public void setVolume24h(BigDecimal volume24h) {
            this.volume24h = volume24h;
        }

        public BigDecimal getChange24h() {
            return change24h;
        }

        public void setChange24h(BigDecimal change24h) {
            this.change24h = change24h;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getPriceUnit() {
            return priceUnit;
        }

        public void setPriceUnit(String priceUnit) {
            this.priceUnit = priceUnit;
        }

        public Integer getTrends() {
            return trends;
        }

        public void setTrends(Integer trends) {
            this.trends = trends;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public static final class Builder {

            private Long id;
            private BigDecimal marketCap;
            private BigDecimal volume24h;
            private BigDecimal change24h;
            private BigDecimal price;
            private String priceUnit;
            private Integer trends;
            private Date createdAt;

            public Builder() {
            }

            public Builder id(Long val) {
                id = val;
                return this;
            }

            public Builder marketCap(BigDecimal val) {
                marketCap = val;
                return this;
            }

            public Builder volume24h(BigDecimal val) {
                volume24h = val;
                return this;
            }

            public Builder change24h(BigDecimal val) {
                change24h = val;
                return this;
            }

            public Builder price(BigDecimal val) {
                price = val;
                return this;
            }

            public Builder priceUnit(String val) {
                priceUnit = val;
                return this;
            }

            public Builder trends(Integer val) {
                trends = val;
                return this;
            }

            public Builder createdAt(Date val) {
                createdAt = val;
                return this;
            }

            public Capitalization build() {
                return new Capitalization(this);
            }
        }
    }

}
