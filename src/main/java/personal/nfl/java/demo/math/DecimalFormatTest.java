package personal.nfl.java.demo.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DecimalFormatTest {

    public static void main(String[] arg) {
        BigDecimal bd = new BigDecimal(98798797879979.2342);
//        BigDecimal bd = new BigDecimal(123456789.3);
//        BigDecimal bd = new BigDecimal(12.3);
        System.out.println(parseNumber("#0.00", bd));
        System.out.println(parseNumber("#.00", bd));
        System.out.println(parseNumber(",##0.00", bd)); // out: 123,456,789
        System.out.println(parseNumber(",###0", bd)); // out: 123,456,789
        System.out.println(parseNumber(",###,###.00", bd)); // out: 123,456,789.30

    }

    public static String parseNumber(String pattern, BigDecimal bd) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(bd);
    }

    public static String format(String value, int place, String unit, int roundingMode) {
        try {
            BigDecimal num = new BigDecimal(value);
            num = num.setScale(place, roundingMode);
            value = num.toPlainString();
            return value + unit;
        } catch (Exception e) {
            return value;
        }
    }

}
