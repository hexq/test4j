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
@Deprecated
public class JMockitNonStrictExpectations extends Expectations implements Test4JInvocations {

    @Mocked
    protected InvokeTimes             invokerTimes;

    @Mocked
    protected ExpectationsResult      expectationsResult;

    @Mocked
    protected final TheStyleAssertion the;

    public JMockitNonStrictExpectations() {
        super();
        ExpectationsUtil.register(this);
        this.the = new TheStyleAssertion();
    }

    public JMockitNonStrictExpectations(int numberOfIterations, Object... classesOrObjectsToBePartiallyMocked) {
        super(numberOfIterations, classesOrObjectsToBePartiallyMocked);
        ExpectationsUtil.register(this);
        this.the = new TheStyleAssertion();
    }

    public JMockitNonStrictExpectations(Object... classesOrObjectsToBePartiallyMocked) {
        super(classesOrObjectsToBePartiallyMocked);
        ExpectationsUtil.register(this);
        this.the = new TheStyleAssertion();
    }

    public <T> ExpectationsResult when(T o) {
        ActiveInvocations.maxTimes(-1);
        return new ExpectationsResult(this);
    }

    @Override
    public void thenReturn(Object value) {
        super.returns(value, null);
    }

    @Override
    public void thenReturn(Object firstValue, Object... remainingValues) {
        super.returns(firstValue, remainingValues);
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
}
