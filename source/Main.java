package ru.lesson.lessons.Dinners.source;


import java.util.HashMap;

/**
 * главный класс программы
 *
 */
public class Main {
    //public HashMap<Integer, Dish> menu; // реализация меню без использования отдельного класса
    //public HashMap<Integer, Client> clientList; // реализация списка клиентов без использования отдельного класса
    public Menu menu;
    public ClientList clientList;

    public OrderList orderList;
    private Parser parser;


    public static void main(String[] args) {
        Main dinnersMain = new Main();
    }

    private Main(){
        //menu = new HashMap<Integer, Dish>(); // реализация меню без использования отдельного класса
        //clientList = new HashMap<Integer, Client>(); // реализация списка клиентов без использования отдельного класса
        menu = new Menu();
        clientList = new ClientList();
        orderList = new OrderList(this);
        parser = new Parser(this);

        parser.parseMenu("source_xml/menu.xml"); // заполняем массив menu из файла menu.xml
        parser.parseClients("source_xml/clients.xml"); // заполняем массив clientList из файла clients.xml
        parser.parseOrders("source_xml/orders.xml"); // заполняем массив orders из файла orders.xml

        orderList.printOrders(); // печатаем первый отчет (для отправки заказа в столовую)
        orderList.printOrdersClients(); // печатаем второй отчет (для раздачи заказанных блюд сотрудникам)
    }
}
