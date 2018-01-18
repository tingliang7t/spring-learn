package context;

import org.junit.Test;
import org.tingliang7t.spring.learn.context.ClassPathXmlApplicationContext;
import service.TestService;

public class ApplicationContextTest {

    @Test
    public void contextTest() {
        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");

            TestService testService = (TestService) applicationContext.getBean("testService");

            testService.printValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
