package com.jacen.study.v3.aop.advisor;


import com.jacen.study.v3.aop.pointcut.Pointcut;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
