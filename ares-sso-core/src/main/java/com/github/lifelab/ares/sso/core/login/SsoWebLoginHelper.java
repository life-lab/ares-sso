package com.github.lifelab.ares.sso.core.login;

import com.github.lifelab.ares.sso.core.store.SsoLoginStore;
import com.github.lifelab.ares.sso.core.store.SsoSessionIdHelper;
import com.github.lifelab.ares.sso.core.util.CookieUtil;
import com.github.lifelab.ares.sso.core.conf.Conf;
import com.github.lifelab.ares.sso.core.user.XxlSsoUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuxueli 2018-04-03
 */
public class SsoWebLoginHelper {

    /**
     * client login
     *
     * @param response
     * @param sessionId
     * @param ifRemember    true: cookie not expire, false: expire when browser close （server cookie）
     * @param xxlUser
     */
    public static void login(HttpServletResponse response,
                             String sessionId,
                             XxlSsoUser xxlUser,
                             boolean ifRemember) {

        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey Fail, sessionId:" + sessionId);
        }

        SsoLoginStore.put(storeKey, xxlUser);
        CookieUtil.set(response, Conf.SSO_SESSIONID, sessionId, ifRemember);
    }

    /**
     * client logout
     *
     * @param request
     * @param response
     */
    public static void logout(HttpServletRequest request,
                              HttpServletResponse response) {

        String cookieSessionId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);
        if (cookieSessionId==null) {
            return;
        }

        String storeKey = SsoSessionIdHelper.parseStoreKey(cookieSessionId);
        if (storeKey != null) {
            SsoLoginStore.remove(storeKey);
        }
        CookieUtil.remove(request, response, Conf.SSO_SESSIONID);
    }



    /**
     * login check
     *
     * @param request
     * @param response
     * @return
     */
    public static XxlSsoUser loginCheck(HttpServletRequest request, HttpServletResponse response){

        //获取每个请求对应的cookieId
        String cookieSessionId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);

        // 根据cookieId获取用户信息，获取到，返回user，获取不到？？？
        XxlSsoUser xxlUser = SsoTokenLoginHelper.loginCheck(cookieSessionId);
        if (xxlUser != null) {
            return xxlUser;
        }

        // redirect user

        // 删除原来的cookie
        SsoWebLoginHelper.removeSessionIdByCookie(request, response);

        // set new cookie
        String paramSessionId = request.getParameter(Conf.SSO_SESSIONID);
        xxlUser = SsoTokenLoginHelper.loginCheck(paramSessionId);
        if (xxlUser != null) {
            CookieUtil.set(response, Conf.SSO_SESSIONID, paramSessionId, false);    // expire when browser close （client cookie）
            return xxlUser;
        }

        return null;
    }


    /**
     * client logout, cookie only
     *
     * @param request
     * @param response
     */
    public static void removeSessionIdByCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.remove(request, response, Conf.SSO_SESSIONID);
    }

    /**
     * get sessionid by cookie
     *
     * @param request
     * @return
     */
    public static String getSessionIdByCookie(HttpServletRequest request) {
        String cookieSessionId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);
        return cookieSessionId;
    }


}
