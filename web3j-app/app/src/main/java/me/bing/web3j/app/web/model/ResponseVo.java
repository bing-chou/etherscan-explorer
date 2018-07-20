package me.bing.web3j.app.web.model;

/**
 * @author bing on 2018/6/29
 */
public class ResponseVo<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResponseVo(CodeEnum codeEnum) {
        this.code = codeEnum.code;
        this.msg = codeEnum.msg;
    }

    public ResponseVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVo() {
        this.code = 0;
        this.msg = "";
    }

    public static ResponseVo failed() {
        return new ResponseVo(CodeEnum.FAILED);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    enum CodeEnum {
        SUCCESS(0, "success"), FAILED(1, "failed");

        int code;
        String msg;

        CodeEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
