package com.cch.agentdemo.demo1;

/**
 * @author: chenghao.chen
 * @date: 2019/10/20 11:13
 * @description:
 * jvm额外参数
 * -javaagent:/Users/xmly/workspace/agent-demo/demo1/target/demo1.jar=com.cch.agentdemo.demo1.MyAgentTest&hello
 */
public class MyAgentTest {
    public static void main( String[] args ) throws InterruptedException {
        while (true) {
            hello();
            Thread.sleep(1000);
        }
    }

    public static void hello() {
        System.out.println("Hello World!");
    }
}
