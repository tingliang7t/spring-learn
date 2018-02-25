package org.tingliang7t.spring.learn.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}
