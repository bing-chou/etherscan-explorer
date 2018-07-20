package me.bing.web3j.app.common;

import java.math.BigDecimal;

/**
 * @author bing on 2018/7/18
 */
public class NumberUtils {
    private NumberUtils(){}

    public static String fromGWei(long gwei) {
        return BigDecimal.valueOf(gwei)
            .divide(new BigDecimal(Constant.GWeiFactor), 8, BigDecimal.ROUND_DOWN)
            .stripTrailingZeros().toPlainString();
    }

    public static String percentage(String value, String total) {
        return new BigDecimal(value)
            .divide(new BigDecimal(total), 8, BigDecimal.ROUND_DOWN)
            .multiply(new BigDecimal(100))
            .stripTrailingZeros().toPlainString();
    }
}
