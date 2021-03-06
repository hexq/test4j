package org.test4j.module.jmockit.extend;

import org.hamcrest.Matcher;
import org.test4j.hamcrest.TheStyleAssertion;
import org.test4j.hamcrest.matcher.JMockitAdapter;
import org.test4j.module.jmockit.utility.ExpectationsUtil;
import org.test4j.tools.reflector.MethodAccessor;

import mockit.Expectations;
import mockit.Mocked;
import mockit.internal.expectations.ActiveInvocations;
import mockit.internal.expectations.argumentMatching.ArgumentMatcher;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class JMockitExpectations extends Expectations implements Test4JInvocations {
    
	@Mocked
    protected InvokeTimes invokeTimes;

    @Mocked
    protected ExpectationsResult expectationsResult;

    @Mocked
    protected TheStyleAssertion the;

    public JMockitExpectations() {
        super();
        ExpectationsUtil.register(this);
        this.the = new TheStyleAssertion();
    }

    public JMockitExpectations(int numberOfIterations, Object... classesOrObjectsToBePartiallyMocked) {
        super(numberOfIterations, classesOrObjectsToBePartiallyMocked);
        ExpectationsUtil.register(this);
        this.the = new TheStyleAssertion();
    }

    public JMockitExpectations(Object... classesOrObjectsToBePartiallyMocked) {
        super(classesOrObjectsToBePartiallyMocked);
        ExpectationsUtil.register(this);
        this.the = new TheStyleAssertion();
    }

    public <T> InvokeTimes when(T o) {
        return new InvokeTimes(this);
    }

    @Override
    public void thenReturn(Object value) {
    		this.thenReturn(value);
    }

    public void thenThrow(Throwable e) {
        ActiveInvocations.addResult(e);
    }

    @Override
    public void thenReturn(Object firstValue, Object... remainingValues) {
        super.returns(firstValue, remainingValues);
    }

    public void thenDo(Delegate delegate) {
        super.returns(delegate, null);
    }

    public <T> T any(Class<T> claz) {
        T o = the.object().any().wanted(claz);
        return o;
    }

    public <T> T is(T value) {
        T o = (T) the.object().reflectionEq(value).wanted();
        return o;
    }

    final static MethodAccessor methodAccessor = new MethodAccessor(Expectations.class, "addMatcher",
                                                       ArgumentMatcher.class);

    protected final <T> T with(Matcher argumentMatcher) {
        JMockitAdapter adapter = JMockitAdapter.create(argumentMatcher);
        methodAccessor.invokeUnThrow(this, new Object[] { adapter });

        Object argValue = adapter.getInnerValue();
        return (T) argValue;
    }

    protected final <T> T with(T argValue, Matcher argumentMatcher) {
        JMockitAdapter adapter = JMockitAdapter.create(argumentMatcher);
        methodAccessor.invokeUnThrow(this, new Object[] { adapter });
        return argValue;
    }

    public static interface Delegate extends mockit.Delegate {
    }
}
