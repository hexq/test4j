package org.test4j.junit.suitetest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.test4j.junit.suitetest.suite.Groups;

@Suite.SuiteClasses({ Test4JSuite.class })
@RunWith(Groups.class)
public abstract class GroupTest {

}