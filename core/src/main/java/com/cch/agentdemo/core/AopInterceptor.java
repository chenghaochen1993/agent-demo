package com.cch.agentdemo.core;

/**
 * @author: chenghao.chen
 * @date: 2019/11/4 13:38
 * @description:
 */
public class AopInterceptor {
    public static void beforeInvoke() {
        System.out.println("before");
    };
    public static void afterInvoke() {
        System.out.println("after");
    };
}
