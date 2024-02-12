package com.kirin.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер приложения
 */
@Controller
public class AppController {

    /**
     * Страница отказа в доступе. В параметрах модели передается ссылка на страницу,
     * с которой было перенаправление.
     *
     * @return представление отказа в доступе
     */
    @GetMapping("/errors/403")
    public String accessDenied(HttpServletRequest request, Model model) {
        String lastPage = request.getHeader("Referer");
        model.addAttribute("lastPage", lastPage);
        return "access-denied";
    }

    /**
     * Аутентификация пользователя.
     *
     * @return представление аутентификации
     */
    @GetMapping("/login")
    public String auth() {
        return "login-page";
    }

}
