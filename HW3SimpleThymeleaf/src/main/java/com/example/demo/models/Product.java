package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Пищевой продукт
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    /**
     * Магический конструктор
     * TODO переделать
     */
    public Product(String name, String foodGroup, String consignment,
                   double count, boolean isPieceProduct) {
        this.name = name;
        this.foodGroup = foodGroup;
        this.consignment = consignment;
        this.count = count;
        this.isPieceProduct = isPieceProduct;
        this.shelfLife = 90; // TODO: годен до - сегодня
        this.isInGoodCondition = true;
    }

    // region Поля

    /**
     * Уникальный идентификатор продукта
     */
    private Long id;

    /**
     * Название продукта
     */
    private String name;

    /**
     * Название группы продуктов питания
     */
    private String foodGroup;

    /**
     * Партия товара
     */
    private String consignment;

    // TODO: дата закупки (из партии/накладной), годен до (bestBeforeDate, из формы или в категории)

    /**
     * Срок годности (дней)
     * TODO добавить формулу
     */
    private int shelfLife;

    /**
     * Количество товара в кг
     */
    private double count;

    /**
     * Является ли продукт штучным (заполняется кол-во и масса 1 шт.), иначе весовым (учет в кг)
     */
    private Boolean isPieceProduct;

    /**
     * Является ли продукт годным к употреблению (не испорчен)
     */
    private boolean isInGoodCondition;

    // endregion Поля

}
