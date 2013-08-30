package org.test4j.tools.commons;

import java.util.Iterator;

import org.junit.Test;
import org.test4j.junit.JTester;
import org.test4j.junit.annotations.DataFrom;
import org.test4j.tools.commons.PrimitiveHelper;

@SuppressWarnings("rawtypes")
public class PrimitiveHelperTest implements JTester {

    @Test
    @DataFrom("testDoesEqualData")
    public void testDoesEqual(Number num1, Number num2, boolean result) {
        boolean actual = PrimitiveHelper.doesEqual(num1, num2);
        want.bool(actual).is(result);
    }

    public static Iterator testDoesEqualData() {
        return new DataIterator() {
            {
                data(1, 1L, true);
                data(Integer.valueOf(2), 2L, true);
                data(Long.valueOf(3), Short.valueOf("3"), true);
                data(4, 4.0, false);
                data(5.0d, 5.0f, true);
            }
        };
    }
}
