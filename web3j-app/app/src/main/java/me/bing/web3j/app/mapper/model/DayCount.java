package me.bing.web3j.app.mapper.model;

/**
 * @author bing on 2018/7/18
 */
public class DayCount {
    private String day;
    private Long count;

    public DayCount(){}

    public DayCount(String day, Long count) {
        this.day = day;
        this.count = count;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
