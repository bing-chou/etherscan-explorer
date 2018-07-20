package me.bing.web3j.app.common;

import java.math.BigInteger;

/**
 * @author bing on 2018/7/5
 */
public class Constant {
    private Constant() {}

    public static String IpcAddress = "/Users/bing/test/data/geth.ipc";

    public static String CapAddress = "https://api.coinmarketcap.com/v1/ticker/ethereum/";

    public static BigInteger GWeiFactor = BigInteger.valueOf(10).pow(9);

    public static String LatestBlockNumberKey = "latest";

    public static long TimestampFactor = 1000;

    public static long EightHourToSecond = 8 * 60 * 60;

    public static BigInteger MineReward = GWeiFactor.multiply(GWeiFactor).multiply(BigInteger.valueOf(3));
}
