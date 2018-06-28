package org.test4j.module.jmockit.extend;

import java.io.File;

import org.test4j.tools.commons.ClazzHelper;
import org.test4j.tools.commons.JSONHelper;

import mockit.internal.expectations.ActiveInvocations;

@SuppressWarnings({ "rawtypes" })
public class ExpectationsResult {
	
    protected Test4JInvocations currExpectations;

    protected ExpectationsResult(Test4JInvocations expectations) {
        this.currExpectations = expectations;
    }

    public void thenReturn(Object obj) {
        if (obj instanceof Throwable) {
            currExpectations.thenReturn(obj);
        } else {
            ActiveInvocations.addResult(obj);
        }
    }

    /**
     * return value parsed from xml file
     * 
     * @param claz the class of the return object
     * @param json
     */
    public void thenReturnFrom(Class claz, String json) {
        Object obj = JSONHelper.fromJsonFile(claz, json);
        thenReturn(obj);
    }

    /**
     * return value parsed from xml file
     * 
     * @param claz the class of the return object
     * @param path class path of the xml file
     * @param json
     */
    public void thenReturnFrom(Class claz, Class clazPath, String json) {
        String path = ClazzHelper.getPathFromPath(clazPath);
        Object obj = JSONHelper.fromJsonFile(claz, path + File.separatorChar + json);
        thenReturn(obj);
    }

    public void thenReturn(Object obj, Object... os) {
        thenReturn(obj);
        for (Object o1 : os) {
            thenReturn(o1);
        }
    }

    public void thenThrows(Throwable e) {
        ActiveInvocations.addResult(e);
    }

    public void thenThrows(Throwable e, Throwable... es) {
        thenThrows(e);
        for (Throwable e1 : es) {
            thenThrows(e1);
        }
    }
}
