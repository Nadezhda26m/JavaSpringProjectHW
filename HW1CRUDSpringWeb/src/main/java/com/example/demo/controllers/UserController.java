package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Контроллер для работы с сущностями Use.
 */
@Controller
public class UserController {

    /**
     * Объект сервиса для работы с пользователями (User).
     */
    private final UserService userService;

    /**
     * Конструктор класса.
     * @param userService объект сервиса для работы с пользователями
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение списка всех пользователей.
     * @param model модель для передачи данных в представление
     * @return представление со списком пользователей
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    /**
     * Создание нового пользователя.
     * @param user объект пользователя
     * @return представление для создания пользователя
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    /**
     * Получение данных о новом пользователе с формы представления.
     * @param user объект пользователя с заполненными полями (пустые поля не принимаются)
     * @param httpResponse объект HttpServlet для возврата информации клиенту.
     * @return перенаправление на страницу со списком пользователей и код состояния CREATED,
     * если все поля были заполнены. Код состояния NO_CONTENT, если есть незаполненные поля
     * @throws IOException если возникает ошибка ввода/вывода
     */
    @PostMapping("/user-create")
    public ResponseEntity<User> createUser(
            User user, HttpServletResponse httpResponse) throws IOException {
        if (userService.isCorrectUser(user)) {
            userService.saveUser(user);
            httpResponse.sendRedirect("/users");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление пользователя.
     * @param id уникальный идентификатор пользователя
     * @return перенаправление на страницу со списком пользователей
     */
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Изменение данных пользователя.
     * @param id уникальный идентификатор пользователя, получаемый из пути
     * @param model модель для передачи данных в представление
     * @return представление для изменения данных
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    /**
     * Получение измененных данных пользователя с формы представления.
     * @param user объект пользователя с заполненными полями (пустые поля не принимаются)
     * @param httpResponse объект HttpServlet для возврата информации клиенту.
     * @return перенаправление на страницу со списком пользователей и код состояния OK,
     * если все поля были заполнены. Код состояния NO_CONTENT, если есть незаполненные поля
     * @throws IOException если возникает ошибка ввода/вывода
     */
    @PostMapping("/user-update")
    public ResponseEntity<User> updateUser(
            @ModelAttribute("user") User user, HttpServletResponse httpResponse)
            throws IOException {
        if (userService.isCorrectUser(user)) {
            userService.updateUser(user);
            httpResponse.sendRedirect("/users");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
