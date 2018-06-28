package org.test4j.module.jmockit.extend;

import org.hamcrest.Matcher;
import org.test4j.hamcrest.matcher.JMockitAdapter;
import org.test4j.module.jmockit.utility.ExpectationsUtil;
import org.test4j.tools.reflector.MethodAccessor;

import mockit.Verifications;
import mockit.internal.expectations.argumentMatching.ArgumentMatcher;

@SuppressWarnings({ "rawtypes" })
public class JMocketVerifications extends Verifications {

	public JMocketVerifications() {
		super();
		ExpectationsUtil.register(this);
	}

//	public JMocketVerifications(int numberOfIterations) {
//		super(numberOfIterations);
//		ExpectationsUtil.register(this);
//	}

	final static MethodAccessor methodAccessor = new MethodAccessor(Verifications.class, "addMatcher",
			ArgumentMatcher.class);

	protected final <T> T with(Matcher argumentMatcher) {
		JMockitAdapter adapter = JMockitAdapter.create(argumentMatcher);
		methodAccessor.invokeUnThrow(this, new Object[] { adapter });
		return null;
	}

	protected final <T> T with(T argValue, Matcher argumentMatcher) {
		JMockitAdapter adapter = JMockitAdapter.create(argumentMatcher);
		methodAccessor.invokeUnThrow(this, new Object[] { adapter });
		return argValue;
	}
}
