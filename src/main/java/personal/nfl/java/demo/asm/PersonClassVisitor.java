package personal.nfl.java.demo.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class PersonClassVisitor extends ClassVisitor {

    public PersonClassVisitor(int api) {
        super(api);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("待处理的方法：" + name);
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
