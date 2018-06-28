package org.test4j.testng;

import org.test4j.module.ICore;
import org.test4j.module.database.IDatabase;
import org.test4j.module.jmockit.IMockit;
import org.test4j.module.jmockit.utility.JMockitModuleHelper;
import org.test4j.module.spring.ISpring;

public abstract class Test4J extends Test4JCore implements ICore, IMockit, ISpring, IDatabase {
  /*  static {
        JMockitModuleHelper.getJMockitJavaagentHit();
    }*/
}
