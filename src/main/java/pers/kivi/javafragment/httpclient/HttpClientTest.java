package pers.kivi.javafragment.httpclient;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author wangqiwei
 * @date 2021/07/03 23:00
 */
public class HttpClientTest {
    Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

    @Before
    public void startHttpServerOnPort8081() throws IOException, InterruptedException {
        startHttpServer(8081, "/hello");
    }

    @Before
    public void startHttpServerOnPort8082() throws IOException, InterruptedException {
        startHttpServer(8082, "/world");
    }

    private void startHttpServer(int port, String path) throws IOException, InterruptedException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.setExecutor(Executors.newFixedThreadPool(10));
        HttpHandler httpHandler = exchange -> {
            logger.info("Server开始处理请求");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {//ignore
            }
            byte[] body = "hello world".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "text/html;charset=UTF-8");
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.getResponseBody().flush();
            exchange.close();
        };

        httpServer.createContext(path, httpHandler);
        httpServer.start();
        logger.info("Http Server listening on port " + port);
    }


    @Test
    public void testHttpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                // 每个路由的最大的连接数
                .setMaxConnPerRoute(1)
                // 连接池的最大连接数
                .setMaxConnTotal(20)
                .build();
        List<String> urls = Arrays.asList("http://localhost:8081/hello", "http://localhost:8081/world");
        for (int i = 0; i < 2; i++) {
            int j = i;
            new Thread(() -> {
                doGet(httpClient, urls.get(j % 2));
            }).start();
        }

        new CountDownLatch(1).await();
    }

    private void doGet(CloseableHttpClient httpClient, String url) {
        logger.info("客户端开始请求URL: " + url);
        HttpGet get = new HttpGet(url);
        RequestConfig config = RequestConfig.custom()
                // 从HttpClient连接池等待连接超时
                .setConnectionRequestTimeout(10000)
                // TCP建立连接超时时间
                .setConnectTimeout(5000)
                // TCP开始传输数据时，接收到两个packet的最大间隔时间
                .setSocketTimeout(8000)
                .build();
        get.setConfig(config);
        try (CloseableHttpResponse result = httpClient.execute(get)) {
            String s = EntityUtils.toString(result.getEntity());
            logger.info("Http Response: " + s);
        } catch (Exception e) {
            logger.error("请求失败", e);
        }
    }
}
