package org.test4j.module.jmockit.intfmockup;

import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

import mockit.Mock;

@SuppressWarnings("unused")
@Test
public class MockUpTest_Interface extends Test4J {
	private ISayHello sayHello1;

	/**
	 * 测试接口使用MockUp的方式进行mock
	 */
	public void testInterfaceMockUp1() {
		new MockUp<ISayHello>() {
			{
//				sayHello1 = this.getMockInstance();
			}

			@Mock
			public String sayHello() {
				return "say hello1.";
			}
		};

		String hello1 = this.sayHello1.sayHello();
		want.string(hello1).isEqualTo("say hello1.");
	}

	public void testInterfaceMockUp2() {

		new MockUp<ISayHello>() {
			@Mock
			public String sayHello() {
				return "say hello2.";
			}
		};

		String hello1 = this.sayHello1.sayHello();
		want.string(hello1).isEqualTo("say hello2.");
	}
}
