package me.bing.web3j.app.service.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.exceptions.ClientConnectionException;

/**
 * HTTP implementation of our services API.
 */
public class HttpService {

    enum METHOD {
        POST, GET
    }

    protected final ObjectMapper objectMapper = new ObjectMapper();

    public static final MediaType JSON_MEDIA_TYPE
        = MediaType.parse("application/json; charset=utf-8");

    private static final Logger log = LoggerFactory.getLogger(HttpService.class);

    private OkHttpClient httpClient;

    private String url;

    private HashMap<String, String> headers = new HashMap<>();

    public HttpService(String url, OkHttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;
    }

    public HttpService(String url) {
        this(url, createOkHttpClient());
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        configureLogging(builder);
        return builder.build();
    }

    private static void configureLogging(OkHttpClient.Builder builder) {
        if (log.isDebugEnabled()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
    }

    protected InputStream performGet() throws IOException {

        Headers headers = buildHeaders();

        okhttp3.Request httpRequest;

        httpRequest = new okhttp3.Request.Builder()
            .url(url)
            .headers(headers)
            .get()
            .build();

        okhttp3.Response response = httpClient.newCall(httpRequest).execute();
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (null != body) {
                return body.byteStream();
            }
            return null;
        } else {
            throw new ClientConnectionException(
                "Invalid response received: " + response.body());
        }
    }

    public <T> T get(Class<T> responseType) throws IOException {
        try (InputStream result = performGet()) {
            if (result != null) {
                return objectMapper.readValue(result, responseType);
            } else {
                return null;
            }
        }
    }

    private Headers buildHeaders() {
        return Headers.of(headers);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void addHeaders(Map<String, String> headersToAdd) {
        headers.putAll(headersToAdd);
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }
}
