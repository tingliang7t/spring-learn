package org.tingliang7t.spring.learn.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised){
        this.advised = advised;
    }

    @Override
    public Object getProxy(){
        return Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] {advised.getTargetSource().getTargetClass()},
                this
        );
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable{
        MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
        return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
    }
}
