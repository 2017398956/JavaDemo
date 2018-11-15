package personal.nfl.java.demo;

import personal.nfl.java.demo.util.StringUtil;

import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
//        StringUtil.printSystemProperties();
        int i = 0;
        switch (i++) {
            case 0:
                System.out.println("=== " + i);
                i--;
                break;
            case 1:
                System.out.println("+++++ " + i);
                i--;
                break;
        }
        System.out.println("======= " + i);
        String text = "2348729874";
        String key = "8";

        for (int index = 0; index < text.length() && index >= 0; ) {
            int temp = text.indexOf(key, index);
            if (temp >= 0) {
                index = temp + 1;
            } else {
                break;
            }
            System.out.println(index);
        }
    }

}
