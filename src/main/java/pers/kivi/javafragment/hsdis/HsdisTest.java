package pers.kivi.javafragment.hsdis;

/**
 * @author wangqiwei
 * @date 2020/08/10 1:11 PM
 */
public class HsdisTest {
    /**
     * -XX:+UnlockDiagnosticVMOptions
     * -XX:+TraceClassLoading
     * -XX:+LogCompilation
     * -XX:LogFile=/tmp/mylogfile.log
     * -XX:+PrintAssembly
     * -XX:+TraceClassLoading
     *
     * @param args
     */
    public static void main(String[] args) {
        int j = qiweiFunc();
        System.out.println(j);
    }

    public static int qiweiFunc() {
        int i = 0;
        int j = i + 1;

        return j;
    }
}
