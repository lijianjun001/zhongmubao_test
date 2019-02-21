package com.zhongmubao.api.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestAnotation extends Father<ProjectModel> {
    @MyAnnotation(getPath = "aaaa")
    public void getA(String a) {
        System.out.println("child" + a);
    }

    public static void main(String args[]) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TestAnotation testAnotation = new TestAnotation();
        testAnotation.getc();
        testAnotation.getD(new ProjectModel());

        Method[] methods = TestAnotation.class.getMethods();
        for (Method method : methods) {

            Class[] classes = method.getParameterTypes();
            for (Class c : classes) {
                System.out.println(method.getName() + "的参数是" + c.getName());
            }
        }
        System.out.println(TestAnotation.class.getGenericSuperclass().getTypeName());

//        System.out.println(TestAnotation.class.getTypeParameters()[0]);

        Method method = TestAnotation.class.getMethod("getA", String.class);
        if (method.getAnnotation(MyAnnotation.class) != null) {
            System.out.println(method.getAnnotation(MyAnnotation.class).getPath());
        }
        method.invoke(testAnotation, "a");

        Method methodb = TestAnotation.class.getMethod("getB", ProjectModel.class);
        if (methodb.getAnnotation(MyAnnotation.class) != null) {
            System.out.println(methodb.getAnnotation(MyAnnotation.class).getPath());
        }
        ProjectModel projectModel = new ProjectModel();
        projectModel.setCustomerId("bbbbb");
        methodb.invoke(testAnotation, projectModel);

        Type[] t = methodb.getGenericParameterTypes();

        printlnType(t);
        System.out.println("泛型类型：" + TestAnotation.class.getGenericSuperclass().getTypeName());

    }

    private static void printlnType(Type[] t2) {
        for (Type paramType : t2) {
            System.out.println("#" + paramType);
            if (paramType instanceof ParameterizedType) {
                //获取泛型中的具体信息
                Type[] genericTypes = ((ParameterizedType) paramType).getActualTypeArguments();
                for (Type genericType : genericTypes) {
                    System.out.println("泛型类型：" + genericType);
                }
            }
        }
    }


}

abstract class Father<T extends ProjectModel> {

    public void getA(String a) {
        System.out.println("Fathera" + a);
    }
    public void getB(T t) {
        System.out.println("Fatherb" + t.getCustomerId());
    }

    public String getc() throws NoSuchMethodException {
        Method method=this.getClass().getMethod("getc");
        Type t = method.getGenericReturnType();
        System.out.println(t.getTypeName());
        return "c";
    }


    public T getD(T t) throws NoSuchMethodException {
        Method method=this.getClass().getMethod("getD",ProjectModel.class);
        Type type = method.getGenericReturnType();
        System.out.println(type.getTypeName());
        return t;
    }
}