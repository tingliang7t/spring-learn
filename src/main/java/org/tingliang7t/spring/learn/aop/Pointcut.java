package org.tingliang7t.spring.learn.aop;


public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
