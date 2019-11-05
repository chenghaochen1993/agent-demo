package com.cch.agentdemo.demo1;

import com.cch.agentdemo.core.MyTransformer;

import java.lang.instrument.Instrumentation;

/**
 * @author: chenghao.chen
 * @date: 2019/10/20 11:08
 * @description:
 */

public class MyPremain {
    public static void premain(String agentOps, Instrumentation inst) {
        String[] args = agentOps.split("&");
        // 绑定ClassFileTransformer
        inst.addTransformer(new MyTransformer(args[0], args[1]));
    }
}
