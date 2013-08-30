package org.jtester.module.spring.parentcontext;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.test4j.fortest.service.UserServiceImpl;
import org.test4j.junit.JTester;

@SuppressWarnings("unused")
public class SpringParentDemo implements JTester {
    @Test
    public void demoSpringParent() {
        ClassPathXmlApplicationContext parent = new ClassPathXmlApplicationContext(new String[] {
                "org/jtester/module/spring/testedbeans/xml/data-source.xml",
                "org/jtester/module/spring/testedbeans/xml/beans.xml" });

        Object userService1 = parent.getBean("userService");
        want.object(userService1).notNull();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "org/jtester/module/spring/testedbeans/xml/beans-child.xml" }, parent);

        Object userService2 = context.getBean("userService");
        UserServiceImpl impl = reflector.getSpringAdvisedTarget(userService2);
        want.object(userService2).notNull();
    }
}
