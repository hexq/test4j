package org.test4j.junit;

import org.junit.runner.RunWith;
import org.test4j.module.ICore;
import org.test4j.module.database.IDatabase;
import org.test4j.module.jmockit.IMockit;
import org.test4j.module.spring.ISpring;


/**
 * 
 * @see mockit.integration.junit4.JMockit
 * @see Test4JRunner
 * 
 *
 * @author hxq8176
 * @date 2018年6月14日 下午4:28:04
 *
 */
@RunWith(Test4JRunner.class)
public abstract class Test4J implements ICore, IMockit, ISpring, IDatabase {
   /* static {
        JMockitModuleHelper.getJMockitJavaagentHit();
    }*/
}
