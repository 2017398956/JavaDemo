package personal.nfl.java.demo.asm.interfacesimpl;

import org.objectweb.asm.MethodVisitor;
import personal.nfl.java.demo.asm.util.CodeGenerator;

public class PrintlnStatement extends BaseStatementBlock {

    private String printlnString = null;

    public PrintlnStatement(String printlnString) {
        this.printlnString = printlnString;
    }

    @Override
    public void invoke(MethodVisitor methodVisitor) {
        super.invoke(methodVisitor);
        CodeGenerator.println(methodVisitor, printlnString);
    }

    public String getPrintlnString() {
        return printlnString;
    }

    public void setPrintlnString(String printlnString) {
        this.printlnString = printlnString;
    }
}
