package ru.lesson.lessons.Dinners.source;

/**
 * Класс с описанием одного блюда из меню
 */
public class Dish {
    private int id;
    private String name;
    private float weight;
    private float price;

    Dish(int id, String name, float weight, float price) {
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
