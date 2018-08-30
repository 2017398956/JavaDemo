package personal.nfl.java.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static void writeFile(String filePath , byte[] bytes){
        File file = new File(filePath);
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
}
