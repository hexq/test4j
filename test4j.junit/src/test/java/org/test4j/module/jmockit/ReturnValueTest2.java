package org.test4j.module.jmockit;

import org.junit.Test;
import org.test4j.junit.Test4J;

import mockit.Mocked;
import mockit.internal.expectations.invocation.UnexpectedInvocation;

public class ReturnValueTest2 extends Test4J {
    @Mocked
    private SomeInterface someInterface;

    @Test
    public void oneOf() {
        new Expectations() {
            {
                someInterface.call();
//                returns("call one");
            }
        };
        this.someInterface.call();
    }

    @Test
    public void exactly() {
        new Expectations() {
            {
                when(someInterface.call()).callExactly(2);
//                returns("call one");
            }
        };
        this.someInterface.call();
        this.someInterface.call();
    }

    @Test
    public void allowing() {
        new Expectations() {
            {
                when(someInterface.call()).callIgnoreTimes();
//                returns("call one");
            }
        };
        this.someInterface.call();
        this.someInterface.call();
    }

    @Test
    public void ignoring() {
        new Expectations() {
            {
                when(someInterface.call()).callIgnoreTimes().thenReturn("call one");
            }
        };
        this.someInterface.call();
        this.someInterface.call();
    }

    @Test(expected = UnexpectedInvocation.class)
    public void never() {
        new Expectations() {
            {
                when(someInterface.call()).callNeverOccur();
//                returns("call one");
            }
        };
        this.someInterface.call();
    }

    @Test
    public void atLeast() {
        new Expectations() {
            {
                when(someInterface.call()).callMinimal(2).thenReturn("call one");;
//                returns("call one");
            }
        };
        this.someInterface.call();
        this.someInterface.call();
        this.someInterface.call();
    }

    @Test
    public void between() {
        new Expectations() {
            {
                when(someInterface.call()).callBetween(2, 3);
//                returns("call one");
            }
        };
        this.someInterface.call();
        this.someInterface.call();
        this.someInterface.call();
    }

    @Test
    public void atMost() {
        new Expectations() {
            {
                when(someInterface.call()).callMaximal(2).thenReturn("call one");
            }
        };
        this.someInterface.call();
        this.someInterface.call();
    }

    public static class SomeInterface {
        public String call() {
            return "testedObject";
        }
    }
}
