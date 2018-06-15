package org.test4j.testng;

import java.util.List;

import org.test4j.tools.reflector.FieldAccessor;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.internal.IConfiguration;

import mockit.integration.testng.TestNGRunnerDecorator;

public class JMockitInvokeMethodListener {
    
	private final static FieldAccessor<IConfiguration> mField       = new FieldAccessor<IConfiguration>(
                                                                            TestRunner.class,
                                                                            "m_invokedMethodListeners");

    /**
     * jmockit的hookable<br>
     * 
     * @see mockit.integration.testng.TestNGRunnerDecorator
     */
    private Boolean beenJMockite = null;

    @SuppressWarnings("unchecked")
    public JMockitInvokeMethodListener(final ITestContext context) {
        if (context == null) {
            throw new RuntimeException("the testng conext can't be null.");
        }
        if (beenJMockite != null) {
            return;
        }
        List<IInvokedMethodListener> listeners = (List<IInvokedMethodListener>) mField.get(context);
        beenJMockite = false;
        for (IInvokedMethodListener listener : listeners) {
            if (listener instanceof TestNGRunnerDecorator) {
                beenJMockite = true;
            }
        }
//        if (beenJMockite == false) {
//            String hits = JMockitModuleHelper.getJMockitJavaagentHit();
//            throw new RuntimeException(hits);
//        }
    }
}
