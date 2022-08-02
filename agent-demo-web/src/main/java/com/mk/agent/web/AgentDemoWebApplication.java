package com.mk.agent.web;

import com.mk.agent.web.demo.TransClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentDemoWebApplication {


    /**
     * 一个线程每个500毫秒就调用一次TransClass的test方法
     * vm参数：（-javaagent:/Users/heronsbill/code/agent-share/agent-demo/target/agent-demo-1.0-SNAPSHOT.jar）
     *        （-javaagent:/Users/heronsbill/code/agent-share/agent-demo/target/agent-demo-1.0-SNAPSHOT-jar-with-dependencies.jar)
     * @param args
     */
    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                new TransClass().test();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        SpringApplication.run(AgentDemoWebApplication.class, args);
    }

}
