package personal.nfl.java.demo.asm.account;

import org.objectweb.asm.*;
import personal.nfl.java.demo.asm.util.CodeGenerator;

import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

public class AddSecurityCheckClassAdapter extends ClassVisitor {

    private int accessProxy;
    private String nameProxy;
    private String descProxy;
    private String signatureProxy;
    private String[] exceptionsProxy;

    public AddSecurityCheckClassAdapter(int api, ClassVisitor classVisitor) {
        //Responsechain 的下一个 ClassVisitor，这里我们将传入 ClassWriter，
        // 负责改写后代码的输出
        super(api, classVisitor);
        // 父变量的 cv 和 classVisitor 是同一个值
    }

    // 重写 visitMethod，访问到 "operation" 方法时，
    // 给出自定义 MethodVisitor，实际改写方法内容
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        /**
         * 如果调用 cv.visitMethod 后却不返回，则原方法中的内容会被清空；如果根据 name 判断后直接返回 null ，则会把方法删除
         */
        if ("willBeDeletedMethod".equals(name)) {
            return null;
        }
        if ("willBeClearedMethod".equals(name)) {
            cv.visitMethod(access, name, desc, signature, exceptions);
            return null;
        }
        if ("shouldBeProxyedMethod".equals(name)) {
            accessProxy = access;
            nameProxy = name;
            descProxy = desc;
            signatureProxy = signature;
            exceptionsProxy = exceptions;
            // 将需要被代理的方法按照一定的规则重命名
            MethodVisitor originMethodVisitor = cv.visitMethod(access, name + "_Proxy", desc, signature, exceptions);
            // 生成被代理方法的同名方法
            MethodVisitor methodVisitor = ((ClassWriter) cv).visitMethod(accessProxy, nameProxy, descProxy, signatureProxy, exceptionsProxy);
            methodVisitor.visitCode();
            CodeGenerator.println(methodVisitor, "0000000000000000000");
            // TODO 根据一定的规则调用原方法，且保持返回值一致；下面的用法有误
//            methodVisitor.visitVarInsn(ILOAD, 1);
//            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, Type.getDescriptor(Account.class), name + "_Proxy", descProxy, false);
//            methodVisitor.visitInsn(Opcodes.IRETURN);
            methodVisitor.visitEnd();
            return originMethodVisitor;
        }

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (mv != null) {
            // 对于 "operation" 方法
            if (name.equals("operation")) {
                // 使用自定义 MethodVisitor，实际改写方法内容
//                mv = cv.visitMethod(access, name + "_Temp", desc, signature, exceptions);

                mv = new AddSecurityCheckMethodAdapter(ASM6, mv);
//
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {

        // 这里是最后添加代码的时机
//        MethodVisitor methodVisitor = ((ClassWriter) cv).visitMethod(accessProxy , nameProxy , descProxy , signatureProxy , exceptionsProxy) ;
//        methodVisitor.visitCode();
//        CodeGenerator.println(methodVisitor , "===================");
//        methodVisitor.visitEnd();
        System.out.println("类访问完毕");
        super.visitEnd();
    }
}