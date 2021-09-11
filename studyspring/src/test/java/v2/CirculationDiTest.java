package v2;

import com.jacen.study.v2.beans.BeanReference;
import com.jacen.study.v2.beans.GenericBeanDefinition;
import com.jacen.study.v2.beans.PreBuildBeanFactory;
import com.jacen.study.v2.sample.DBean;
import com.jacen.study.v2.sample.EBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CirculationDiTest {

	static PreBuildBeanFactory bf = new PreBuildBeanFactory();

	@Test
	public void testCirculationDI() throws Exception {
		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(DBean.class);
		List<Object> args = new ArrayList<>();
		args.add(new BeanReference("ebean"));
		bd.setConstructorArgumentValues(args);
		bf.registerBeanDefinition("dbean", bd);

		bd = new GenericBeanDefinition();
		bd.setBeanClass(EBean.class);
		args = new ArrayList<>();
		args.add(new BeanReference("dbean"));
		bd.setConstructorArgumentValues(args);
		bf.registerBeanDefinition("ebean", bd);

		bf.preInstantiateSingletons();
	}
}
