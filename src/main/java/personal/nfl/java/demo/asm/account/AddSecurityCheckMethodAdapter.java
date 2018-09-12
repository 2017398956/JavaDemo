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
        // 父变量的 mv 和 methodVisitor 是同一个值
    }


    public void visitCode() {
        mv.visitCode();
        CodeGenerator.println(mv , "开始访问方法");
//        super.visitCode();

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
        /**
         * {@linkplain Opcodes#IRETURN} , {@linkplain Opcodes#LRETURN} , {@linkplain Opcodes#FRETURN} ,
         * {@linkplain Opcodes#DRETURN} , {@linkplain Opcodes#ARETURN} , {@linkplain Opcodes#RETURN}
         * 都是表示结束方法访问
         */
        // 由于这个方法是 void 的所以只判断 {@linkplain Opcodes#RETURN} 就行了
        if (opcode == Opcodes.RETURN) {
            // 当匹配到 Opcodes.RETURN 时，说明方法访问即将结束，可以在方法结束前加入一些操作，
            // 但不能放在下面的 super.visitInsn 之后，否则不会执行
            visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(SecurityChecker.class),
                    "checkSecurity", Type.getMethodDescriptor(Type.BOOLEAN_TYPE), false);
            CodeGenerator.println(mv , "方法执行完毕。");
            CodeGenerator.println(mv , "++++++++++++++++++++++++++");
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
        // 当该方法访问完毕时，可以在这里做一些收尾的工作，但不能再往方法中添加代码了
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