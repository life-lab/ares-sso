package com.xxl.sso.sample.config;

import com.xxl.sso.core.conf.Conf;
import com.xxl.sso.core.filter.XxlSsoTokenFilter;
import com.xxl.sso.core.util.CaffeineUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxueli 2018-11-15
 */
@Configuration
public class XxlSsoConfig implements DisposableBean {



    @Value("${xxl.sso.logout.path}")
    private String xxlSsoLogoutPath;

    @Value("${xxl-sso.excluded.paths}")
    private String xxlSsoExcludedPaths;


    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {

        // xxl-sso, filter init
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setName("XxlSsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new XxlSsoTokenFilter());
        registration.addInitParameter(Conf.SSO_LOGOUT_PATH, xxlSsoLogoutPath);
        registration.addInitParameter(Conf.SSO_EXCLUDED_PATHS, xxlSsoExcludedPaths);

        return registration;
    }

    @Override
    public void destroy() throws Exception {

        // xxl-sso, redis close
        CaffeineUtil.clear();
    }

}
