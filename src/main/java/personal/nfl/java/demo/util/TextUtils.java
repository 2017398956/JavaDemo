package personal.nfl.java.demo.util;

public class TextUtils {

    public static boolean isEmpty(String string) {
        if (null == string || "".equals(string)) {
            return true;
        } else {
            return false;
        }
    }
}
