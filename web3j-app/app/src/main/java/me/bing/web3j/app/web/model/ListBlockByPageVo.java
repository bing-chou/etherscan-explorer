package me.bing.web3j.app.web.model;

import me.bing.web3j.app.service.utils.PageIterator;
import me.bing.web3j.app.web.model.BlockVo.Block;

/**
 * @author bing on 2018/7/6
 */
public class ListBlockByPageVo extends ResponseVo<PageIterator<BlockVo.Block>> {
    @Override
    public void setData(PageIterator<Block> data) {
        super.setData(data);
    }
}
