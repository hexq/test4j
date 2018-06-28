package org.test4j.module.jmockit;


import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

import mockit.Mocked;
import mockit.internal.expectations.invocation.UnexpectedInvocation;

@Test(groups = "test4j")
public class ReturnValueTest2 extends Test4J {
    @Mocked
    private SomeInterface someInterface;

    public void oneOf() {
        new Expectations() {
            {
                someInterface.call();
//                returns("call one");
                result = "call one";
            }
        };
        this.someInterface.call();
    }

    public void exactly() {
        new Expectations() {
            {
                when(someInterface.call()).callExactly(2);
//                returns("call one");
                result = ("call one");
            }
        };
        this.someInterface.call();
        this.someInterface.call();
    }

    public void allowing() {
        new Expectations() {
            {
                when(someInterface.call()).callIgnoreTimes();
//                returns("call one");
                result = "call one";
            }
        };
        this.someInterface.call();
        this.someInterface.call();
    }

    public void ignoring() {
        new Expectations() {
            {
                when(someInterface.call()).callIgnoreTimes().thenReturn("call one");
            }
        };
        this.someInterface.call();
        this.someInterface.call();
    }

    @Test(expectedExceptions = UnexpectedInvocation.class)
    public void never() {
        new Expectations() {
            {
                when(someInterface.call()).callNeverOccur();
//                returns("call one");
                result = "call one";
            }
        };
        this.someInterface.call();
    }

    public void atLeast() {
        new Expectations() {
            {
                when(someInterface.call()).callMinimal(2);
//                returns("call one");
                result = "call one";
            }
        };
        this.someInterface.call();
        this.someInterface.call();
        this.someInterface.call();
    }

    public void between() {
        new Expectations() {
            {
                when(someInterface.call()).callBetween(2, 3);
//                returns("call one");
                result = "call one";
            }
        };
        this.someInterface.call();
        this.someInterface.call();
        this.someInterface.call();
    }

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
            return "test";
        }
    }
}
