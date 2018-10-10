package personal.nfl.java.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static void writeFile(String filePath, byte[] bytes) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(file);
            fout.write(bytes);
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getProjectDir() {
        return System.getProperties().getProperty("user.dir");
    }

    public static String getMyTempDir() {
        return System.getProperties().getProperty("user.dir") + File.separator + "MyTemp" + File.separator;
    }
}
