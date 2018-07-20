package me.bing.web3j.app.web.model;

import java.util.List;

/**
 * @author bing on 2018/7/6
 */
public class ListBlockVo extends ResponseVo<List<BlockVo.Block>> {
    @Override
    public void setData(List<BlockVo.Block> data) {
        super.setData(data);
    }
}
