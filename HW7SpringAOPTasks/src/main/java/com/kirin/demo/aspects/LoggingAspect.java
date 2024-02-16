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

/**
 * Аспекты для регистрации действий пользователя.
 * Вывод в консоль информации о вызовах методов классов TaskService и TaskController.
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Выводит информацию о методе класса TaskService перед его вызовом.
     * @param joinPoint вызываемый метод
     */
    @Order(1)
    @Before("execution(* com.kirin.demo.services.TaskService.*(..))")
    public void logAction(JoinPoint joinPoint) {
        System.out.println("Метод " + joinPoint.getSignature().toString() + " был вызван");
    }

    /**
     * Выводит информацию об аргументах метода, отмеченного аннотацией TrackUserAction.
     * Действует после метода logAction().
     * @param joinPoint вызываемый метод
     */
    @Order(2)
    @Before(value = "@annotation(com.kirin.demo.aspects.TrackUserAction)")
    public void logArgsAction(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        System.out.println("Аргументы метода: " + Arrays.toString(arguments));
    }

    /**
     * После AfterReturningAdvice выводит сообщение о завершении работы метода
     * из класса TaskService.
     * @param joinPoint вызываемый метод
     */
    @After("execution(* com.kirin.demo.services.TaskService.*(..))")
    public void logEndAction(JoinPoint joinPoint) {
        System.out.println("Метод " + joinPoint.getSignature().toShortString()
                + " завершил свою работу");
    }

    /**
     * Сразу после завершения метода, который находится в классе TaskService,
     * отмечен аннотацией TrackUserAction и возвращает объект типа Task, выводит
     * в консоль возвращаемую из метода задачу.
     * @param joinPoint вызываемый метод
     * @param returnTask возвращаемый целевым методом экземпляр класса Task
     */
    @AfterReturning(
            value = "@annotation(com.kirin.demo.aspects.TrackUserAction) " +
                    "&& execution(* com.kirin.demo.services.TaskService.*(..))",
            returning = "returnTask")
    public void logReturnValue(JoinPoint joinPoint, Task returnTask) {
        System.out.println("Возвращаемая задача: " + returnTask);
    }

    /**
     * Сразу после завершения метода, который находится в классе TaskService
     * и возвращает список задач, выводит в консоль информацию о joinPoint
     * и количестве найденных задач.
     * @param joinPoint вызываемый метод
     * @param listTasks возвращаемый целевым методом список задач
     */
    @AfterReturning(
            value = "execution(* com.kirin.demo.services.TaskService.*(..))",
            returning = "listTasks")
    public void logCountTasks(JoinPoint joinPoint, List<Task> listTasks) {
        System.out.println("AfterReturning для: " + joinPoint.toLongString());
        System.out.println("Всего найдено задач: " + listTasks.size());
    }

    /**
     * Печатает в консоль визуальные границы выполнения метода, расположенного
     * в классе TaskService. Действует до выполнения BeforeAdvice и после AfterAdvice.
     * @param joinPoint вызываемый метод
     * @return возвращаемое целевым методом значение (без изменений)
     */
    @Around("execution(* com.kirin.demo.services.TaskService.*(..))")
    public Object addBorders(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(">>>");
        Object result = joinPoint.proceed();
        System.out.println(">>>");
        return result;
    }

    /**
     * Перед вызовом любого метода любого класса в пакете controllers выводит в консоль
     * название метода и принимаемые аргументы. Действует после метода preHandle перехватчика.
     * @param joinPoint вызываемый метод
     */
    @Before("execution(* com.kirin.demo.controllers.*.*(..))")
    public void logControllerAction(JoinPoint joinPoint) {
        System.out.println("Метод " + joinPoint.getSignature().getName()
                + " был вызван с аргументами: " + Arrays.toString(joinPoint.getArgs()));
    }
}
