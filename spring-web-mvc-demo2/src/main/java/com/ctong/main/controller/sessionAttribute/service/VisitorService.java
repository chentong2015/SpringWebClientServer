package com.ctong.main.controller.sessionAttribute.service;

import com.ctong.main.controller.sessionAttribute.model.Visitor;
import com.ctong.main.controller.sessionAttribute.model.VisitorCount;
import com.ctong.main.controller.sessionAttribute.model.VisitorData;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorService {

    public VisitorCount updateCount(VisitorCount visitorCount) {
        visitorCount.setCount(visitorCount.getCount() + 1);
        return visitorCount;
    }

    // 直接在Session Data中添加一个incoming visitor即可
    public void registerVisitor(VisitorData sessionData, VisitorData incomingVisitor) {
        List<Visitor> visitors = sessionData.getVisitors();
        Visitor newVisitor = new Visitor(incomingVisitor.getCurrentVisitorName(), incomingVisitor.getCurrentVisitorEmail());
        sessionData.setCurrentVisitorName(newVisitor.getName());
        sessionData.setCurrentVisitorEmail(newVisitor.getEmail());
        visitors.add(newVisitor);
    }

    public Long computeDuration(LocalDateTime sessionStartTime) {
        Duration sessionDuration = Duration.between(sessionStartTime, LocalDateTime.now());
        return sessionDuration.getSeconds();
    }

    public String describeCurrentTime(LocalDateTime currentTime) {
        return "current local time is :" + currentTime.getHour() + ":" + currentTime.getMinute();
    }
}
