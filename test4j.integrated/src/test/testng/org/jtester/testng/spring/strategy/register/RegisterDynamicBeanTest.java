package org.jtester.testng.spring.strategy.register;

import org.jtester.module.spring.annotations.AutoBeanInject;
import org.jtester.module.spring.annotations.SpringContext;
import org.jtester.module.spring.annotations.SpringBeanByName;
import org.jtester.module.spring.annotations.AutoBeanInject.BeanMap;
import org.jtester.testng.JTester;
import org.test4j.fortest.service.UserAnotherDao;
import org.test4j.fortest.service.UserService;
import org.test4j.module.database.IDatabase;
import org.test4j.module.spring.ISpring;
import org.testng.annotations.Test;

@SpringContext({ "org/jtester/module/spring/testedbeans/xml/data-source.xml" })
@AutoBeanInject(maps = { @BeanMap(intf = "**.*Service", impl = "**.*ServiceImpl"),
		@BeanMap(intf = "**.*Dao", impl = "**.*DaoImpl") })
@Test(groups = "jtester")
public class RegisterDynamicBeanTest extends JTester implements IDatabase, ISpring {
	@SpringBeanByName
	protected UserService userService;

	@SpringBeanByName
	protected UserAnotherDao userAnotherDao;

	public void getSpringBean() {
		want.object(userService).notNull();
		Object o = spring.getBean("userAnotherDao");
		want.object(o).same(userAnotherDao);

		Object userDao = spring.getBean("userDao");
		want.object(userDao).notNull();
	}

	/**
	 * 测试深度嵌套的setProperty() 时，bean的自动注入
	 */
	public void callCascadedDao() {
		this.userAnotherDao.callCascadedDao();
	}

	/**
	 * 测试自动注入spring bean的时候调用spring init method
	 */
	public void testSpringInitMethod() {
		int springinit = (Integer) reflector.getField(userAnotherDao, "springinit");
		want.number(springinit).isEqualTo(100);
	}
}
