package org.test4j.module.jmockit.demo;

import java.util.Observable;
import java.util.concurrent.Callable;

import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

import mockit.Capturing;
import mockit.Mocked;
import mockit.Verifications;

@Test(groups = "testng-demo")
public class InternalInstancesDemoTest extends Test4J {
	@Mocked
	@Capturing
	Service service;

	@Test
	public void captureAllInternallyCreatedInstances(@Mocked @Capturing final Callable<?> callable)
			throws Exception {
		Service initialMockService = service;

		new Expectations() {
			@Mocked
			@Capturing
			Observable observable;
			{
				service.doSomething();
				returns(3, 4);
			}
		};

		InternalInstancesDemo unit = new InternalInstancesDemo();
		int result = unit.businessOperation(true);

		want.object(unit.observable).notNull();
		want.object(initialMockService).not(the.object().same(service));
		want.number(result).isEqualTo(7);

		new Verifications() {
			{
				callable.call();
			}
		};
	}
}
