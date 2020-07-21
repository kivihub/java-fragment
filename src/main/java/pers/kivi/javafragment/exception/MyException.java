package pers.kivi.javafragment.exception;

/**
 * @author wangqiwei
 * @date 2020/07/20 9:43 PM
 */
public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable t) {
        super(message, t);
    }
}
