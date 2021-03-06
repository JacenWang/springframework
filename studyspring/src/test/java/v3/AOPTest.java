package v3;

import com.jacen.study.v3.aop.AdvisorAutoProxyCreator;
import com.jacen.study.v3.aop.advisor.AspectJPointcutAdvisor;
import com.jacen.study.v3.beans.BeanReference;
import com.jacen.study.v3.beans.GenericBeanDefinition;
import com.jacen.study.v3.beans.PreBuildBeanFactory;
import com.jacen.study.v3.sample.ABean;
import com.jacen.study.v3.sample.CBean;
import com.jacen.study.v3.sample.aop.MyAfterReturningAdvice;
import com.jacen.study.v3.sample.aop.MyBeforeAdvice;
import com.jacen.study.v3.sample.aop.MyMethodInterceptor;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class AOPTest {

	static PreBuildBeanFactory bf = new PreBuildBeanFactory();

	@Test
	public void testCirculationDI() throws Throwable {

		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(ABean.class);
		List<Object> args = new ArrayList<>();
		args.add("abean01");
		args.add(new BeanReference("cbean"));
		bd.setConstructorArgumentValues(args);
		bf.registerBeanDefinition("abean", bd);

		bd = new GenericBeanDefinition();
		bd.setBeanClass(CBean.class);
		args = new ArrayList<>();
		args.add("cbean01");
		bd.setConstructorArgumentValues(args);
		bf.registerBeanDefinition("cbean", bd);

		// 前置增强advice bean注册
		bd = new GenericBeanDefinition();
		bd.setBeanClass(MyBeforeAdvice.class);
		bf.registerBeanDefinition("myBeforeAdvice", bd);

		// 环绕增强advice bean注册
		bd = new GenericBeanDefinition();
		bd.setBeanClass(MyMethodInterceptor.class);
		bf.registerBeanDefinition("myMethodInterceptor", bd);

		// 后置增强advice bean注册
		bd = new GenericBeanDefinition();
		bd.setBeanClass(MyAfterReturningAdvice.class);
		bf.registerBeanDefinition("myAfterReturningAdvice", bd);

		// 往BeanFactory中注册AOP的BeanPostProcessor
		AdvisorAutoProxyCreator aapc = new AdvisorAutoProxyCreator();
		bf.registerBeanPostProcessor(aapc);
		// 向AdvisorAutoProxyCreator注册Advisor
		aapc.registAdvisor(
				new AspectJPointcutAdvisor("myBeforeAdvice", "execution(* com.jacen.study.v3.sample.ABean.*(..))"));
		// 向AdvisorAutoProxyCreator注册Advisor
		aapc.registAdvisor(
				new AspectJPointcutAdvisor("myMethodInterceptor", "execution(* com.jacen.study.v3.sample.ABean.do*(..))"));
		// 向AdvisorAutoProxyCreator注册Advisor
		aapc.registAdvisor(new AspectJPointcutAdvisor("myAfterReturningAdvice",
				"execution(* com.jacen.study.v3.sample.ABean.do*(..))"));

		bf.preInstantiateSingletons();

		ABean abean = (ABean) bf.getBean("abean");

		abean.doSomthing();
		System.out.println("--------------------------------");
		abean.sayHello();
	}
}
