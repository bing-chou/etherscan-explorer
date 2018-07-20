package me.bing.web3j.app.mapper;

import java.util.List;
import me.bing.web3j.app.mapper.model.BlockTransactionCount;
import me.bing.web3j.app.mapper.model.DayCount;
import me.bing.web3j.app.mapper.model.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author bing on 2018/7/1
 */
@Mapper
public interface TransactionMapper {

    List<Transaction> listByBlock(@Param("blockNumber") long blockNumber);

    List<Transaction> listByBlocks(@Param("blockNumbers") List<Long> blockNumbers);

    Integer countInBlock(@Param("blockNumber") long blockNumber);

    List<BlockTransactionCount> countInBlocks(@Param("blocks") List<Long> blockNumbers);

    Transaction load(@Param("hash") String hash);

    Integer insert(@Param("transactions") List<Transaction> transactions);

    List<Transaction> listByAddress(@Param("address") String address);

    List<Transaction> top(@Param("offset") long offset, @Param("limit") long limit);

    Long count();

    Long countByBlockAndAddress(@Param("address") String address, @Param("block") Long block);

    List<Transaction> list(@Param("offset") long offset, @Param("limit") long limit,
        @Param("address") String address, @Param("block") Long block);

    List<DayCount> countByTimestamp(@Param("from") String from, @Param("to") String to);
}
