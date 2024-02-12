package com.kirin.demo.repositories;

import com.kirin.demo.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Склад пищевых продуктов
 */
@Repository
public class ProductRepository {

    /**
     * Список пищевых продуктов
     */
    private List<Product> products;

    /**
     * Счетчик ID
     */
    private AtomicLong id;

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.id = new AtomicLong();
        init();
    }

    /**
     * Заполнение начальных данных
     */
    private void init() {
        addProduct(new Product("Говядина", "Мясо и мясопродукты",
                "12", 2.83, false));
        addProduct(new Product("Помидоры", "Овощи",
                "14", 1.2, false));
        addProduct(new Product("Огурцы", "Овощи",
                "14", 4.5, false));
        addProduct(new Product("Подсолнечное масло", "Растительные масла",
                "14", 3, true));
        addProduct(new Product("Сыр моцарелла", "Молоко и молочные продукты",
                "12", 2.4, true));
        addProduct(new Product("Сливочное масло 82,5%", "Молоко и молочные продукты",
                "10", 1.52, true));
    }

    /**
     * Получение списка всех продуктов на складе
     *
     * @return список продуктов или null, если список пустой
     */
    public List<Product> getAllProducts() {
        if (products.isEmpty()) return null;
        return products;
    }

    /**
     * Добавление продукта на склад
     *
     * @param product продукт с необходимыми данными
     * @return продукт с присвоенным ID
     */
    public Product addProduct(Product product) {
        product.setId(id.incrementAndGet());

        if (product.getShelfLife() == 0) product.setShelfLife(90); // TODO переделать
        product.setInGoodCondition(true);

        products.add(product);
        return product;
    }

}
