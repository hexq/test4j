package org.jtester.module.spring.strategy.register;

import org.jtester.module.spring.annotations.AutoBeanInject;
import org.jtester.module.spring.annotations.AutoBeanInject.BeanMap;
import org.jtester.module.spring.annotations.SpringBeanByName;
import org.jtester.module.spring.annotations.SpringContext;
import org.jtester.module.spring.annotations.SpringInitMethod;
import org.junit.Test;
import org.test4j.database.table.ITable;
import org.test4j.fortest.service.UserService;
import org.test4j.fortest.service.UserServiceImpl;
import org.test4j.junit.JTester;

@SuppressWarnings("serial")
@SpringContext({ "org/jtester/module/spring/testedbeans/xml/data-source.xml" })
@AutoBeanInject(maps = { @BeanMap(intf = "**.*Service", impl = "**.*ServiceImpl"),
        @BeanMap(intf = "**.*Dao", impl = "**.*DaoImpl") })
public class SpringBeanRegisterTest_RunDbFitInInitMethod implements JTester {
    @SpringBeanByName(claz = UserServiceImplEx.class)
    UserService userService;

    /**
     * 测试在init方法中执行dbfit文件
     */
    @Test
    public void paySalary() {
        double total = this.userService.paySalary("310010");
        want.number(total).isEqualTo(3100d);
    }

    public static class UserServiceImplEx extends UserServiceImpl {
        @SpringInitMethod
        public void initDbFit() {
            db.table(ITable.t_tdd_user).clean().insert(3, new DataMap() {
                {
                    this.put("id", "1", "2", "3");
                    this.put("sarary", "1000.0", "2100.0", "1000.0");
                    this.put("post_code", "310010", "310010", "310012");
                }
            }).commit();
        }
    }
}
