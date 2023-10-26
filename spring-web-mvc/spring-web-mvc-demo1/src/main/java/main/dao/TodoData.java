package main.dao;

import main.model.TodoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * 模拟持久层的功能：设计成immutable class
 * 1. 对数据进行零时存储 in-memory database
 * 2. 基本4方法：Add, Get, Update, Remove
 */
public class TodoData {

    private static int idValue = 1;
    private final List<TodoItem> items = new ArrayList<>();

    public TodoData() {
        // 初始化基础的测试数据
    }

    // 对外暴露不可修改的List, 以防止数据在外部被损坏, 仅仅可以访问其中的数据
    public List<TodoItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // 可以给参数添加注解 @NonNull 确保传递的参数不为空 !!
    public void addItem(TodoItem item) {
        if (item == null) {
            throw new NullPointerException("Item can not be null");
        }
        item.setId(idValue++);
        items.add(item);
    }

    // 迭代到指定的id位置进行移除
    public void removeItem(int id) {
        ListIterator<TodoItem> itemListIterator = items.listIterator();
        while (itemListIterator.hasNext()) {
            TodoItem item = itemListIterator.next();
            if (item.getId() == id) {
                itemListIterator.remove();
                break;
            }
        }
    }

    public TodoItem getItem(int id) {
        for (TodoItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void updateItem(TodoItem item) {
        // itemListIterator.set(item);
    }
}
