package com.cch.agentdemo.demo2;

/**
 * @author: chenghao.chen
 * @date: 2019/10/20 11:50
 * @description:
 */
import com.cch.agentdemo.core.MyTransformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

public class MyAgentmain {

    public static void agentmain(String agentOps, Instrumentation inst) throws UnmodifiableClassException {
        String[] args = agentOps.split("&");
        MyTransformer myTransformer =null;
        Class temp =null;
        for(Class clazz : inst.getAllLoadedClasses()) {
            if(clazz.getName().equals(args[0])) {
                // 绑定ClassFileTransformer
                myTransformer = new MyTransformer(args[0], args[1]);
                inst.addTransformer(myTransformer, true);
                // 对已加载类重新转换处理
                temp =clazz;
                inst.retransformClasses(clazz);

            }
        }
        inst.removeTransformer(myTransformer);
        try {

            Thread.sleep(5000);
            System.out.println("reset-----------");
            final ClassFileTransformer resetClassFileTransformer = new ClassFileTransformer() {
                @Override
                public byte[] transform(
                        ClassLoader loader,
                        String className,
                        Class<?> classBeingRedefined,
                        ProtectionDomain protectionDomain,
                        byte[] classfileBuffer) throws IllegalClassFormatException {
                    return null;
                }
            };

            inst.addTransformer(resetClassFileTransformer,true);
            inst.retransformClasses(temp);
            inst.removeTransformer(resetClassFileTransformer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void premain(String agentArgs, Instrumentation inst){

    }
}
