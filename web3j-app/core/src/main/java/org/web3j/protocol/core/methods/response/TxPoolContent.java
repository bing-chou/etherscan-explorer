package org.web3j.protocol.core.methods.response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.web3j.protocol.core.Response;

/**
 * @author bing on 2018/6/28
 */
public class TxPoolContent extends Response<Map<String, Map<String, Map<String, Transaction>>>> {
    public Map<String, Map<String, Map<String, Transaction>>> getTxPoolContent() {
        return getResult();
    }

    public List<Transaction> getPendingTransactions() {
        return getResult().get("pending").values().stream().flatMap(m -> m.values().stream()).collect(Collectors.toList());
    }
}
