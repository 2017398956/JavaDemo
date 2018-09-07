package personal.nfl.java.demo.asm.account;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import personal.nfl.java.demo.asm.interfacesimpl.BaseStatementBlock;
import personal.nfl.java.demo.asm.interfacesimpl.PrintlnStatement;
import personal.nfl.java.demo.asm.util.CodeGenerator;
import personal.nfl.java.demo.asm.util.SecurityChecker;

public class AddSecurityCheckMethodAdapter extends MethodVisitor {

    public AddSecurityCheckMethodAdapter(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }


    public void visitCode() {
        super.visitCode();
        System.out.println("visitCode()");


//        0 invokestatic #2 <personal/nfl/java/demo/asm/util/SecurityChecker/checkSecurity()Z>
//                3 ifeq 24 (+21)
//        6 getstatic #3 <java/lang/System/out Ljava/io/PrintStream;>
//        9 ldc #4 <Account.operation( )>
//                11 invokevirtual #5 <java/io/PrintStream/println(Ljava/lang/String;)V>
//                14 getstatic #3 <java/lang/System/out Ljava/io/PrintStream;>
//        17 ldc #6 <Account.operation2( )>
//                19 invokevirtual #5 <java/io/PrintStream/println(Ljava/lang/String;)V>
//                22 iconst_1
//        23 ireturn
//        24 iconst_2
//        25 ireturn


//        Label irtn = new Label();
//        visitJumpInsn(Opcodes.IFEQ, irtn);
//        CodeGenerator.println(mv , "Hello out 1");
//        visitLabel(irtn);
        CodeGenerator.iF(mv, isSafe , new PrintlnStatement("Hello out 1"));

//        CodeGenerator.println(mv, "Hello out 2");

//        CodeGenerator.iF(mv, isSafe ,  new PrintlnStatement("Hello out 3"));
    }

    @Override
    public void visitInsn(int opcode) {
        System.out.println("opcode: " + opcode);
        if (opcode == Opcodes.RETURN) {
            visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(SecurityChecker.class),
                    "checkSecurity", Type.getMethodDescriptor(Type.BOOLEAN_TYPE), false);
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitLabel(Label label) {
        System.out.println("label:" + label.info);
        super.visitLabel(label);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    private BaseStatementBlock isSafe = new BaseStatementBlock() {
        @Override
        public void invoke(MethodVisitor methodVisitor) {
            super.invoke(methodVisitor);
            visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(SecurityChecker.class),
                    "checkSecurity", Type.getMethodDescriptor(Type.BOOLEAN_TYPE), false);
        }
    };

}