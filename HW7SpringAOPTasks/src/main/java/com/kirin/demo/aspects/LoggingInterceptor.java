package com.kirin.demo.aspects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Перехватчик HTTP-запросов
 */
public class LoggingInterceptor implements HandlerInterceptor {

    /**
     * Время начала выполнения метода
     */
    private Long start;

    /**
     * Общее время выполнения метода
     */
    private Long time;

    /**
     * Выводит перед выполнением метода информацию о запросе. Фиксирует время
     * начала выполнения метода. Вызывается перед BeforeAdvice.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("=============================================");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Status: " + response.getStatus());
        System.out.println("Обработчик: " + handler);
        start = System.currentTimeMillis();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * Выводит после завершения метода информацию о времени его выполнения.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        time = System.currentTimeMillis() - start;
        System.out.println("Время работы метода: " + time + " milliseconds");
        System.out.println("=============================================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
