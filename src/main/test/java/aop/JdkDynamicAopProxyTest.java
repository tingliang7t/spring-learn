package aop;

import org.junit.Test;
import org.tingliang7t.spring.learn.aop.AdvisedSupport;
import org.tingliang7t.spring.learn.aop.JdkDynamicAopProxy;
import org.tingliang7t.spring.learn.aop.TargetSource;
import org.tingliang7t.spring.learn.context.ApplicationContext;
import org.tingliang7t.spring.learn.context.ClassPathXmlApplicationContext;
import service.PrintValueService;
import service.TestService;

public class JdkDynamicAopProxyTest {

    @Test
    public void testInterceptor () throws Exception{

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");

        PrintValueService testService = (PrintValueService) ctx.getBean("testService");

        testService.printValue();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(PrintValueService.class, testService);
        advisedSupport.setTargetSource(targetSource);

        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        PrintValueService testServiceProxy = (PrintValueService) jdkDynamicAopProxy.getProxy();

        testServiceProxy.printValue();
    }
}
