package ru.lesson.lessons.Dinners.source;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Общий список заказов (один элемент = одно блюдо одного клиента)
 */
public class OrderList extends ArrayList<Order> implements OrderListInterface {

    private HashMap<Integer, Integer> orderListTotal; // итоговый список заказов
    private HashMap<Integer, OrderClient> orderListClients; // список заказов по клиентам
    private Main main; // ссылка на главный объект программы


    /**
     * задаем ширину колонок первого отчета (для отправки заказа в столовую)
     */
    private int columnWidthDishName = 35;
    private int columnWidthDishCount = 13;
    private int columnWidthDishCost = 10;

    /**
     * задаем ширину колонок второго отчета (для раздачи заказанных блюд сотрудникам)
     */
    private int columnWidthClientName = 20;
    private int columnWidthDishList = 40;
    private int columnWidthOrderCost = 10;



    public int getColumnWidthClientName() {
        return columnWidthClientName;
    }

    public int getColumnWidthDishList() {
        return columnWidthDishList;
    }

    public int getColumnWidthOrderCost() {
        return columnWidthOrderCost;
    }


    public OrderList(Main main){
        this.main = main;
    }

    /**
     * печатаем итоговый список заказов
     */
    public void printOrders() {
        fillOrderListTotal(); // сначала заполняем итоговый список заказов
        float totalCost=0;


        System.out.println("Общая таблица с заказами:");
        System.out.println("название блюда" + SecondaryUtils.repeatString(" ", columnWidthDishName - 14) + "количество" + SecondaryUtils.repeatString(" ", columnWidthDishCount - 10) + "стоимость");
        System.out.println(SecondaryUtils.repeatString("-", columnWidthDishName - 1) + " " + SecondaryUtils.repeatString("-", columnWidthDishCount - 1) + " " + SecondaryUtils.repeatString("-", columnWidthDishCost - 1));
        for (Integer dishId : orderListTotal.keySet()){
            String dishName = this.main.menu.get(dishId).getDishName();
            if (dishName.length()>columnWidthDishName){dishName=dishName.substring(0,columnWidthDishName-1);}

            Integer dishCountTotal = orderListTotal.get(dishId);
            float dishPrice = this.main.menu.get(dishId).getPrice();
            System.out.println(dishName + SecondaryUtils.repeatString(" ", columnWidthDishName - dishName.length()) + dishCountTotal + SecondaryUtils.repeatString(" ", columnWidthDishCount - SecondaryUtils.getCountsOfDigits(dishCountTotal)) + dishCountTotal * dishPrice);
            totalCost+= dishCountTotal * dishPrice;
        }
        System.out.println("--------");
        System.out.println("Итоговая стоимость:\t" + totalCost);
        System.out.println("");
    }

    /**
     * заполняем итоговый список заказов
     */
    public void fillOrderListTotal() {
        orderListTotal = new HashMap<Integer, Integer>();
        int count=0;

        for (Order order : this) { // перебираем все заказы
            try{
                count = orderListTotal.get(order.getDishId()); // количество уже добавленных заказов по данному DishId
            }
            catch (NullPointerException e){
                count = 0;
            }
            orderListTotal.put(order.getDishId(),++count); // добавляем заказ по данному DishId
        }
    }




    /**
     * печатаем итоговый список заказов по клиентам
     */
    public void printOrdersClients() {
        fillOrderListClients(); // сначала заполняем список заказов по клиентам
        OrderClient orderClient;

        System.out.println("Таблица с заказами по клиентам:");
        System.out.println("заказчик" + SecondaryUtils.repeatString(" ", getColumnWidthClientName() - 8) + "заказ" + SecondaryUtils.repeatString(" ", getColumnWidthDishList() - 5) + "стоимость");
        System.out.println(SecondaryUtils.repeatString("-", getColumnWidthClientName() - 1) + " " + SecondaryUtils.repeatString("-", getColumnWidthDishList() - 1) + " " + SecondaryUtils.repeatString("-", getColumnWidthOrderCost() - 1));

        for (Integer clientId : orderListClients.keySet()){ // распечатываем каждую строку таблицы отдельно
            orderClient = orderListClients.get(clientId);
            orderClient.printOrderClient();


        }
        System.out.println("--------");
    }


    /**
     * заполняем список заказов по клиентам (orderListClients) из общего списка заказов (this, заполнен ранее методом Parser.parseOrders)
     */
    public void fillOrderListClients() {
        orderListClients = new HashMap<Integer, OrderClient>();
        String dishList = "";
        Float totalCost = 0f;

        for (Order order : this) { // перебираем все заказы
            try{
                dishList = orderListClients.get(order.getClientId()).getDishList(); // список уже добавленных заказов по данному ClientId
                totalCost = orderListClients.get(order.getClientId()).getTotalCost(); // стоимость уже добавленных заказов по данному ClientId
            }
            catch (NullPointerException e){
                dishList = "";
                totalCost = 0f;
            }

            // формируем строку со списком блюд
            if(dishList==""){
                dishList=this.main.menu.get(order.getDishId()).getDishName(); // если в списке заказов по клиентам еще нет такого клиента - то  добавляем первое блюдо в заказ клиента
            }
            else{
                dishList+=", " + this.main.menu.get(order.getDishId()).getDishName(); // добавляем следующее блюдо в заказ клиента
            }

            totalCost+=this.main.menu.get(order.getDishId()).getPrice();
            OrderClient orderClient = new OrderClient(order.getClientId(), dishList, totalCost, this, this.main);
            orderListClients.put(order.getClientId(), orderClient); // добавляем заказ по данному DishId. Если такой клиент в списке уже был - то его заказ перезапишется.
        }
        // список orderListClients заполнен
    }






}
