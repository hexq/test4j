package org.test4j.tools.commons;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.test4j.junit.Test4J;

import mockit.Mock;

//@UsingMocksAndStubs({ MockDateUtil.class })
@Deprecated
public class DateUtilTest_MockClass extends Test4J {
    public static class MockDateUtil extends MockUp<DateHelper> {
        @Mock
        public static Date now() {
            Calendar cal = DateUtilTest.mockCalendar(2012, 1, 28);
            return cal.getTime();
        }
    }

    @Test
    public void testCurrDateTimeStr_format() {
        String str = DateHelper.currDateTimeStr("MM/dd/yy hh:mm:ss");
        want.string(str).isEqualTo("01/28/12 07:58:55");
    }
}
