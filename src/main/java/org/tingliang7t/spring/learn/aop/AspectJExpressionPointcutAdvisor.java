package org.tingliang7t.spring.learn.aop;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

    public void setExression(String expression){
        this.pointcut.setExpression(expression);
    }

    @Override
    public Advice getAdvice(){
        return advice;
    }

    @Override
    public Pointcut getPointcut(){
        return pointcut;
    }
}
