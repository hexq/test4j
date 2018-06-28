package org.test4j.tools.commons;

import java.util.Calendar;
import java.util.Date;

import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

import mockit.Mock;

@Test(groups = "test4j")
//@UsingMocksAndStubs({ MockDateUtil.class })
public class DateUtilTest_MockClass extends Test4J {
    public static class MockDateUtil extends MockUp<DateHelper> {
        @Mock
        public Date now() {
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
