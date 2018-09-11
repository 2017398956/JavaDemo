package personal.nfl.java.demo.asm.util;

import org.objectweb.asm.*;
import personal.nfl.java.demo.asm.interfaces.InstructionBlock;

import java.io.PrintStream;

import static org.objectweb.asm.Opcodes.*;

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

    /**
     * 创建 if 代码块
     * @param mv
     * @param booleanInstructionBlock
     * @param instructionBlock
     */
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

    /**
     * 创建一个新的 field
     * @param cw
     * @param access
     * @param name
     * @param type
     * @param t
     * @param <T>
     */
    public static <T> void newField(ClassWriter cw, int access, String name, Class<T> type, T t) {
        newField(cw , access , name , Type.getDescriptor(type) , t);
    }

    /**
     * 创建一个新的 field
     * @param cw
     * @param access
     * @param name
     * @param descriptor
     * @param t
     * @param <T>
     */
    public static <T> void newField(ClassWriter cw, int access, String name, String descriptor , T t) {
        if(descriptor == null){
            throw new IllegalArgumentException("descriptor 不能为空") ;
        }
        if(t != null && !descriptor.equals(Type.getDescriptor(t.getClass()))){
            throw new IllegalArgumentException("descriptor 与值（t）的数据类型不匹配") ;
        }
        FieldVisitor fv = cw.visitField(access, name, descriptor ,null, t);
        fv.visitEnd();
    }

    public static void createConstructor(ClassWriter cw){
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), null, null);
        mv.visitCode();
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE) , false);
        mv.visitInsn(RETURN);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }


}
