package com.wn.wangzheng.chapter40;

import java.util.*;

public class EmailReporter extends ScheduleReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public EmailReporter(List<String> emailToAddresses) {
        this(new RedisMetricsStorage(), new Aggregator(), new EmailViewer(emailToAddresses));
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }

    public void startDailyReport() {
        // new Date()可以获取当前时间
        Date firstTime = trimTimeFieldsToZeroOfNextDay(new Date());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 省略其他代码...
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }

    protected Date trimTimeFieldsToZeroOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance(); // 这里可以获取当前时间
        calendar.setTime(date); // 重新设置时间
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
