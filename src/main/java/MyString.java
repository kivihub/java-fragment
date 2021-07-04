import java.math.BigDecimal;

/**
 * @author wangqiwei
 * @date 2020/08/12 9:14 PM
 */
public class MyString {
    static {
        System.out.println("here is version 2 static block.");
    }

    public static void main(String[] args) {
//        System.out.println(new BigDecimal(0.1).setScale(2, BigDecimal.ROUND_UNNECESSARY).longValue());
        System.out.println(new BigDecimal("1.9").subtract(new BigDecimal("1")).setScale(0, BigDecimal.ROUND_HALF_UP).longValue());
    }

    public static void main1(String[] args) {
        double d = 0.2d;
        System.out.println(d + 1);
        System.out.println((d + 1) * 100);
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d + 1));
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UP);
        System.out.println(multiply.intValue());

        double d1 = 0.2d;
        System.out.println(d1 + 1);
        System.out.println((d1 + 1) * 100);
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1 + 1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal multiply1 = bigDecimal1.multiply(new BigDecimal(100));
        System.out.println(multiply1.intValue());

        double d2 = 1.01d;
        System.out.println(d2 - 1);
        System.out.println((d2 - 1) * 100);
        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(d2 - 1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal multiply2 = bigDecimal2.multiply(new BigDecimal(100));
        System.out.println(multiply2.toBigInteger().intValue());
//        System.out.println(multiply2.floatValue());

        double d3 = 1.1d;
        System.out.println(d3 - 1);
        System.out.println((d3 - 1) * 100);
        BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(d3 - 1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal multiply3 = bigDecimal3.multiply(new BigDecimal(100));
        System.out.println(multiply3.toBigInteger().intValue());
//        System.out.println(multiply3.floatValue());
    }
}
