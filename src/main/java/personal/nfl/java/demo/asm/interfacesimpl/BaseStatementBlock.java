package personal.nfl.java.demo.asm.interfacesimpl;

import org.objectweb.asm.MethodVisitor;
import personal.nfl.java.demo.asm.interfaces.InstructionBlock;
import personal.nfl.java.demo.asm.util.CodeGenerator;

public abstract class BaseStatementBlock implements InstructionBlock {

    @Override
    public void invoke(MethodVisitor methodVisitor) {
    }
}
