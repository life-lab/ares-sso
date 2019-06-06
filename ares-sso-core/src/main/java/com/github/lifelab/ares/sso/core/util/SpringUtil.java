package com.github.lifelab.ares.sso.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author liuyunran i01007600665
 * @description
 * @date 2018/12/4 16:47
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static SpringUtil springUtil = new SpringUtil();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(springUtil.applicationContext == null) {
            springUtil.applicationContext = applicationContext;
		}
	}

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return springUtil.applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}