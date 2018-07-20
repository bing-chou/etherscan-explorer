package me.bing.web3j.app.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import me.bing.web3j.app.common.Constant;
import me.bing.web3j.app.common.DateUtils;
import me.bing.web3j.app.common.NumberUtils;
import me.bing.web3j.app.mapper.model.Account;
import me.bing.web3j.app.mapper.model.Block;
import me.bing.web3j.app.mapper.model.BlockTransactionCount;
import me.bing.web3j.app.mapper.model.DayCount;
import me.bing.web3j.app.mapper.model.Transaction;
import me.bing.web3j.app.mapper.model.TransactionStatusEnum;
import me.bing.web3j.app.service.EthService;
import me.bing.web3j.app.service.MarketCapitalizationService;
import me.bing.web3j.app.service.utils.PageIterator;
import me.bing.web3j.app.web.model.AccountExtensionVo;
import me.bing.web3j.app.web.model.AccountVo;
import me.bing.web3j.app.web.model.BlockVo;
import me.bing.web3j.app.web.model.CapitalizationVo;
import me.bing.web3j.app.web.model.ListAccountByPageVo;
import me.bing.web3j.app.web.model.ListBlockByPageVo;
import me.bing.web3j.app.web.model.ListBlockVo;
import me.bing.web3j.app.web.model.ListTransactionByPageVo;
import me.bing.web3j.app.web.model.ListTransactionVo;
import me.bing.web3j.app.web.model.MapVo;
import me.bing.web3j.app.web.model.ResponseVo;
import me.bing.web3j.app.web.model.TransactionVo;
import me.bing.web3j.app.web.utils.BlockUtils;
import me.bing.web3j.app.web.utils.ConverterFunctionUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class EthController {

    private static final Integer PAGE_SIZE = 25;

    private MarketCapitalizationService capitalizationService;
    private EthService ethService;

    public EthController(MarketCapitalizationService capitalizationService, EthService ethService) {
        this.capitalizationService = capitalizationService;
        this.ethService = ethService;
    }

    @RequestMapping("/")
    public String index() {
        return "Yet another implementation of etherscan";
    }

    @RequestMapping(value = "/market_cap", method = RequestMethod.GET)
    public CapitalizationVo marketCap() throws Exception {
        CapitalizationVo vo = new CapitalizationVo();
        vo.setData(ConverterFunctionUtil.toVoCapitalization.apply(capitalizationService.get()));
        return vo;
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET, params = "type=latest")
    public ListBlockVo latestBlocks() {
        ListBlockVo vo = new ListBlockVo();
        List<BlockVo.Block> blockList = ConverterFunctionUtil.toVoBlocks.apply(ethService.latestBlocks(1, 10));
        List<Long> blockNumbers = BlockUtils.blockNumbers(blockList);
        List<BlockTransactionCount> blockTransactionCounts = ethService.countTransactionInBlocks(blockNumbers);
        vo.setData(BlockUtils.blocksWithTxCnt(blockList, blockTransactionCounts));
        return vo;
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET)
    public ListBlockByPageVo blocks(@RequestParam(value = "m", required = false) String miner,
        @RequestParam(value = "p", required = false, defaultValue = "1") int page) {
        PageIterator<Block> blockPageIterator = ethService.listBlocks(page, PAGE_SIZE, miner);

        PageIterator<BlockVo.Block> voPageIterator = PageIterator.create(blockPageIterator);
        voPageIterator.getData().addAll(ConverterFunctionUtil.toVoBlocks.apply(blockPageIterator.getData()));

        List<Long> blockNumbers = BlockUtils.blockNumbers(voPageIterator.getData());
        List<Transaction> transactions = ethService.listTransactionInBlocks(blockNumbers);
        BlockUtils.blocksWithTx(voPageIterator.getData(), transactions);

        ListBlockByPageVo vo = new ListBlockByPageVo();
        vo.setData(voPageIterator);

        return vo;
    }

    @RequestMapping(value = "/block/{key}", method = RequestMethod.GET)
    public BlockVo block(@PathVariable("key") String key) {
        Block block = ethService.loadBlock(key);
        BlockVo vo = new BlockVo();
        if (null != block) {
            BlockVo.Block voBlock = ConverterFunctionUtil.toVoBlock.apply(block);
            voBlock.setMaxHeight(ethService.blockNumber());

            List<Transaction> transactions = ethService.listTransactionInBlock(voBlock.getHeight());

            vo.setData(BlockUtils.blockWithTx(voBlock, transactions));
        }

        return vo;
    }

    @RequestMapping(value = "/tx", method = RequestMethod.GET, params = "type=latest")
    public ListTransactionVo latestTransactions() {
        List<Transaction> transactions = ethService.latestTransactions(1, 10);
        ListTransactionVo vo = new ListTransactionVo();
        vo.setData(ConverterFunctionUtil.toVoTransactions.apply(transactions));
        return vo;
    }

    @RequestMapping(value = "/tx", method = RequestMethod.GET)
    public ListTransactionByPageVo transactions(@RequestParam(value = "block", required = false) Long block,
        @RequestParam(value = "a", required = false) String address,
        @RequestParam(value = "p", required = false, defaultValue = "1") int page,
        @RequestParam(value = "isPending", required = false, defaultValue = "false") Boolean isPending)
        throws IOException {

        String type;
        if (null != block) {
            type = "block";
        } else if (StringUtils.isNoneEmpty(address)) {
            type = "address";
        } else {
            type = "total";
        }

        long count = 0;
        List<Transaction> txs;
        if (isPending) {
            PageIterator<Transaction> pageIterator = ethService.pendingTransactions(page, PAGE_SIZE, address);
            count = pageIterator.getTotalCount();
            txs = pageIterator.getData();
        } else {
            count = ethService.countTransaction(address, block);
            txs = ethService.transactions(page, PAGE_SIZE, address, block);
        }

        ListTransactionByPageVo vo = new ListTransactionByPageVo();
        vo.setData(new ListTransactionByPageVo.TransactionByPage.Builder()
            .txnList(ConverterFunctionUtil.toVoTransactions.apply(txs))
            .txnCnt(count)
            .currentPage(page)
            .totalPage((int) (count / PAGE_SIZE + 1))
            .maxDisplayCnt(count)
            .type(type)
            .build());
        return vo;
    }

    @RequestMapping("/tx/{hash}")
    public TransactionVo transaction(@PathVariable("hash") String hash) throws IOException {
        Transaction tx = ethService.loadTransaction(hash);
        if (null == tx) {
            tx = ethService.loadPendingTransaction(hash);
            if (null == tx) {
                return (TransactionVo) ResponseVo.failed();
            }
            tx.setStatus(TransactionStatusEnum.PENDING.getValue());
        }

        TransactionVo vo = new TransactionVo();
        vo.setData(ConverterFunctionUtil.toVoTransaction.apply(tx));

        return vo;
    }

    @RequestMapping("/tx/cnt_static")
    public ResponseVo<Map<String, Long>> transactionStatic() {
        Date from = LocalDate.now().plusDays(-15).toDate();
        Date to = LocalDate.now().toDate();
        List<DayCount> dayCounts = ethService.countTransactionByTimestamp(from, to);
        Map<String, Long> dayCountMap = dayCounts.stream()
            .collect(Collectors.toMap(DayCount::getDay, DayCount::getCount));

        LocalDate fromLocalDate = LocalDate.fromDateFields(from);
        LocalDate toLocalDate = LocalDate.fromDateFields(to);
        while (fromLocalDate.isBefore(toLocalDate)) {
            dayCountMap.putIfAbsent(DateUtils.toDay(fromLocalDate.toDate()), 0L);
            fromLocalDate = fromLocalDate.plusDays(1);
        }

        ResponseVo<Map<String, Long>> result = new ResponseVo<>();
        result.setData(new TreeMap<>(dayCountMap));

        return result;
    }

    @RequestMapping("/account")
    public ListAccountByPageVo accounts(@RequestParam(value = "p", required = false, defaultValue = "1") int page) {
        List<Account> accounts = ethService.listAccount(page, PAGE_SIZE);
        long count = ethService.countAccount();
        long totalBalance = ethService.totalBalance();

        List<AccountVo.Account> accountList = ConverterFunctionUtil.toVoAccounts.apply(accounts);
        int i = (page - 1) * PAGE_SIZE;
        for (AccountVo.Account acc : accountList) {
            acc.setRank(++ i);
            acc.setPercentage(NumberUtils.percentage(acc.getBalance(), Constant.GWeiFactor.multiply(BigInteger.valueOf(totalBalance)).toString()));
        }

        ListAccountByPageVo result = new ListAccountByPageVo();
        ListAccountByPageVo.AccountByPage accountByPage = new ListAccountByPageVo.AccountByPage.Builder()
            .addressList(accountList)
            .totalAccountsCnt(count)
            .totalBalance(NumberUtils.fromGWei(totalBalance))
            .page(page)
            .totalPage((int) (count / PAGE_SIZE + 1))
            .build();
        result.setData(accountByPage);

        return result;
    }

    @RequestMapping("/address/{hash}")
    public AccountExtensionVo account(@PathVariable("hash") String hash) throws IOException {
        Account acc = ethService.loadAccount(hash);
        if (null == acc) {
            return (AccountExtensionVo) ResponseVo.failed();
        }

        PageIterator<Transaction> pendingTxs = ethService.pendingTransactions(1, PAGE_SIZE, hash);
        List<Transaction> txs = ethService.transactions(1, PAGE_SIZE, hash, null);

        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(pendingTxs.getData());
        transactions.addAll(txs);

        List<TransactionVo.Transaction> transactionList = ConverterFunctionUtil.toVoTransactions.apply(transactions);
        AccountExtensionVo result = new AccountExtensionVo();
        result.setData(new AccountExtensionVo.AccountExtension.Builder()
            .address(ConverterFunctionUtil.toVoAccount.apply(acc))
            .pendingTxCnt(pendingTxs.getTotalCount())
            .txCnt(ethService.countTransaction(hash, null))
            .txList(transactionList)
            .mintedBlkCnt(ethService.countBlockByMiner(hash))
            .build());

        return result;
    }

    @RequestMapping("/search")
    public MapVo search(@RequestParam(value = "q") String q) {
        if(StringUtils.isEmpty(q)) {
            return MapVo.failed().put("type", "unknown").put("q", "");
        }

        if (StringUtils.isNumeric(q)) {
            Block block = ethService.loadBlock(q);
            if (null != block) {
                return MapVo.success().put("type", "block").put("q", block.getNumber());
            }
            return MapVo.success().put("type", "unknown").put("q", q);
        }
        if (q.length() < 64) {
            Account account = ethService.loadAccount(q);
            if (null != account) {
                return MapVo.success().put("type", "address").put("q", account.getHash());
            }
        } else {
            Block block = ethService.loadBlock(q);
            if (null != block) {
                return MapVo.success().put("type", "block").put("q", block.getNumber());
            }
            Transaction transaction = ethService.loadTransaction(q);
            if (null != transaction) {
                return MapVo.success().put("type", "tx").put("q", transaction.getHash());
            }
        }

        return MapVo.success().put("type", "unknown").put("q", q);
    }
}
