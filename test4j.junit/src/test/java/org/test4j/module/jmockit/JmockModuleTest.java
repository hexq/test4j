package org.test4j.module.jmockit;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.test4j.fortest.beans.ISpeak;
import org.test4j.junit.Test4J;

import mockit.Mocked;

public class JmockModuleTest extends Test4J {
    @Mocked
    private ISpeak say;

    @Before
    public void before() {
        new Expectations() {
            {
                when(say.count()).thenReturn(4);
            }
        };
    }

    @Test
    public void mock_test1() {
        new Expectations() {
            {
                when(say.count()).thenReturn(5);
            }
        };
        int count1 = say.count();
        MatcherAssert.assertThat(count1, IsEqual.equalTo(4));
        int count2 = say.count();
        MatcherAssert.assertThat(count2, IsEqual.equalTo(5));
    }

    @Test
    public void mock_test2() {
        new Expectations() {
            {
                when(say.count()).callIgnoreTimes().thenReturn(3);
            }
        };
        MatcherAssert.assertThat(say.count(), IsEqual.equalTo(4));
        MatcherAssert.assertThat(say.count(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(say.count(), IsEqual.equalTo(3));
    }
}
