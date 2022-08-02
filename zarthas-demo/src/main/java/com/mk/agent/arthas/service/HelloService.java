package com.mk.agent.arthas.service;

/**
 * @Desc TODO
 * @Author zhxy
 * @Version V1.0
 **/
public class HelloService {
    public void hello() {

        String println = System.currentTimeMillis() + " : "
                + new SleepService().sleep() + "--" + new WorkService().work();

        System.out.format("\33[36;1m %s%n", println);

    }
}
