package com.wn.wangzheng.observer.demo2;

public class RegPromotionObserver implements RegObserver {
    private PromotionService promotionService; // 依赖注入

    @Override
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}
