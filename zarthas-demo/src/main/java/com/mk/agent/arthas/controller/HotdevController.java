package com.mk.agent.arthas.controller;

import com.mk.agent.arthas.service.HelloService;
import com.mk.agent.arthas.service.SleepService;
import com.mk.agent.arthas.service.WorkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc TODO
 * @Author zhxy
 * @Version V1.0
 **/

@RestController
public class HotdevController {


    @RequestMapping("/hotdev")
    public void hotdev() {
        new HelloService().hello();
    }

}
