package org.test4j.module.jmockit.mockbug;

import mockit.Mocked;

import org.junit.Test;
import org.test4j.module.jmockit.mockbug.SayHelloImpl;
import org.test4j.module.jmockit.mockbug.SayHelloImpl2;
import org.test4j.module.spring.annotations.SpringBeanFrom;

public class JMockitMockManagerTest extends JMockitMockManageBaseTest {

	@SpringBeanFrom
	@Mocked(inverse = true, methods = { "<clinit>", "<init>" })
	SayHelloImpl sayHello;

	@SpringBeanFrom
	@Mocked(inverse = true, methods = { "<clinit>" })
	SayHelloImpl2 sayHello2;

	@Test
	public void sayHello_Mock() {
		new Expectations() {
			{
				when(sayHello.sayHello()).thenReturn("say hello mock1");
				when(sayHello2.sayHello()).thenReturn("say hello mock2");
			}
		};

		String str = sayHello.sayHello();
		want.string(str).isEqualTo("say hello mock1");

		String str2 = sayHello2.sayHello();
		want.string(str2).isEqualTo("say hello mock2");
	}
}
