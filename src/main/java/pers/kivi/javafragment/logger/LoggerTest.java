package pers.kivi.javafragment.logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
    private Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    /**
     * 出现异常打印日志时需要把异常对象放到最后一个参数，好处：
     * 1）打印完整堆栈
     * 2）方便定位错误
     */
    @Test
    public void logException() {
        try {
            int i = 1 / 0;
        } catch (Throwable throwable) {
            logger.error("计算表达式‘{}’时出现异常", "1/0", throwable);
        }
    }
}
