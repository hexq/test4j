package jmockit.test4j;

import java.lang.reflect.Constructor;

import org.junit.internal.builders.JUnit4Builder;
import org.junit.runner.Runner;

import mockit.Mock;
import mockit.MockUp;

public class JUnit4BuilderDecorator extends MockUp<JUnit4Builder> {
    @SuppressWarnings("rawtypes")
    
    @Mock
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        Constructor constructor = Class.forName("org.test4j.junit.Test4JRunner").getDeclaredConstructor(Class.class);
        return (Runner) constructor.newInstance(testClass);
//        return new Test4JRunner(testClass);
    }
}
