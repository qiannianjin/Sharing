package top.arexstorm.sharing.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.arexstorm.sharing.annotation.TargetDataSource;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DynamicDataSourceAspect {

    @Around("execution(public * top.arexstorm.sharing.service..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        if(targetMethod.isAnnotationPresent(TargetDataSource.class)){
            String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).value() ;
            DataSourceContextHolder.setDataSource(targetDataSource);
        }
        Object result = pjp.proceed();
        DataSourceContextHolder.clearDataSource();
        return result;
    }
}
