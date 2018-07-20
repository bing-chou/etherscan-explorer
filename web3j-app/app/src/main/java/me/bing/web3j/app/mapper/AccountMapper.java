package me.bing.web3j.app.mapper;

import java.util.List;
import me.bing.web3j.app.mapper.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author bing on 2018/7/1
 */
@Mapper
public interface AccountMapper {

    Account load(@Param("hash") String hash);

    Long count();

    List<Account> topByBalance(@Param("offset") long offset, @Param("limit") long limit);

    Integer update(@Param("addresses") List<Account> addresses);

    Long sumBalance();
}
