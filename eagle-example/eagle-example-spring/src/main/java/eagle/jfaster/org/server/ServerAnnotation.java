package eagle.jfaster.org.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fangyanpeng1 on 2017/8/9.
 */
public class ServerAnnotation {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("server_annotation.xml");
        appCtx.start();
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }
}
