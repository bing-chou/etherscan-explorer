package me.bing.web3j.app.mapper;

import java.util.List;
import me.bing.web3j.app.mapper.model.Block;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author bing on 2018/7/1
 */
@Mapper
public interface BlockMapper {

    Long latestNumber();

    Block load(@Param("number") long number);

    Block loadByHash(@Param("hash") String hash);

    Integer insert(@Param("block") Block block);

    List<Block> topByNumber(@Param("startNumber") long startNumber, @Param("endNumber") long endNumber);

    List<Block> list(@Param("offset") long offset, @Param("limit") long limit, @Param("miner") String miner);

    Long count(@Param("miner") String miner);
}
