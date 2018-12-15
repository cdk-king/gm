package com.cdk.util.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {

    @MyAnnotation(hello = "Hello,cdk", world = "Hello,world")
    public void output() {
        System.out.println("method output is running ");
    }


    public void test() throws NoSuchMethodException {

        // 获得要调用的类
        Class<AnnotationTest> myTestClass = AnnotationTest.class;
        // 获得要调用的方法，output是要调用的方法名字，new Class[]{}为所需要的参数。空则不是这种
        Method method = myTestClass.getMethod("output", new Class[]{});
        // 是否有类型为MyAnnotation的注解
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            // 获得注解
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            // 调用注解的内容
            System.out.println(annotation.hello());
            System.out.println(annotation.world());
        }
        System.out.println("----------------------------------");
        // 获得所有注解。必须是runtime类型的
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            // 遍历所有注解的名字
            System.out.println(annotation.annotationType().getName());
        }

    }

}
