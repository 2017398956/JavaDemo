package personal.nfl.java.demo.asm.util;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import personal.nfl.java.demo.asm.interfaces.InstructionBlock;

import java.io.PrintStream;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

public class CodeGenerator {

    /**
     * 生成 System.out.println(str) ;
     *
     * @param mv
     * @param str
     */
    public static void println(MethodVisitor mv, String str) {
        if (null == mv) {
            return;
        }
        mv.visitFieldInsn(GETSTATIC, Type.getInternalName(System.class), "out",
                Type.getDescriptor(PrintStream.class));
        mv.visitLdcInsn(str);
        mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(PrintStream.class), "println",
                Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String.class)), false);
    }

    public static void iF(MethodVisitor mv, InstructionBlock booleanInstructionBlock, InstructionBlock instructionBlock) {
        if (null == mv || booleanInstructionBlock == null) {
            return;
        }
        // 这里应该有一个 Boolean 类型的语句
        booleanInstructionBlock.invoke(mv);
        Label iF = new Label();
        mv.visitJumpInsn(Opcodes.IFEQ, iF);
        if (null != instructionBlock) {
            instructionBlock.invoke(mv);
        }
        mv.visitLabel(iF);

    }
}
