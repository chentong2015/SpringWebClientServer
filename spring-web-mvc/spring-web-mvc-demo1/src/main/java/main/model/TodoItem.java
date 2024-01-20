package main.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TodoItem {

    private int id;
    private String title;
    private String details;
    private LocalDate deadline;

    public TodoItem(String title, String details, LocalDate deadline) {
        this.title = title;
        this.details = details;
        this.deadline = deadline;
    }

    /**
     * 选择Intellij default
     * Accept subclasses as parameter to equals method 接受他的子类型作为方法的参数
     * 根据类型的指定属性来生成重写的equals() & hashCode()方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoItem)) {
            return false;
        }
        TodoItem todoItem = (TodoItem) o; // Subclass也同样适用
        return id == todoItem.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
