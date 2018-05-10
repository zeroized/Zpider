package com.zeroized.spider;


import com.zeroized.spider.domain.DistributedCrawlerConfig;
import com.zeroized.spider.domain.DistributedProxyItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DistributedCrawlerConfigTest {

    @Test
    public void testValidLogic() {
        List<DistributedProxyItem> proxyItems = new ArrayList<>();
        for (int i = 1; i < 5; ++i) {
            proxyItems.add(new DistributedProxyItem("Host" + i, i, "user" + i, "pass" + i));
        }
        DistributedCrawlerConfig config = new DistributedCrawlerConfig(proxyItems);
        Assert.assertEquals(config.getProxyHost(), "Host1");
        Assert.assertEquals(config.getProxyPort(), 1);
        Assert.assertEquals(config.getProxyPassword(), "pass1");
        Assert.assertEquals(config.getProxyUsername(), "user1");
        Assert.assertEquals(config.getProxyPort(), 2);
        Assert.assertEquals(config.getProxyHost(), "Host3");
        Assert.assertEquals(config.getProxyHost(), "Host3");
        Assert.assertEquals(config.getProxyPort(), 3);

    }
}
