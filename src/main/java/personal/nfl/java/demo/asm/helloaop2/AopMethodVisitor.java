package personal.nfl.java.demo.asm.helloaop2;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class AopMethodVisitor extends MethodVisitor implements Opcodes {

    private String voidMethodDescriptor;

    {
        voidMethodDescriptor = Type.getMethodDescriptor(Type.VOID_TYPE);
    }

    public AopMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, Type.getInternalName(AopClassAdapter.class), "before",
                voidMethodDescriptor, false);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode >= IRETURN && opcode <= RETURN) {
            // 在返回之前安插after 代码。
            this.visitMethodInsn(INVOKESTATIC, Type.getInternalName(AopClassAdapter.class), "after",
                    voidMethodDescriptor, false);
        }
        super.visitInsn(opcode);
    }

}
