package personal.nfl.java.demo.asm.account;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import personal.nfl.java.demo.asm.util.SecurityChecker;

public class AddSecurityCheckMethodAdapter extends MethodVisitor {

    public AddSecurityCheckMethodAdapter(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(SecurityChecker.class),
                "checkSecurity", Type.getMethodDescriptor(Type.VOID_TYPE) , false);
    }
}