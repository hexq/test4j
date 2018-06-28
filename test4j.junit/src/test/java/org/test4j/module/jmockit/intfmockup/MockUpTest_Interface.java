package org.test4j.module.jmockit.intfmockup;

import mockit.Mock;
import mockit.Mocked;

import org.junit.Test;
import org.test4j.junit.Test4J;

public class MockUpTest_Interface extends Test4J {
    
	@Mocked
	private ISayHello sayHello1;

    /**
     * 测试接口使用MockUp的方式进行mock
     */
    @Test
    public void testInterfaceMockUp1() {
        new MockUp<ISayHello>() {
           /* {
                sayHello1 = this.getMockInstance();
            }*/

            @Mock
            public String sayHello() {
                return "say hello1.";
            }
        };

        String hello1 = this.sayHello1.sayHello();
        want.string(hello1).isEqualTo("say hello1.");
    }

    @Test
    public void testInterfaceMockUp2() {

        new MockUp<ISayHello>() {
            @Mock
            public String sayHello() {
                return "say hello2.";
            }
        } /* .getMockInstance()*/;

        String hello1 = this.sayHello1.sayHello();
        want.string(hello1).isEqualTo("say hello2.");
    }
}
