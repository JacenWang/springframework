package com.jacen.study.v3.aop.advisor;


import com.jacen.study.v3.aop.pointcut.AspectJExcepressPointcut;
import com.jacen.study.v3.aop.pointcut.Pointcut;

public class AspectJPointcutAdvisor implements PointcutAdvisor {

    private String adviceBeanName;
    private String expression;
    private AspectJExcepressPointcut pointcut;

    public AspectJPointcutAdvisor(String adviceBeanName, String expression) {
        super();
        this.adviceBeanName = adviceBeanName;
        this.expression = expression;
        this.pointcut = new AspectJExcepressPointcut(this.expression);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public String getAdviceBeanName() {
        return this.adviceBeanName;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }

    public void setAdviceBeanName(String adviceBeanName) {
        this.adviceBeanName = adviceBeanName;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setPointcut(AspectJExcepressPointcut pointcut) {
        this.pointcut = pointcut;
    }
}
