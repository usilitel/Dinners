package ru.lesson.lessons.Dinners.source;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Разбираем исходные xml-файлы и записываем их содержимое в массивы
 */
public class Parser {
    private Main main; // ссылка на главный объект программы

    public Parser(Main main){
        this.main = main;
    }

    /**
     * заполняем массив menu
     * @param fileName - название исходного файла
     */
    public void parseMenu(String fileName) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(fileName);
            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            // Просматриваем все подэлементы корневого
            NodeList dishes = root.getChildNodes();
            for (int i = 0; i < dishes.getLength(); i++) {
                Node dish = dishes.item(i);
                // Если нода не текст, то заходим внутрь
                if (dish.getNodeType() != Node.TEXT_NODE) {
                    int dishId = 0;
                    String dishName = "";
                    float dishWeight = 0;
                    float dishPrice = 0;

                    NodeList dishProps = dish.getChildNodes();
                    for(int j = 0; j < dishProps.getLength(); j++) {
                        Node dishProp = dishProps.item(j);
                        if (dishProp.getNodeName() == "id") { dishId = Integer.parseInt(dishProp.getChildNodes().item(0).getTextContent()); }
                        if (dishProp.getNodeName() == "Name") { dishName = (dishProp.getChildNodes().item(0).getTextContent()); }
                        if (dishProp.getNodeName() == "Weight") { dishWeight = Float.parseFloat(dishProp.getChildNodes().item(0).getTextContent()); }
                        if (dishProp.getNodeName() == "Price") { dishPrice = Float.parseFloat(dishProp.getChildNodes().item(0).getTextContent()); }
                    }
                    this.main.menu.put(dishId, new Dish(dishId, dishName, dishWeight, dishPrice));
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        // массив menu заполнен
    }

    /**
     * заполняем массив clientList
     * @param fileName - название исходного файла
     */
    public void parseClients(String fileName) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(fileName);
            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            // Просматриваем все подэлементы корневого
            NodeList clients = root.getChildNodes();
            for (int i = 0; i < clients.getLength(); i++) {
                Node dish = clients.item(i);
                // Если нода не текст, то заходим внутрь
                if (dish.getNodeType() != Node.TEXT_NODE) {
                    int clientId = 0;
                    String clientName = "";

                    NodeList dishProps = dish.getChildNodes();
                    for(int j = 0; j < dishProps.getLength(); j++) {
                        Node dishProp = dishProps.item(j);
                        if (dishProp.getNodeName() == "id") { clientId = Integer.parseInt(dishProp.getChildNodes().item(0).getTextContent()); }
                        if (dishProp.getNodeName() == "Name") { clientName = (dishProp.getChildNodes().item(0).getTextContent()); }
                    }
                    this.main.clientList.put(clientId, new Client(clientId, clientName));
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        // массив clientList заполнен
    }


    /**
     * заполняем массив orders
     * @param fileName - название исходного файла
     */
    public void parseOrders(String fileName) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(fileName);
            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            // Просматриваем все подэлементы корневого
            NodeList orders = root.getChildNodes();
            for (int i = 0; i < orders.getLength(); i++) {
                Node order = orders.item(i);
                // Если нода не текст, то заходим внутрь
                if (order.getNodeType() != Node.TEXT_NODE) {
                    int clientId = 0;
                    int dishId = 0;
                    NodeList orderProps = order.getChildNodes();
                    for(int j = 0; j < orderProps.getLength(); j++) {
                        Node orderProp = orderProps.item(j);
                        if (orderProp.getNodeName() == "ClientId") { clientId = Integer.parseInt(orderProp.getChildNodes().item(0).getTextContent()); }
                        if (orderProp.getNodeName() == "DishId") { dishId = Integer.parseInt(orderProp.getChildNodes().item(0).getTextContent()); }
                    }
                    this.main.orderList.add(new Order(clientId, dishId)); // добавляем блюдо в заказ клиента
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        // массив orders заполнен
    }
}
