package org.test4j.spec;

import org.test4j.module.ICore;
import org.test4j.module.database.IDatabase;
import org.test4j.module.jmockit.IMockit;
import org.test4j.module.spring.ISpring;

/**
 * JSpec步骤定义文件接口 <br>
 * 标识接口
 * 
 * @author darui.wudr 2013-6-3 下午7:04:36
 */
public interface Steps<T extends SharedData> extends ICore, IMockit, ISpring, IDatabase {
    /**
     * 设置共享数据
     * 
     * @param data
     */
    void setSharedData(T data);

    public static class Default<T extends SharedData> implements Steps<T> {
        protected T shared;

        @Override
        public void setSharedData(T data) {
            this.shared = data;
        }
    }
}
