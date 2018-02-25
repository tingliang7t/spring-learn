package org.tingliang7t.spring.learn.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();

}
