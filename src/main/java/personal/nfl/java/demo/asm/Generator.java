package personal.nfl.java.demo.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import personal.nfl.java.demo.asm.account.Account;
import personal.nfl.java.demo.asm.account.AddSecurityCheckClassAdapter;
import personal.nfl.java.demo.asm.helloaop.AopClassAdapter;
import personal.nfl.java.demo.asm.helloaop.HelloAop;
import personal.nfl.java.demo.util.FileUtil;

import java.io.IOException;

import static org.objectweb.asm.ClassReader.SKIP_DEBUG;
import static org.objectweb.asm.Opcodes.ASM6;

/**
 * 利用 asm 修改字节码文件
 */
public class Generator {

    public static final String BASE_PATH = "../JavaDemo/out/production/asm/" ;
    public static void main(String[] args) throws IOException {
        System.out.println("Hello ASM");
        generator(Account.class);
//        generator(HelloAop.class, AopClassAdapter.class);
    }

    public static String createClassPath(Class clazz){
        return BASE_PATH + clazz.getName().replace(".", "/") + ".class" ;
    }

    public static void generator(Class clazz) throws IOException {
        ClassReader classReader = new ClassReader(clazz.getName());
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        classReader.accept(new AddSecurityCheckClassAdapter(ASM6, classWriter), SKIP_DEBUG);
        FileUtil.writeFile(createClassPath(clazz), classWriter.toByteArray());
    }

    public static void generator(Class clazz, Class<? extends ClassVisitor> classVisitorClass) {
        try {
            ClassReader classReader = new ClassReader(clazz.getName());
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            classReader.accept(classVisitorClass.getConstructor(int.class , ClassVisitor.class).newInstance(ASM6, classWriter), SKIP_DEBUG);
            FileUtil.writeFile(createClassPath(clazz), classWriter.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getA(int a){}
    public void getA(Integer a){}
}
