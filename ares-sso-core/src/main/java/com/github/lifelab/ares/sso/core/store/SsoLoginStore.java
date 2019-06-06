package com.github.lifelab.ares.sso.core.store;

import com.github.lifelab.ares.sso.core.conf.Conf;
import com.github.lifelab.ares.sso.core.user.XxlSsoUser;
import com.github.lifelab.ares.sso.core.util.JedisUtil;
import com.github.lifelab.leisure.common.utils.LocalCacheUtils;

/**
 * 用一个线程轮询同步client和server端的用户信息，当clinet中的cookie失效，需要通知server端删除本地缓存中的用户信息
 * 用一个线程轮询同步的目的是防止某一端挂了，server或client能感知到
 *
 * @author xuxueli 2018-04-02 20:03:11
 */
public class SsoLoginStore {

    private static int redisExpireMinite = 1440;

    /**
     *  1440 minite, 24 hour
     * @param redisExpireMinite
     */
    public static void setRedisExpireMinite(int redisExpireMinite) {
        if (redisExpireMinite < 30) {
            redisExpireMinite = 30;
        }
        SsoLoginStore.redisExpireMinite = redisExpireMinite;
    }
    public static int getRedisExpireMinite() {
        return redisExpireMinite;
    }

    /**
     * get
     *
     * @param storeKey
     * @return
     */
    public static XxlSsoUser get(String storeKey) {

        String redisKey = redisKey(storeKey);
        Object objectValue = JedisUtil.getObjectValue(redisKey);
        if (objectValue != null) {
            XxlSsoUser xxlUser = (XxlSsoUser) objectValue;
            return xxlUser;
        }
        return null;
    }

    /**
     * 根据cookie的失效时间来删除缓存中的用户信息
     *
     * @param storeKey
     */
    public static void remove(String storeKey) {
        String redisKey = redisKey(storeKey);
        JedisUtil.del(redisKey);
    }

    /**
     * put
     *
     * @param storeKey
     * @param xxlUser
     */
    public static void put(String storeKey, XxlSsoUser xxlUser) {
        String redisKey = redisKey(storeKey);
        JedisUtil.setObjectValue(redisKey, xxlUser, redisExpireMinite * 60);  // minite to second
    }

    private static String redisKey(String sessionId){
        return Conf.SSO_SESSIONID.concat("#").concat(sessionId);
    }

}
