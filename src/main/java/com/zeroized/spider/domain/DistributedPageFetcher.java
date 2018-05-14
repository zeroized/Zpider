package com.zeroized.spider.domain;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class DistributedPageFetcher extends PageFetcher implements Cloneable {
    private HttpClientBuilder clientBuilder;

    public DistributedPageFetcher(CrawlConfig config) {
        super(config);

        RequestConfig requestConfig = RequestConfig.custom()
                .setExpectContinueEnabled(false)
                .setCookieSpec(CookieSpecs.STANDARD)
                .setRedirectsEnabled(false)
                .setSocketTimeout(config.getSocketTimeout())
                .setConnectTimeout(config.getConnectionTimeout())
                .build();

        clientBuilder = HttpClientBuilder.create();
        clientBuilder.setDefaultRequestConfig(requestConfig);
        clientBuilder.setConnectionManager(connectionManager);
        clientBuilder.setUserAgent(config.getUserAgentString());
        clientBuilder.setDefaultHeaders(config.getDefaultHeaders());

    }

    public PageFetcher getNewPageFetcher(DistributedProxyItem proxyItem) {
        if (proxyItem != null && proxyItem.getHost() != null) {
            if (proxyItem.getPort() != null) {
                BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(
                        new AuthScope(proxyItem.getHost(), proxyItem.getPort()),
                        new UsernamePasswordCredentials(proxyItem.getUsername(),
                                proxyItem.getPassword()));
                clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }

            HttpHost proxy = new HttpHost(proxyItem.getHost(), proxyItem.getPort());
            clientBuilder.setProxy(proxy);
        }

        CloseableHttpClient httpClient = clientBuilder.build();
        try {
            DistributedPageFetcher newPageFetcher = (DistributedPageFetcher) this.clone();
            newPageFetcher.httpClient = httpClient;
            return newPageFetcher;
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

}
