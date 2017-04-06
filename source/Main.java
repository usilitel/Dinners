package ru.lesson.lessons.Dinners.source;

/**
 * главный класс программы
 *
 */
public class Main {
    static public Menu menu;
    static public ClientList clientList;
    static public OrderList orderList;


    public static void main(String[] args) {
        Main dinnersMain = new Main();
    }

    Main(){
        menu = new Menu();
        clientList = new ClientList();
        orderList = new OrderList();

        Parser.parseMenu("source_xml/menu.xml"); // заполняем массив menu из файла menu.xml
        Parser.parseClients("source_xml/clients.xml"); // заполняем массив clientList из файла clients.xml
        Parser.parseOrders("source_xml/orders.xml"); // заполняем массив orders из файла orders.xml

        orderList.printOrders(); // печатаем первый отчет (для отправки заказа в столовую)
        orderList.printOrdersClients(); // печатаем второй отчет (я раздачи заказанных блюд сотрудникам)
    }
}
