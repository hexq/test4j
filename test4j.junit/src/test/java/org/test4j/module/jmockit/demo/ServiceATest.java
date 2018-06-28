package org.test4j.module.jmockit.demo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import mockit.Mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.test4j.junit.Test4J;
import org.test4j.module.core.utility.MessageHelper;

@SuppressWarnings("rawtypes")
public class ServiceATest extends Test4J {

    public static class MockDatabase extends MockUp<Database> {
        @Mock /*(invocations = 1) */
        public static List<?> find(String ql, Object arg1) {
            want.string(ql).notNull();
            want.object(arg1).notNull();
            return Collections.EMPTY_LIST;
        }

        @Mock /*(maxInvocations = 1) */
        public static void save(Object o) {
            want.object(o).notNull();
        }
    }

    private MockDatabase mock;

    @Before
    public void setUp() {
        mock = new MockDatabase();
    }

    @After
    public void tearDown() {
//        mock.tearDown();
    }

    @Test
    public void doBusinessOperationXyz() throws Exception {
        final BigDecimal total = new BigDecimal("125.40");

        MessageHelper.info("doBusinessOperationXyz-test");
        new MockUp(ServiceB.class) {
            @Mock /* (invocations = 1) */
            public BigDecimal computeTotal(List<?> items) {
                want.collection(items).notNull();
                return total;
            }
        };

        EntityX data = new EntityX(5, "abc", "5453-1");
        new ServiceA().doBusinessOperationXyz(data);
        want.number(data.getTotal()).isEqualTo(total);
    }

    @Test(expected = Exception.class)
    public void doBusinessOperationXyzWithInvalidItemStatus() throws Exception {
        new MockUp(ServiceB.class) {
            @Mock
            public BigDecimal computeTotal(List<?> items) throws Exception {
                throw new Exception();
            }
        };

        EntityX data = new EntityX(5, "abc", "5453-1");
        new ServiceA().doBusinessOperationXyz(data);
    }
}
