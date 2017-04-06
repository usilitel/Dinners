package ru.lesson.lessons.Dinners.source;

/**
 * класс с описанием клиента
 */
public class Client {
    private int id;
    private String name;

    Client(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getClientName() {
        return name;
    }


}
