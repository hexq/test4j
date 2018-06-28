package org.test4j.tools.commons;

import java.util.Calendar;
import java.util.Date;

import org.test4j.testng.Test4J;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mockit.Mock;
import mockit.Mocked;

@Test(groups = { "test4j", "assertion" })
public class DateUtilTest extends Test4J {

    @Test
    public void testToDateTimeStr() {
        String dateStr = DateHelper.toDateTimeStr(getMockDate(), "yyyy-MM-dd HH:mm:ss");
        want.string(dateStr).isEqualTo("2010-02-12 19:58:55");
    }

    public void testToDateTimeStr_MockitExpectation() {
        new Expectations() {
            @Mocked
            DateHelper dateUtil;
            {
                DateHelper.now();
//                returns(getMockDate());
                result = getMockDate();
            }
        };
        String str = DateHelper.currDateTimeStr();
        want.string(str).isEqualTo("2010-02-12 19:58:55");
    }

    public void testToDateTimeStr_MockitExpectation_returnSequence() {
        new Expectations() {
            @Mocked
            DateHelper dateUtil;
            {
                DateHelper.now();
                returns(getMockDate(), mockCalendar(2013, 2, 12).getTime());
            }
        };
        String str = DateHelper.currDateTimeStr();
        want.string(str).isEqualTo("2010-02-12 19:58:55");
        str = DateHelper.currDateTimeStr();
        want.string(str).isEqualTo("2013-02-12 19:58:55");
    }

    public void testToDateTimeStr_MockitExpectation2() {
        new Expectations() {
            @Mocked
            DateHelper dateUtil;
            {
                DateHelper.now();
                result = mockCalendar(2015, 6, 25).getTime();
            }
        };
        String str = DateHelper.currDateTimeStr();
        want.string(str).isEqualTo("2015-06-25 19:58:55");
    }

    public void testToDateTimeStr_dynamicPartialMock() {
        new Expectations(DateHelper.class) {
            {
                DateHelper.now();
                result = mockCalendar(2009, 6, 25).getTime();
            }
        };
        String str = DateHelper.currDateTimeStr();
        want.string(str).isEqualTo("2009-06-25 19:58:55");
    }

    public static final Date getMockDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 1, 12, 19, 58, 55);
        return cal.getTime();
    }

    public static class MockDateUtil extends MockUp<DateHelper> {
        @Mock
        public static final Date now() {
            Calendar cal = mockCalendar();
            return cal.getTime();
        }
    }

    public static Calendar mockCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 1, 12, 19, 58, 55);
        return cal;
    }

    public static Calendar mockCalendar(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 19, 58, 55);
        return cal;
    }

    @Test(dataProvider = "dataParse_Format")
    public void testParse_Format(String input, String output) {
        Date date = DateHelper.parse(input);
        want.date(date).eqByFormat(output);
    }

    @DataProvider
    public DataIterator dataParse_Format() {
        return new DataIterator() {
            {
                data("2011-09-12 12:23:34", "2011-09-12 12:23:34");
                data("2011-09-13  12:23:34", "2011-09-13 12:23:34");
                data("2011-09-14", "2011-09-14");
                data("2011-9-14", "2011-09-14");
                data("2011-09-15 12:23:34.1", "2011-09-15 12:23:34");
                data("2011-09-16 12:23:34.145", "2011-09-16 12:23:34");
                data(" 2011-09-17  \t12:23:34 ", "2011-09-17 12:23:34");
            }
        };
    }

    @Test
    public void testParse() {
        Date date = DateHelper.parse("2010-10-20");
        want.date(date).eqByFormat("2010/10/20", "yyyy/MM/dd");
    }

    @Test
    public void testParse_ContainsMillioSecond() {
        Date date = DateHelper.parse("2010-10-20 18:20:36.231");
        want.date(date).eqByFormat("2010/10/20 06:20:36.231", "yyyy/MM/dd hh:mm:ss.SSS");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testParse_IllegalFormat() {
        DateHelper.parse("2010-10/20 18:20:36.231");
    }
}
