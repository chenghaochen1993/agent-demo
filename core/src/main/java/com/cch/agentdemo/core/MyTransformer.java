package com.cch.agentdemo.core;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author: chenghao.chen
 * @date: 2019/10/20 11:08
 * @description:
 */
public class MyTransformer implements ClassFileTransformer {
    private String targetClassName;
    private String targetMethodName;

    public MyTransformer(String targetClassName, String targetMethodName) {
        this.targetClassName = targetClassName;
        this.targetMethodName = targetMethodName;
    }
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");
        if (className.equals(targetClassName)) {
            try {
                ClassReader cr = new ClassReader(className);
                ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
                ClassVisitor cv = new ClassVisitor(Opcodes.ASM7, cw) {
                    @Override
                    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
                        if (name.equals("hello")) {
                            return new MethodModify(methodVisitor);
                        }
                        return methodVisitor;
                    }
                };
                cr.accept(cv, 0);
                byte[] data = cw.toByteArray();
                return data;
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return null;
    }
}
