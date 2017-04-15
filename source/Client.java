package ru.lesson.lessons.Dinners.source;

/**
 * класс с описанием клиента
 */
public class Client {
    private int id; // id клиента
    private String name; // ФИО клиента


    /**
     * конструктор
     * @param   id - id клиента
     * @param   name - ФИО клиента
     */
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getClientName() {
        return name;
    }


}
