package ru.lesson.lessons.Dinners.source;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Список заказов
 */
public class OrderList extends ArrayList<Order> implements OrderListInterface {

    HashMap<Integer, Integer> orderListTotal; // итоговый список заказов
    HashMap<Integer, OrderClient> orderListClients; // список заказов по клиентам

    /**
     * задаем ширину колонок таблицы
     */
    private int columnWidthDishName = 35;
    private int columnWidthDishCount = 13;
    private int columnWidthDishCost = 10;

    static private int columnWidthClientName = 20;
    static private int columnWidthDishList = 40;
    static private int columnWidthOrderCost = 10;



    public static int getColumnWidthClientName() {
        return columnWidthClientName;
    }

    public static int getColumnWidthDishList() {
        return columnWidthDishList;
    }

    public static int getColumnWidthOrderCost() {
        return columnWidthOrderCost;
    }



    /**
     * печатаем итоговый список заказов
     */
    public void printOrders() {
        fillOrderListTotal();
        float totalCost=0;


        System.out.println("Общая таблица с заказами:");
        System.out.println("название блюда" + repeatString(" ", columnWidthDishName-14) + "количество" + repeatString(" ", columnWidthDishCount-10) + "стоимость");
        System.out.println(repeatString("-", columnWidthDishName-1) + " " + repeatString("-", columnWidthDishCount-1) + " " + repeatString("-", columnWidthDishCost-1));
        for (Integer dishId : orderListTotal.keySet()){
            String dishName = Main.menu.get(dishId).getDishName();
            if (dishName.length()>columnWidthDishName){dishName=dishName.substring(0,columnWidthDishName-1);}

            Integer dishCountTotal = orderListTotal.get(dishId);
            float dishPrice = Main.menu.get(dishId).getPrice();
            System.out.println(dishName + repeatString(" ", columnWidthDishName-dishName.length()) + dishCountTotal + repeatString(" ", columnWidthDishCount-getCountsOfDigits(dishCountTotal)) + dishCountTotal * dishPrice);
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
        fillOrderListClients();
        OrderClient orderClient;

        System.out.println("Таблица с заказами по клиентам:");
        System.out.println("заказчик" + repeatString(" ", getColumnWidthClientName() - 8) + "заказ" + repeatString(" ", getColumnWidthDishList() - 5) + "стоимость");
        System.out.println(repeatString("-", getColumnWidthClientName() - 1) + " " + repeatString("-", getColumnWidthDishList() - 1) + " " + repeatString("-", getColumnWidthOrderCost() - 1));

        for (Integer clientId : orderListClients.keySet()){
            orderClient = orderListClients.get(clientId);
            orderClient.printOrderClient();
        }
        System.out.println("--------");
    }


    /**
     * заполняем список заказов по клиентам
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

            if(dishList==""){dishList=Main.menu.get(order.getDishId()).getDishName();}
            else{dishList+=", " + Main.menu.get(order.getDishId()).getDishName();}

            totalCost+=Main.menu.get(order.getDishId()).getPrice();
            OrderClient orderClient = new OrderClient(order.getClientId(), dishList, totalCost);
            orderListClients.put(order.getClientId(), orderClient); // добавляем заказ по данному DishId
        }
        // список orderListClients заполнен
    }




    /**
     * повторяем строку n раз
     */
    public static String repeatString(String symbol, int count) {
        String stringOut="";
        for(int i=0;i<count;i++){
            stringOut+=symbol;
        }
        return stringOut;
    }


    /**
     * количество цифр в числе
     */
    public static int getCountsOfDigits(long number) {
        return(number == 0) ? 1 : (int) Math.ceil(Math.log10(Math.abs(number) + 0.5));
    }

}
