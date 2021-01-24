package pers.kivi.javafragment.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wangqiwei
 * @date 2021/01/14 1:13 PM
 */
public class TargetInterceptor implements MethodInterceptor {
    Target origin;

    public TargetInterceptor(Target origin) {
        this.origin = origin;
    }

    /**
     * 重写方法拦截在方法前和方法后加入业务
     * Object obj为目标对象
     * Method method为目标方法
     * Object[] params 为参数，
     * MethodProxy proxy CGlib方法代理对象
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] params, MethodProxy proxy) throws Throwable {
        System.out.println("调用前");
        // 自调用时，不会走代理
        Object result = proxy.invoke(origin, params);

        // 自调用时，会走代理
//        Object result = proxy.invokeSuper(obj, params);

        System.out.println("调用后" + result);
        return result;
    }


}