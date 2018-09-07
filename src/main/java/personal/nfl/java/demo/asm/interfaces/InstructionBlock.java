package personal.nfl.java.demo.asm.interfaces;

import org.objectweb.asm.MethodVisitor;

public interface InstructionBlock {

    void invoke(MethodVisitor methodVisitor);
}
