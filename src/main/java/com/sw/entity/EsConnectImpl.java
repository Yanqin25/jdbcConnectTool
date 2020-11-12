package com.sw.entity;

import com.sw.utils.StringUtils;
import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;

import java.io.IOException;

@Data
public class EsConnectImpl implements Connect {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String sql;

    @Override
    public void connect() {
        RestHighLevelClient client = null;
        HttpHost httpHost = new HttpHost(host, port);
        if (StringUtils.isEmpty(username) ||StringUtils.isEmpty(password)) {
            client = new RestHighLevelClient(RestClient.builder(httpHost));
        } else {
            //用户密码认证
            CredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
            RestClientBuilder restClientBuilder = RestClient.builder(httpHost).setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(provider));
            client = new RestHighLevelClient(restClientBuilder);
        }
        try {
            Response response = client.getLowLevelClient().performRequest(new Request("GET", sql));
            System.out.println("URI(sql)查询结果是：");
            System.out.println("--------------------------------------------------");
            System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println("--------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
