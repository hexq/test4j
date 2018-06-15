package org.test4j.hamcrest.matcher.string;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.test4j.junit.Test4J;
import org.test4j.junit.annotations.DataFrom;

public class StringEqualMatcherTest extends Test4J {

    @Test
    @DataFrom("data_StringEqualMatcher_Equals")
    public void testMatches_Equals(String expected, Object actual, boolean doesMatch, StringMode[] modes) {
        StringEqualMatcher matcher = new StringEqualMatcher(expected);

        matcher.setStringModes(modes);
        boolean match = matcher.matches(actual);
        want.bool(match).isEqualTo(doesMatch);
    }

    public static Object[][] data_StringEqualMatcher_Equals() {
        return new Object[][] {
                { null, "", false, null },
                { "a b c", "a\f\rb\tc", true, new StringMode[] { StringMode.IgnoreSpace } },// <br>
                { "ABC", "AbC", true, new StringMode[] { StringMode.IgnoreCase } }, // <br>
                { "'abc\"\n\t\"abc'", "'abc' 'abc'", true,
                        new StringMode[] { StringMode.SameAsQuato, StringMode.SameAsSpace } }, // <br>
                { "a b c", "abc", false, null },// <br>
                { "ABC", "AbC", false, null }, // <br>
                { "'abc\"\n\t\"abc'", "'abc' 'abc'", false, null } // <br>
        };
    }

    @Test(expected = AssertionError.class)
    public void testMatches_ActualIsNull() {
        MatcherAssert.assertThat(null, new StringEqualMatcher(""));
    }
}
