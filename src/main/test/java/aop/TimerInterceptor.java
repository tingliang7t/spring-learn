package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable{

        long time = System.nanoTime();
        System.out.println("Invocation of method" + methodInvocation.getMethod().getName() + "start !");
        Object proceed = methodInvocation.proceed();
        System.out.println("Invocation of method" + methodInvocation.getMethod().getName() + "end !");
        return proceed;
    }
}
