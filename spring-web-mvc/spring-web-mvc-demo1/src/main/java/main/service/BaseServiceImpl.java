package main.service;

import org.springframework.stereotype.Service;

/**
 * @Service: 沟通Controller层和Dao持久层(repository)
 * 1. For the com.ctong.springboot.service layer, holds the business logic / process data 在服务层，提供数据处理和业务逻辑
 * 2. Controller gets data or calculation result from Service 使用构造器注入Service到Controller中
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Override
    public String getMessage(String username) {
        return "Hello: " + username;
    }
}
