package org.jtester.module.spring.resource;

import mockit.Mock;
import mockit.Mocked;

import org.jtester.module.spring.annotations.SpringBeanByName;
import org.jtester.module.spring.annotations.SpringBeanFrom;
import org.jtester.module.spring.annotations.SpringContext;
import org.jtester.module.spring.testedbeans.resource.UserDaoResourceImpl;
import org.junit.Test;
import org.test4j.fortest.beans.User;
import org.test4j.fortest.service.UserDao;
import org.test4j.fortest.service.UserService;
import org.test4j.junit.JTester;

@SpringContext({ "org/jtester/module/spring/testedbeans/resource/resource-bean.xml" })
public class ResourceBeanTest_SpringBeanFrom implements JTester {

    @SpringBeanByName
    UserService userService;

    @SpringBeanFrom
    @Mocked
    UserDao     userDao;

    @Test
    public void testResourceBean() {
        new MockUp<UserDaoResourceImpl>() {
            @Mock
            public void insertUser(User user) {
                want.fail("this api can't be invoke.");
            }
        };

        new Expectations() {
            {
                userDao.insertUser((User) any);
            }
        };
        userService.insertUser(new User());
    }
}
