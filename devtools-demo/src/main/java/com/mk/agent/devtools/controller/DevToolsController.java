package com.mk.agent.devtools.controller;

import com.mk.agent.third.ThirdUtilFirst;
import com.mk.agent.third.ThirdUtilSecond;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc TODO
 * @Author zhxy
 * @Version V1.0
 **/
@RestController
public class DevToolsController {

    @RequestMapping("/devtool")
    public String test(){
        // 1、为 variableA 设置
        ThirdUtilFirst.variableA = "hello devtools";

        // 2、获取 variableB 的值
        String variableA = ThirdUtilSecond.getVariableA();
        System.out.println(variableA);


        System.out.println("ThirdUtilFirst: " + ThirdUtilFirst.class.getClassLoader());
        System.out.println("ThirdUtilSecond: " + ThirdUtilSecond.class.getClassLoader());


        return variableA;
    }


}
