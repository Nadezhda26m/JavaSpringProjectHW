package com.kirin.demo.aspects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor {
    private Long start, time;

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

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        time = System.currentTimeMillis() - start;
        System.out.println("Время работы метода: " + time + " milliseconds");
        System.out.println("=============================================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
