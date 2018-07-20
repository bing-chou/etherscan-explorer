package me.bing.web3j.app.web.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bing on 2018/7/18
 */
public class MapVo extends ResponseVo<Map<String, Object>> {
    public MapVo() {}

    public static MapVo success() {
        MapVo result = new MapVo();
        result.setCode(CodeEnum.SUCCESS.getCode());
        result.setMsg(CodeEnum.SUCCESS.getMsg());
        result.setData(new HashMap<>());
        return result;
    }

    public static MapVo failed() {
        MapVo result = new MapVo();
        result.setCode(CodeEnum.FAILED.getCode());
        result.setMsg(CodeEnum.FAILED.getMsg());
        return result;
    }

    public MapVo put(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }
}
