package personal.nfl.java.demo.asm.account;

import com.sun.tools.classfile.Opcode;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import personal.nfl.java.demo.asm.util.SecurityChecker;

public class AddSecurityCheckMethodAdapter extends MethodVisitor {

    public AddSecurityCheckMethodAdapter(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }


    public void visitCode() {
        super.visitCode();
        visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(SecurityChecker.class),
                "checkSecurity", Type.getMethodDescriptor(Type.BOOLEAN_TYPE), false);

//        0 invokestatic #2 <personal/nfl/java/demo/asm/util/SecurityChecker/checkSecurity()Z>
//                3 ifeq 24 (+21)
//        System.out.println();
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


        Label irtn = new Label();
        visitJumpInsn(Opcodes.IFEQ, irtn);
//        visitMethodInsn(Opcode.GETSTATIC , Type.getInternalName());
//
//
//        visitJumpInsn(Opcodes.RETURN, rtn);
//        visitLabel(end);
//
//
//        visitLabel(rtn);

        visitEnd();
    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(label);
        System.out.println("label:" + label.info);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();

    }
}