package pers.kivi.javafragment.httpclient;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

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
    @Before
    public void startHttpServer() throws IOException, InterruptedException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8081), 0);
        httpServer.setExecutor(Executors.newFixedThreadPool(10));
        HttpHandler httpHandler = exchange -> {
            System.out.println("接收请求, Time: " + new Date());
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

        httpServer.createContext("/hello", httpHandler);
        httpServer.createContext("/world", httpHandler);
        httpServer.start();
        System.out.println("Http Server Listening port 8081");
    }

    @Test
    public void testHttpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
//                .setMaxConnPerRoute(2).setMaxConnTotal(3)
                .build();
        List<String> urls = Arrays.asList("http://localhost:8081/hello", "http://localhost:8081/world");
        for (int i = 0; i < 3; i++) {
            int j = i;
            new Thread(() -> {
                String url = urls.get(j % 2);
                System.out.println("开始执行：" + j + "; URL: " + url);
                HttpGet get = new HttpGet(url);
                try (CloseableHttpResponse result = httpClient.execute(get)) {
                    String s = EntityUtils.toString(result.getEntity());
                    System.out.println(s);
                } catch (Exception e) {  //ignore
                }
                System.out.println("结束执行：" + j);
            }).start();
        }

        new CountDownLatch(1).await();
    }
}
