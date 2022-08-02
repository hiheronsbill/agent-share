package com.mk.agent.arthas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZarthasDemoApplication {

    /**
     * -javaagent:/Users/heronsbill/code/agent-share/hotdev-demo/target/hotdev-demo-1.0-SNAPSHOT-jar-with-dependencies.jar
     */
    public static void main(String[] args) {
        SpringApplication.run(ZarthasDemoApplication.class, args);
    }

}
