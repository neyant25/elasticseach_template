package com.cun.utils;

import org.springframework.util.StopWatch;

public class ConcurrentStopWatch {

    private ThreadLocal<StopWatch> stopWatch;

    public ConcurrentStopWatch() {
        this.stopWatch = ThreadLocal.withInitial(StopWatch::new);
    }


    public ConcurrentStopWatch(final String name) {
        this.stopWatch = ThreadLocal.withInitial(() -> new StopWatch(name));
    }

    public void start(String taskName) {
        try {
            if (!this.stopWatch.get().isRunning()) {
                this.stopWatch.get().start(taskName);
            } else {
                this.stopWatch.get().stop();
            }
        } catch (Exception e) {
            System.err.println(String.format("开始监控任务%s失败,原因:%s", taskName, e.getCause()));
        }
    }

    public void stop() {
        try {
            if (this.stopWatch.get().isRunning()) {
                this.stopWatch.get().stop();
            }
        } catch (Exception e) {
            System.err.println(String.format("停止监控任务失败,原因:%s", e.getCause()));
        }
    }

    public String prettyPrint() {
        try {
            return this.stopWatch.get().prettyPrint();
        } catch (Exception e) {
            System.err.println(String.format("打印监控日志失败,原因:%s", e.getCause()));
        }
        return "";
    }
}
