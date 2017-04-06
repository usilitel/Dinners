package ru.lesson.lessons.Dinners.source;

import java.util.ArrayList;

/**
 * общий заказ одного клиента
 */
public class OrderClient {

    ArrayList<String> arrayDishList; // список заказов, разделенный по строкам (для удобства восприятия)

    private int clientId;
    private String dishList;
    private float totalCost;


    OrderClient(int clientId, String dishList, float totalCost){
        this.clientId = clientId;
        this.dishList = dishList;
        this.totalCost = totalCost;
    }

    /**
     * печатаем в консоль строку таблицы с информацией о заказе одного клиента
     */
    public void printOrderClient() {
        String clientName;
        String totalCost;

        clientName = Main.clientList.get(this.clientId).getClientName();
        if(clientName.length()>OrderList.getColumnWidthClientName()){clientName=clientName.substring(0,OrderList.getColumnWidthClientName()-1);}
        totalCost = Float.toString(this.totalCost);

        String dishListTemp;
        arrayDishList = new ArrayList<String>();
        fillArrayDishList(this.dishList, OrderList.getColumnWidthDishList());

        for(int i=0;i<arrayDishList.size();i++){
            dishListTemp=arrayDishList.get(i);


            if(i==0){
                System.out.println(clientName + OrderList.repeatString(" ", OrderList.getColumnWidthClientName() - clientName.length()) + dishListTemp + OrderList.repeatString(" ", OrderList.getColumnWidthDishList() - dishListTemp.length()) + totalCost);
            }
            else{
                System.out.println(OrderList.repeatString(" ", OrderList.getColumnWidthClientName()) + dishListTemp + OrderList.repeatString(" ", OrderList.getColumnWidthDishList() - dishListTemp.length()));
            }
        }
    }


    /**
     * заполняем массив ArrayDishList
     */
    public void fillArrayDishList(String string, int columnWidth) {

        String arrWords[] = string.split(" ");  // Массив слов
        ArrayList<String> arrPhrases = new ArrayList<String>(); // Коллекция подстрок(фраз)

        StringBuilder stringBuffer = new StringBuilder(); // Буфер для накопления фразы
        int cnt = 0;   // Счётчик, чтобы не выйти за пределы 30 символов
        int index = 0; // Индекс элемента в массиве arrWords. Сразу указывает на первый элемент
        int length = arrWords.length; // Общее количество слов (длина массива)

        while (index != length) {  // Пока не дойдём до последнего элемента
            if (cnt + arrWords[index].length() <= columnWidth) { // Если текущая фраза + текущее слово в массиве arrWords не превышает 30
                cnt += arrWords[index].length() + 1;  // То увеличиваем счётчик
                stringBuffer.append(arrWords[index]).append(" ");  // и накапливаем фразу
                index++;   // Переходим на следующее слово
            } else {   // Фраза превысит лимит в 30 символов
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
