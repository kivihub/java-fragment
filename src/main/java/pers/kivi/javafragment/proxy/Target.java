package pers.kivi.javafragment.proxy;

/**
 * @author wangqiwei
 * @date 2021/01/14 1:12 PM
 */
public class Target {
    public String echo(String paramName) {
        System.out.println("hello " + paramName);
        return paramName;
    }

    public void run() {
        System.out.println("running...");
    }

    public void runWithEcho(String name) {
        System.out.println("begin running with echo");
        run();
        echo(name);
        System.out.println("end running with echo");
    }

    @Override
    public String toString() {
        return "TargetObject []" + getClass();
    }
}
