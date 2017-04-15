package ru.lesson.lessons.Dinners.source;

/**
 * Класс с описанием одного блюда из меню
 */
public class Dish {
    private int id; // id блюда
    private String name; // название блюда
    private float weight; // вес блюда
    private float price; // цена блюда

    /**
     * конструктор, создает блюдо с заданными характеристиками
     * @param   id - id блюда
     * @param   name - название блюда
     * @param   weight - вес блюда
     * @param   price - цена блюда
     */
    public Dish(int id, String name, float weight, float price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getDishName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
