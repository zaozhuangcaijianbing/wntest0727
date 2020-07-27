package com.wn.wangzheng.chapter14;

import com.wn.wangzheng.chapter14.utils.SHAUtil;

public class AuthToken {
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 60 * 60 * 1000L;
    public static final String SPLIT = "&";
    private long createTime;
    private String token;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public static AuthToken generate(String baseUrl, String appId, String password, long timestamp) {
        String token = genToken(baseUrl, appId, password, timestamp);
        return new AuthToken(token, timestamp);
    }

    public static String genToken(String baseUrl, String appId, String password, long timestamp) {
        StringBuffer sb = new StringBuffer();
        sb.append(baseUrl);
        sb.append(SPLIT);
        sb.append(appId);
        sb.append(SPLIT);
        sb.append(password);
        sb.append(SPLIT);
        sb.append(timestamp);
        String token = SHAUtil.SHA(sb.toString());
        return token;
    }


    public boolean isExpired() {
        return System.currentTimeMillis() > (createTime + expiredTimeInterval);
    }

    public boolean match(AuthToken authToken) {
        if (this.token.equals(authToken.token)) {
            return true;
        }
        return false;
    }
}
