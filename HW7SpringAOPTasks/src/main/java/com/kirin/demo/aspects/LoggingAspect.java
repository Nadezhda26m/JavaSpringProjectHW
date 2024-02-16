package com.kirin.demo.aspects;

import com.kirin.demo.model.Task;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    @Order(1)
    @Before("execution(* com.kirin.demo.services.TaskService.*(..))")
    public void logAction(JoinPoint joinPoint) {
        System.out.println("Метод " + joinPoint.getSignature().toString() + " был вызван");
    }

    @Order(2)
    @Before(value = "@annotation(com.kirin.demo.aspects.TrackUserAction)")
    public void logArgsAction(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        System.out.println("Аргументы метода: " + Arrays.toString(arguments));
    }

    @After("execution(* com.kirin.demo.services.TaskService.*(..))")
    public void logEndAction(JoinPoint joinPoint) {
        System.out.println("Метод " + joinPoint.getSignature().toShortString()
                + " завершил свою работу");
    }

    @AfterReturning(
            value = "@annotation(com.kirin.demo.aspects.TrackUserAction) " +
                    "&& execution(* com.kirin.demo.services.TaskService.*(..))",
            returning = "returnTask")
    public void logReturnValue(JoinPoint joinPoint, Task returnTask) {
        System.out.println("Возвращаемая задача: " + returnTask);
    }

    @AfterReturning(
            value = "execution(* com.kirin.demo.services.TaskService.*(..))",
            returning = "listTasks")
    public void logCountTasks(JoinPoint joinPoint, List<Task> listTasks) {
        System.out.println("AfterReturning для: " + joinPoint.toLongString());
        System.out.println("Всего найдено задач: " + listTasks.size());
    }

    @Around("execution(* com.kirin.demo.services.TaskService.*(..))")
    public Object addBorders(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(">>>");
        Object result = joinPoint.proceed();
        System.out.println(">>>");
        return result;
    }

    @Before("execution(* com.kirin.demo.controllers.*.*(..))")
    public void logControllerAction(JoinPoint joinPoint) {
        System.out.println("Метод " + joinPoint.getSignature().getName()
                + " был вызван с аргументами: " + Arrays.toString(joinPoint.getArgs()));
    }
}
