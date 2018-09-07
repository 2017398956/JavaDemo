package personal.nfl.java.demo.asm.helloaop;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class AopClassAdapter extends ClassVisitor implements Opcodes {

    public AopClassAdapter(int api, ClassVisitor cv) {
        super(api, cv);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //更改类名，并使新类继承原有的类。
        super.visit(version, access, name + "_Tmp", signature, name, interfaces);
        MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, name, "<init>", "()V" , false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if ("<init>".equals(name))
            return null;
        if (!name.equals("helloAop"))
            return null;
        //
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        return new AopMethod(this.api, mv);
    }
}

class AopMethod extends MethodVisitor implements Opcodes {
    public AopMethod(int api, MethodVisitor mv) {
        super(api, mv);
    }

    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, Type.getInternalName(AopInterceptor.class), "beforeInvoke", "()V" , false);
    }

    public void visitInsn(int opcode) {
        if (opcode == RETURN) {
            mv.visitMethodInsn(INVOKESTATIC, Type.getInternalName(AopInterceptor.class), "afterInvoke", "()V" , false);
        }
        super.visitInsn(opcode);
    }
}
