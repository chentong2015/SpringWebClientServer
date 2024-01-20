package com.ctong.main.controller;

import com.ctong.main.controller.sessionAttribute.model.Visitor;
import com.ctong.main.controller.sessionAttribute.model.VisitorCount;
import com.ctong.main.controller.sessionAttribute.model.VisitorData;
import com.ctong.main.controller.sessionAttribute.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 在浏览器中刷新(关闭)会构建全新的Session, Chrome需要移除Settings data
@Controller
@SessionAttributes(names = {"visitorData", "visitorCount"})
@RequestMapping("/visitorRegister")
public class BaseRequestController {

    @Autowired
    public VisitorService visitorService;

    // When request reached one of the handler methods in the com.ctong.springboot.controller
    // 根据Class Level声明的SessionAttribute, Spring将会从HTTP Session Object中找对应的属性和值 !!
    // 如果没有找到，则会使用下面的方法来(填充)设置Session中属性的值
    @ModelAttribute("visitorData")
    public VisitorData addVisitorData() {
        List<Visitor> visitors = new ArrayList<>();
        // Create stateless instance of VisitorData
        VisitorData visitorData = new VisitorData(null, null, visitors);
        return visitorData;
    }

    @ModelAttribute("visitorCount")
    public VisitorCount countVisitors() {
        return new VisitorCount(0);
    }

    // 初始提供的给view页面的model中数据是空的, 绑定View Form: modelAttribute="visitorStats"
    @RequestMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("sessionRequestAttributeHome", "visitorStats", new VisitorData());
    }

    // 1. currentVisitor绑定前端view提交的表单数据
    // 2. use session object to retrieve data that gets stored in the session by Spring
    // 3. use sessionStatus to reset local Session attributes 这里只正对于局部的session attributes
    @RequestMapping(value = "/visitor", method = RequestMethod.POST)
    public String getVisitors(@ModelAttribute("visitor") VisitorData currentVisitor, HttpSession session, SessionStatus sessionStatus) {
        VisitorData visitorDataFromSession = (VisitorData) session.getAttribute("visitorData");
        VisitorCount visitorCount = (VisitorCount) session.getAttribute("visitorCount");
        visitorService.registerVisitor(visitorDataFromSession, currentVisitor);
        visitorService.updateCount(visitorCount);
        if (visitorCount.getCount() == 5) {
            sessionStatus.setComplete(); // set "visitorData" & "visitorCount"
        }
        return "sessionRequestAttributeResult";
    }

    // 直接绑定(全局的)HttpSession和HttpServletRequest中的Attributes !!
    // session.invalidate(); invalidate the session attribute, set sessionStartTime to null
    @RequestMapping("/testAttribute")
    public String testAttribute(@SessionAttribute(name = "sessionStartTime") LocalDateTime startTime,
                                @RequestAttribute(name = "currentTime") LocalDateTime clockTime,
                                HttpSession session, Model model) {
        Long currentSessionDuration = visitorService.computeDuration(startTime);
        model.addAttribute("sessionId", session.getId());
        session.invalidate();
        model.addAttribute("timeHeading", visitorService.describeCurrentTime(clockTime));
        return "testAttribute";
    }
}
