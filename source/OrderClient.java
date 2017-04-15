package ru.lesson.lessons.Dinners.source;

import java.util.ArrayList;

/**
 * общий заказ одного клиента ()
 */
public class OrderClient {

    private int clientId; // id клиента
    private String dishList; // список блюд
    private float totalCost; // общая стоимость заказа

    private OrderList orderList; // ссылка на общий список заказов
    private Main main; // ссылка на главный объект программы

    private ArrayList<String> arrayDishList; // список заказов, разделенный по строкам (для удобства восприятия)


    /**
     * конструктор, создает заказ клиента
     * @param   clientId - id клиента
     * @param   dishList - список блюд
     * @param   totalCost - общая стоимость заказа
     * @param   orderList - ссылка на общий список заказов
     * @param   main - ссылка на главный объект программы
     */
    public OrderClient(int clientId, String dishList, float totalCost, OrderList orderList, Main main){
        this.clientId = clientId;
        this.dishList = dishList;
        this.totalCost = totalCost;
        this.orderList = orderList;
        this.main = main;
    }

    /**
     * печатаем в консоль строку таблицы с информацией о заказе одного клиента
     */
    public void printOrderClient() {
        String clientName;
        String totalCost;

        clientName = this.main.clientList.get(this.clientId).getClientName();
        if(clientName.length()>orderList.getColumnWidthClientName()){clientName=clientName.substring(0,orderList.getColumnWidthClientName()-1);}
        totalCost = Float.toString(this.totalCost);

        String dishListTemp;
        arrayDishList = new ArrayList<String>();
        fillArrayDishList(this.dishList, orderList.getColumnWidthDishList());

        for(int i=0;i<arrayDishList.size();i++){
            dishListTemp=arrayDishList.get(i);


            if(i==0){
                System.out.println(clientName + SecondaryUtils.repeatString(" ", orderList.getColumnWidthClientName() - clientName.length()) + dishListTemp + SecondaryUtils.repeatString(" ", orderList.getColumnWidthDishList() - dishListTemp.length()) + totalCost);
            }
            else{
                System.out.println(SecondaryUtils.repeatString(" ", orderList.getColumnWidthClientName()) + dishListTemp + SecondaryUtils.repeatString(" ", orderList.getColumnWidthDishList() - dishListTemp.length()));
            }
        }
    }


    /**
     * заполняем массив ArrayDishList
     * @param   string - строка со списком блюд, которую нужно разбить на несколько строк
     * @param   columnWidth - ширина колонки
     */
    public void fillArrayDishList(String string, int columnWidth) {

        String arrWords[] = string.split(" ");  // Массив слов
        ArrayList<String> arrPhrases = new ArrayList<String>(); // Коллекция подстрок(фраз)

        StringBuilder stringBuffer = new StringBuilder(); // Буфер для накопления фразы
        int cnt = 0;   // Счётчик, чтобы не выйти за пределы columnWidth символов
        int index = 0; // Индекс элемента в массиве arrWords. Сразу указывает на первый элемент
        int length = arrWords.length; // Общее количество слов (длина массива)

        while (index != length) {  // Пока не дойдём до последнего элемента
            if (cnt + arrWords[index].length() <= columnWidth) { // Если текущая фраза + текущее слово в массиве arrWords не превышает columnWidth
                cnt += arrWords[index].length() + 1;  // То увеличиваем счётчик
                stringBuffer.append(arrWords[index]).append(" ");  // и накапливаем фразу
                index++;   // Переходим на следующее слово
            } else {   // Фраза превысит лимит в columnWidth символов
                arrPhrases.add(stringBuffer.toString());   // Добавляем фразу в коллекцию
                stringBuffer = new StringBuilder();
                cnt = 0;                                   // Обнуляем счётчик
            }

        }

        if (stringBuffer.length() > 0) {
            arrPhrases.add(stringBuffer.toString());       // Забираем "остатки"
        }

        for (String elem : arrPhrases) {
            arrayDishList.add(elem);
        }
    }






    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDishList() {
        return dishList;
    }

    public void setDishList(String dishList) {
        this.dishList = dishList;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }


}
