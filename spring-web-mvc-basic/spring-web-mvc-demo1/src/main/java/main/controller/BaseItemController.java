package main.controller;

import main.model.TodoItem;
import main.util.Mappings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class BaseItemController {

    /**
     * 1. 合并添加item和编辑item的界面：当ID不提供的时候, 作为添加新的item界面, 创建默认的object
     * 2. 提供ID默认值(提供string), Spring会默认转成int, -1表示不存在这个item
     */
    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
        // TodoItem todoItem = todoItemService.getItem(id);
        TodoItem todoItem = new TodoItem("", "", LocalDate.now());
        model.addAttribute("todoItem", todoItem);
        return "addItem";
    }

    /**
     * POST Redirect GET Pattern 模式
     * 方法的参数是从页面form获取的object对象, view所绑定的变量名称必须和object的attributes一致 !!
     */
    @PostMapping(Mappings.ADD_ITEM) // 从添加item的页面发送POST请求
    public String processItem(@ModelAttribute("todoItem") TodoItem todoItem) {
        // 提交请求之后, 使用注入的Service完成数据背后的更新
        // if(todoItem.getId() == 0) {
        //    todoItemService.addItem(todoItem);
        // } else {
        //    todoItemService.updateItem(todoItem);
        // }
        // 重定向到指定的页面, 显示更新的信息
        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping("deleteItem")
    public String deleteItem(@RequestParam int id) {
        // todoItemService.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }
}
