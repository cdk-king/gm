package com.cdk.classLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {
    public void LoaderTest() {
        DiskClassLoader diskLoader = new DiskClassLoader("D:\\lib");
        try {
            Class c = diskLoader.loadClass("com.cdk.classLoader.DistClassTest");
            if (c != null) {
                try {
                    //newInstance: 弱类型。低效率。只能调用无参构造。
                    //new: 强类型。相对高效。能调用任何public构造。
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
