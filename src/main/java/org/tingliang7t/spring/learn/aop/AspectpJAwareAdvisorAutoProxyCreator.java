package org.tingliang7t.spring.learn.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.tingliang7t.spring.learn.ioc.bean.BeanPostProcessor;
import org.tingliang7t.spring.learn.ioc.factory.AbstractBeanFactory;
import org.tingliang7t.spring.learn.ioc.factory.BeanFactory;
import org.tingliang7t.spring.learn.ioc.factory.BeanFactoryAware;

import java.util.List;

public class AspectpJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware{

    private AbstractBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception{
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception{
        if (bean instanceof AspectJExpressionPointcutAdvisor ){
            return bean;
        }

        if (bean instanceof MethodInvocation){
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors
                = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);

        for(AspectJExpressionPointcutAdvisor advisor : advisors){
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                AdvisedSupport advisedSupport = new AdvisedSupport();
                advisedSupport.setMethodInterceptor((MethodInterceptor)advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);

                return new JdkDynamicAopProxy(advisedSupport).getProxy();
            }
        }

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory)beanFactory;
    }
}
