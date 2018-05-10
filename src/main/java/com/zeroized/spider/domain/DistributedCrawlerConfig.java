package com.zeroized.spider.domain;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;

import java.util.List;

/**
 * 分布式爬虫配置项
 * MARK:可以考虑用一个锁，不过用处不大，大部分情况下Host和Port的获取时间很近很近，基本上不会造成分布式的问题
 */
public class DistributedCrawlerConfig extends CrawlConfig {

    private List<DistributedProxyItem> proxyItems;
    private DistributedProxyItem currentProxyItem = null;
    private int currentProxyIdx = 0;
    private boolean isCurrentProxyIdxValid = true;


    public DistributedCrawlerConfig(List<DistributedProxyItem> proxyItems) {
        super();
        this.proxyItems = proxyItems;
    }

    public List<DistributedProxyItem> getProxyItems() {
        return proxyItems;
    }

    public void setProxyItems(List<DistributedProxyItem> proxyItems) {
        this.proxyItems = proxyItems;
    }

    public DistributedProxyItem getCurrentProxyItem() {
        return currentProxyItem;
    }

    @Override
    public String getProxyHost() {
        this.checkItemValid();
        return currentProxyItem.getHost();
    }

    @Override
    public int getProxyPort() {
        this.checkItemValid();
        this.isCurrentProxyIdxValid = false; //代理端口只允许获取一次,也就是说，对于一个代理来说，getHost必须先getPort运行
        return currentProxyItem.getPort();
    }

    @Override
    public String getProxyUsername() {
        return currentProxyItem.getUsername();//这儿如果报错了，请先获取HOST
    }

    @Override
    public String getProxyPassword() {
        isCurrentProxyIdxValid = true;//这儿.....如果代理有密码的话，Host和Port会获取两次，重置一下
        return currentProxyItem.getPassword();//这儿如果报错了，请先获取HOST
    }

    /**
     * 检查当前项是否合法
     * 这儿的分布式调度策略采用FIFO，如果要更高级的，请自行开发
     * 经查源代码有以下规律：
     * 1：getProxyHost总是第一个调用，但是不一定只调用一次
     * 2：getProxyPort方法总是会调用，调用一次是一个轮回，当前项失效
     * 3：username和password不用管
     */
    protected void checkItemValid() {
        int totalItemCount = proxyItems.size();
        if (totalItemCount == 0) throw new RuntimeException("代理服务器长度为0");
        if (currentProxyItem == null) {
            currentProxyItem = proxyItems.get(currentProxyIdx);//这儿应该可以直接设置为0
        } else if (!isCurrentProxyIdxValid) {
            int nextIdx = (currentProxyIdx + 1) % totalItemCount;
            currentProxyItem = proxyItems.get(nextIdx);
            currentProxyIdx = nextIdx;
        }
        isCurrentProxyIdxValid = true;
    }

    //region 禁用的方法组
    @Override
    @Deprecated
    public void setProxyPort(int proxyPort) {
        this.throwException();
    }

    @Override
    @Deprecated
    public void setProxyHost(String proxyHost) {
        this.throwException();
    }

    @Override
    @Deprecated
    public void setProxyPassword(String proxyPassword) {
        this.throwException();
    }

    @Override
    @Deprecated
    public void setProxyUsername(String proxyUsername) {
        this.throwException();
    }

    private void throwException() {
        throw new RuntimeException("分布式爬虫禁用");
    }
    //endregion
}
