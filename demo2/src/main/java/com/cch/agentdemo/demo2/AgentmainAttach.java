package com.cch.agentdemo.demo2;

/**
 * @author: chenghao.chen
 * @date: 2019/10/20 11:53
 * @description:
 */
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.util.Scanner;

public class AgentmainAttach {
    private final static String AGENT_JAR_PATH="/Users/xmly/workspace/agent-demo/demo2/target/demo2.jar";
    private final static String AGENT_JAR_PATH2="/Users/xmly/workspace/agent-demo/demo2/target/demo2-jar-with-dependencies.jar";
    private final static String AGENT_OPS ="com.cch.agentdemo.demo1.MyAgentTest&hello";
    public static void main(String[] args) throws IOException, AttachNotSupportedException,
            AgentLoadException, AgentInitializationException {
        System.out.println("print pid");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String pid = scanner.next();
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(AGENT_JAR_PATH2, AGENT_OPS);
            System.out.println("success");
        }

    }
}