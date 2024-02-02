package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с продуктами
 */
@Service
public class ProductService {

    /**
     * Склад пищевых продуктов
     */
    @Autowired
    private ProductRepository repository;

    /**
     * Создание продукта
     * @param name название продукта
     * @param foodGroup название группы продуктов питания
     * @param consignment партия товара
     * @param count количество товара в кг
     * @param isPieceProduct является ли продукт штучным
     * @return продукт без ID
     */
    public Product createProduct(String name, String foodGroup, String consignment,
                                 double count, boolean isPieceProduct) {
        return new Product(name, foodGroup, consignment, count, isPieceProduct);
    }

    /**
     * Получение списка всех продуктов на складе
     * @return список продуктов или null, если список пустой
     */
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    /**
     * Добавление продукта на склад
     * @param name название продукта
     * @param foodGroup название группы продуктов питания
     * @param consignment партия товара
     * @param count количество товара в кг
     * @param isPieceProduct является ли продукт штучным
     * @return продукт с присвоенным ID
     */
    public Product addProduct(String name, String foodGroup, String consignment,
                              double count, boolean isPieceProduct) {
        Product product = createProduct(name, foodGroup, consignment, count, isPieceProduct);
        return repository.addProduct(product);
    }

    /**
     * Добавление продукта на склад
     * @param product продукт с необходимыми данными
     * @return продукт с присвоенным ID
     */
    public Product addProduct(Product product) {
        return repository.addProduct(product);
    }


    // region TODO доделать

    /**
     * Списание продукта (при порче)
     * @param product
     * @return
     */
    public Product writeOffProduct(Product product) {
        product.setInGoodCondition(false);
        return product;
    }

    public Product takeProduct(Product product, double count) {
        if (isProductInStock(product, count)) {
            product.setCount(product.getCount() - count);
        }
        return product;
    }

    public boolean isProductInStock(Product product, double count) {
        return isGoodProduct(product)
                && product.getCount() >= count;
    }

    private boolean isGoodProduct(Product product) {
        return product.isInGoodCondition()
                && product.getShelfLife() != 0;
    }

    // endregion

}
