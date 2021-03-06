package org.test4j.module.jmockit.utility;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import mockit.internal.expectations.RecordAndReplayExecution;
import mockit.internal.expectations.TestOnlyPhase;
import mockit.internal.expectations.argumentMatching.ArgumentMatcher;
import mockit.internal.expectations.argumentMatching.ArgumentMismatch;
import mockit.internal.state.TestRun;

/**
 * 用于注册或获取当前线程下当前使用的org.jmock.Expectations实例<br>
 * 用于expectation参数断言调用org.test4j.hamcrest.iassert.common.intf.IAssert.wanted( )
 * 时,向当前的expectation实例注册期望发生的方法等
 * 
 * @author darui.wudr
 */
@SuppressWarnings({ "rawtypes" })
public class ExpectationsUtil {
    private static ThreadLocal<Object> jes = new ThreadLocal<Object>();

    /**
     * 注册当前线程下的mockit.Expectations实例
     * 
     * @param ex
     */
    public static void register(mockit.Expectations ex) {
        jes.set(ex);
    }

    /**
     * 注册当前线程下的mockit.Verifications实例
     * 
     * @param ex
     */
    public static void register(mockit.Verifications ve) {
        jes.set(ve);
    }

    public static boolean isJmockitExpectations() {
        Object ex = jes.get();
        return ex == null ? false : ex instanceof mockit.Expectations;
    }

    public static boolean isJmockitVerifications() {
        Object ex = jes.get();
        return ex == null ? false : ex instanceof mockit.Verifications;
    }

    /**
     * 往mockit.Expectations中增加参数断言
     * 
     * @param matcher
     */
    public static void addArgMatcher(Matcher matcher) {
//        RecordAndReplayExecution instance = TestRun.getRecordAndReplayForRunningTest(false);
    	 RecordAndReplayExecution instance = TestRun.getRecordAndReplayForRunningTest();

        if (instance == null) {
            return;
        }
        TestOnlyPhase currentPhase = instance.getCurrentTestOnlyPhase();
        if (currentPhase != null) {
            ArgumentMatcher _matcher = convert(matcher);
            currentPhase.addArgMatcher(_matcher);
        }
    }

    /**
     * 把官方的ext.test4j.hamcrest.Matcher类转换为mockit.external.hamcrest.Matcher类
     * 
     * @param matcher
     * @return
     */
    public static ArgumentMatcher convert(final Matcher matcher) {
        return new ArgumentMatcher() {
        	@Override
			public boolean same(ArgumentMatcher other) {
				 return this == other;
			}
        	
            @Override
            public boolean matches(Object item) {
                return matcher.matches(item);
            }

            @Override
            public void writeMismatchPhrase(ArgumentMismatch argumentMismatch) {
                Description message = new StringDescription();
                matcher.describeTo(message);
                argumentMismatch.append(message.toString());
            }
        };
    }
}
