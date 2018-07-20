package me.bing.web3j.app.service.utils;

import me.bing.web3j.app.common.Constant;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.ipc.UnixIpcService;

/**
 * @author bing on 2018/6/29
 */
public enum IpcWeb3j {
    INSTANCE;

    Web3j value;

    IpcWeb3j() {
        this.value = Web3j.build(new UnixIpcService(Constant.IpcAddress));
    }

    public Web3j get() {
        return this.value;
    }
}
