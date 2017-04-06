package ru.lesson.lessons.Dinners.source;


/**
 * Класс с описанием одного заказа (1 заказ = 1 блюдо для одного клиента)
 */
public class Order {

    private Integer clientId;
    private Integer dishId;


    Order(int clientId, int dishId) {
        this.clientId = clientId;
        this.dishId = dishId;
    }


    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

}
