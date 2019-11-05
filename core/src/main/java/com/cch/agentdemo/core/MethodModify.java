package com.cch.agentdemo.core;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: chenghao.chen
 * @date: 2019/11/4 12:23
 * @description:
 */
public class MethodModify extends MethodVisitor {
    public MethodModify(MethodVisitor mv) {
        super(Opcodes.ASM7,mv);
    }

    /**
     * 在代码开始处新增代码
     */
    @Override
    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(Opcodes.INVOKESTATIC, "com/cch/agentdemo/core/AopInterceptor", "beforeInvoke", "()V",false);
    }

    /**
     * 在代码 return前新增代码
     * @param opcode
     */
    @Override
    public void visitInsn(int opcode) {
        if(opcode == Opcodes.RETURN){
            this.visitMethodInsn(Opcodes.INVOKESTATIC, "com/cch/agentdemo/core/AopInterceptor", "afterInvoke", "()V",false);
        }
        super.visitInsn(opcode);
    }

}
