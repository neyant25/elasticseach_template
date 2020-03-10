package com.cun.utils;

import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: K_D_Lee
 * @Date: 2019/8/20 21:36
 * @Description: 序列化工具
 * @Version:
 */
public class ProtoBufUtil {

    private ProtoBufUtil() {
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            service.execute(Test.getInstance());
        }
    }

    static class Test implements Runnable {
        ConcurrentStopWatch  watch = new ConcurrentStopWatch ();

        @Override
        public void run() {
            try {
                watch.start("111111111111");
                TimeUnit.SECONDS.sleep(1);

                watch.stop();
                watch.start("222222222222");
                TimeUnit.SECONDS.sleep(1);

                watch.stop();
                watch.start("333333333333");
                TimeUnit.SECONDS.sleep(1);

                watch.stop();
                watch.start("444444444444");
                TimeUnit.SECONDS.sleep(1);

                watch.stop();
                String out = watch.prettyPrint();
                System.out.println(out);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private static class SingleHolder {
            private static Test test = new Test();
        }

        public static Test getInstance() {
            return SingleHolder.test;
        }
    }
}
