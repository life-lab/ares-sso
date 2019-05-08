package com.github.lifelab.ares.sso.server.config;

import com.github.lifelab.ares.sso.core.store.SsoLoginStore;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxueli 2018-04-03 20:41:07
 */
@Configuration
public class XxlSsoConfig implements InitializingBean, DisposableBean {

    @Value("${xxl.sso.redis.expire.minite}")
    private int redisExpireMinite;

    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinite(redisExpireMinite);
//        JedisUtil.init(redisAddress);
    }

    @Override
    public void destroy() throws Exception {
//        JedisUtil.close();
    }

}
