package lambda.function;


import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * {@link Function}的类型变量不能为Void，否则会编译错误
 */
public class FunctionTest {
    private Person personInstance;
    public FunctionTest() {
        System.out.println("init");
    }

    @Before
    public void initPerson() {
        personInstance = new Person();
        personInstance.setName("laoWang");
    }

    @Test
    public void testGetName() {
        Function<Person, String> ageFunction = Person::getName;
        assert StringUtils.equals(ageFunction.apply(personInstance), "laoWang");

//        Function<Void, String> func2 = personInstance::getName;  //Function的类型变量为Void, 编译错误
    }


    @Test
    public void testSetName() {
//        Function<String, Void> setFunction = personInstance::setName; // Function的类型变量为Void, 编译错误
//        BiFunction<Person, String, Void> setFunction2 = Person::setName; // Function的类型变量为Void, 编译错误

        BiConsumer<Person, String> setNameByBiConsumer = Person::setName;
        setNameByBiConsumer.accept(personInstance, "laoLi");
        assert StringUtils.equals(personInstance.getName(), "laoLi");

        Consumer<String> setNameByConsumer = personInstance::setName;
        setNameByConsumer.accept("laoZhang");
        assert StringUtils.equals(personInstance.getName(), "laoZhang");
    }

    @Test
    public void testEcho() {
        BiFunction<Person, String, String> echoByBiFunction = Person::echo;
        String result = echoByBiFunction.apply(personInstance, "hello");
        assert StringUtils.equals(result, "hello");

        Function<String, String> echoByFunction = personInstance::echo;
        result = echoByFunction.apply("hello");
        assert StringUtils.equals(result, "hello");
    }
}
