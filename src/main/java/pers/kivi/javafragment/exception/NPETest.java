package pers.kivi.javafragment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangqiwei
 * @date 2020/07/20 9:32 PM
 */
public class NPETest {
    static Logger logger = LoggerFactory.getLogger(NPETest.class);

    public static void main(String[] args) {
        try {
            try {
                long l = (Long) null;
            } catch (Throwable e) {
                // NPE的getMessage返回null
                throw new MyException("内部异常信息" + e.getMessage(), e);
            }
        } catch (Throwable throwable) {
//            logger.warn("外部异常信息", throwable);
//            logger.warn("外部异常信息{}", throwable);
//            logger.warn("外部异常信息{}{}", "占位", throwable, throwable);
            logger.warn("外部异常信息{}{}", "占位", throwable);
            logger.warn("外部异常信息{}{}{}", "占位", "占位", throwable);
        }
    }
}
