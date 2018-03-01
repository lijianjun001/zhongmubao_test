package com.zhongmubao.api.util.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author 孙阿龙
 */
public class DataSourceAspect {
    /**
     * 在dao层方法获取datasource对象之前，在切面中指定当前线程数据源
     */
    public void before(JoinPoint point) {
        String defaultDb = "master";

        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                System.out.println("用户选择数据库库类型：" + data.value());
                HandleDataSource.putDataSource(data.value());
            } else {
                DataSource data = m.getAnnotation(DataSource.class);
                System.out.println("默认数据库" + defaultDb);
                HandleDataSource.putDataSource(defaultDb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
