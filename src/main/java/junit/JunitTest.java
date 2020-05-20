package junit;

import org.junit.*;

/**
 * @author wangqiwei
 * @date 2020/05/20 9:19 AM
 */
public class JunitTest {
    @BeforeClass
    public static void beforeClassFunc() {
        System.out.println("before class");
    }

    @AfterClass
    public static void afterClassFunc() {
        System.out.println("after class");
    }

    @Before
    public void beforeFunc() {
        System.out.println("before");
    }

    @After
    public void afterFunc() {
        System.out.println("after");
    }

    @Test
    public void testFunc() {
        System.out.println("test");
    }
}
