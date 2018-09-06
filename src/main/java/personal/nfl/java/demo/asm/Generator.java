package personal.nfl.java.demo.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import personal.nfl.java.demo.asm.account.Account;
import personal.nfl.java.demo.asm.account.AddSecurityCheckClassAdapter;
import personal.nfl.java.demo.util.FileUtil;

import java.io.IOException;

import static org.objectweb.asm.ClassReader.SKIP_DEBUG;
import static org.objectweb.asm.Opcodes.ASM6;

/**
 * 利用 asm 修改字节码文件
 */
public class Generator {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello ASM");
        generator(Account.class);
    }

    public static void generator(Class clazz) throws IOException {
        String basePath = "../JavaDemo/out/production/asm/";
        ClassReader classReader = new ClassReader(clazz.getName());
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        classReader.accept(new AddSecurityCheckClassAdapter(ASM6, classWriter), SKIP_DEBUG);
        FileUtil.writeFile(basePath + clazz.getName().replace(".", "/") + ".class", classWriter.toByteArray());

    }
}
