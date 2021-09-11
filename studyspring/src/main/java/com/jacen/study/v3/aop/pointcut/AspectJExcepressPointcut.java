package com.jacen.study.v3.aop.pointcut;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

public class AspectJExcepressPointcut implements Pointcut{

    private static PointcutParser pp = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();
    private String expression;
    private PointcutExpression pe;

    public AspectJExcepressPointcut(String expression) {
        super();
        this.expression = expression;
        this.pe = pp.parsePointcutExpression(expression);
    }

    @Override
    public boolean matchsClass(Class<?> targetClass) {
        return pe.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    public boolean matchMethod(Method method, Class<?> targetClass) {
        ShadowMatch sm = pe.matchesMethodExecution(method);
        return sm.alwaysMatches();
       // return false;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public PointcutExpression getPe() {
        return pe;
    }

    public void setPe(PointcutExpression pe) {
        this.pe = pe;
    }


}
