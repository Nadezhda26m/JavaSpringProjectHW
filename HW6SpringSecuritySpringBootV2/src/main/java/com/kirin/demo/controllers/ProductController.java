package com.kirin.demo.controllers;

import com.kirin.demo.models.Product;
import com.kirin.demo.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для работы с страницами склада пищевых продуктов
 */
@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    /**
     * Сервис для работы с продуктами
     */
    private ProductService service;

    /**
     * Получение списка всех продуктов.
     *
     * @param model модель для передачи данных в представление
     * @return представление со списком всех продуктов
     */
    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("products", service.getAllProducts());
        return "products-list";
    }

    /**
     * Создание нового продукта.
     *
     * @param product объект продукта
     * @param model   модель для передачи данных в представление
     * @return представление для создания продукта
     */
    @GetMapping("/add")
    public String addProductForm(Product product, Model model) {
        model.addAttribute("product", product);
        return "product-create";
    }

    /**
     * Получение данных о продукте с формы представления.
     *
     * @param product объект продукта с данными из формы
     * @param model   модель для передачи данных в представление
     * @return перенаправление на страницу со списком продуктов
     */
    @PostMapping("/add")
    public String addProduct(Product product, Model model) {
        service.addProduct(product);
        return "redirect:/products";
    }

    /**
     * Получение справочной информации о группе продуктов.
     *
     * @param group название группы продуктов (из пути)
     * @param model модель для передачи данных в представление
     * @return представление с информацией о группе продуктов
     */
    @GetMapping("/{fg}")
    public String showFoodGroup(@PathVariable("fg") String group, Model model) {
        model.addAttribute("group", group);
        return "food-group-products";
    }
}
