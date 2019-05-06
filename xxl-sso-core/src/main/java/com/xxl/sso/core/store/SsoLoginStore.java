package com.xxl.sso.core.store;

import com.xxl.sso.core.conf.Conf;
import com.xxl.sso.core.user.XxlSsoUser;
import com.xxl.sso.core.util.LocalCacheUtils;

/**
 * local login store
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
        Object objectValue = LocalCacheUtils.getValue(redisKey);
        if (objectValue != null) {
            XxlSsoUser xxlUser = (XxlSsoUser) objectValue;
            return xxlUser;
        }
        return null;
    }

    /**
     * remove
     *
     * @param storeKey
     */
    public static void remove(String storeKey) {
        String redisKey = redisKey(storeKey);
        LocalCacheUtils.remove(redisKey);
    }

    /**
     * put
     *
     * @param storeKey
     * @param xxlUser
     */
    public static void put(String storeKey, XxlSsoUser xxlUser) {
        String redisKey = redisKey(storeKey);
        LocalCacheUtils.put(redisKey, xxlUser);
    }

    private static String redisKey(String sessionId){
        return Conf.SSO_SESSIONID.concat("#").concat(sessionId);
    }

}
