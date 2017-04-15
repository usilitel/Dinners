package ru.lesson.lessons.Dinners.source;

import java.util.HashMap;

/**
 * Список клиентов.
 * Отдельный класс сделан на случай, если придется реализовать новые методы. Например добавление нового клиента или распечатка списка клиентов (в задании таких задач не ставится, поэтому реализации нет).
 *
 * Смысл использования именно HashMap - в том, чтобы:
 *     1) Ускорить поиск нужной информации из списка.
 *        Например, при использовании ArrayList для поиска информации о конкретном клиенте пришлось бы каждый раз перебирать весь список (сложность O(n)), а при использовании HashMap сложность равна O(1).
 *     2) Сразу (на этапе заполнения списка из исходного файла) убрать возможные ошибки/дубли в исходных данных.
 *        Например, если в исходном файле clients.xml указать двух клиентов с одинаковым id - то в списке останется только один из них (т.к. HashMap перезапишет дубли).
 */

public class ClientList extends HashMap<Integer, Client> {

    // добавление нового клиента (реализации нет, т.к. в текущем задании она не требуется)
    public void addClient(Client client) { }

    // распечатка списка клиентов (реализации нет, т.к. в текущем задании она не требуется)
    public void printClientList() { }

}
