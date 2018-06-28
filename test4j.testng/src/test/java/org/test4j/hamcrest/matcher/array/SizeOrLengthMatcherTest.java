package org.test4j.hamcrest.matcher.array;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.test4j.hamcrest.matcher.array.SizeOrLengthMatcher.SizeOrLengthMatcherType;
import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

@Test(groups = { "test4j", "assertion" })
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SizeOrLengthMatcherTest extends Test4J {

    public void test_AssertMessage() {
        Matcher matcher = new SizeOrLengthMatcher(2, SizeOrLengthMatcherType.EQ);
        Object actuals = new int[] { 1, 2, 3 };
        try {
            MatcherAssert.assertThat(actuals, matcher);
            want.fail("之前应该已抛出异常");
        } catch (Throwable e) {
            String message = e.getMessage();
            want.string(message).contains("but actual size is[3].");
        }
    }
}
