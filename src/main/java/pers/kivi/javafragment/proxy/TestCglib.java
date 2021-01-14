package pers.kivi.javafragment.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author wangqiwei
 * @date 2021/01/14 1:14 PM
 */
public class TestCglib {
    public static void main(String args[]) {
        Target origin = new Target();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new TargetInterceptor(origin));
        Target targetObject2 = (Target) enhancer.create();

//        targetObject2.echo("kivi");
//        targetObject2.run();
        targetObject2.runWithEcho("kivi");
    }
}
