package com.tigrex.user.utils;

import com.tigrex.api.constant.ContextConst;
import com.tigrex.api.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Order(-1)
@Aspect
public class DynamicDataSourceAspect {

    /**
     * 注解出切入数据库链接转换
     * @param point
     */
    @Before("@annotation(com.tigrex.api.annotation.DataSource)")
    public void doBefore(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        ContextConst.DataSourceType dataSource = DataSourceContextHolder.DEFAULT_DATASOURCE;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DataSource注解
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource annotation = method.getAnnotation(DataSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dataSource.name());
        // 切换数据源
        DataSourceContextHolder.setDataSource(dataSource.name());
    }

    /**
     * 在注解处处理事务结束之后的数据链接清除
     * @param point
     */
    @After("@annotation(com.tigrex.api.annotation.DataSource)")
    public void doAfter(JoinPoint point) {
        DataSourceContextHolder.clearDataSource();
    }

}
