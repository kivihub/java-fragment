package agent;

import java.lang.instrument.Instrumentation;

/**
 * @author wangqiwei
 * @date 2020/05/20 12:58 PM
 */
public class Agent {
    public static void premain(String args, Instrumentation instrumentation) {
        ClassLogger transformer = new ClassLogger();
        instrumentation.addTransformer(transformer);
    }
}
