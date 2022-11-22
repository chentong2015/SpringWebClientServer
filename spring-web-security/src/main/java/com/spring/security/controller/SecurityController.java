package com.spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// SecurityExpressionRoot 声明访问的权限
// @PreAuthorize("permitAll()")
//   > allows (permits) any user's (all) session to be authorized to execute that method.
// @PreAuthorize("hasAuthority('generator::read')")
//   > restrict your whole class or application with a certain permission
//
// Web Security 所有可能的权限
// - hasRole, hasAnyRole
// - hasAuthority, hasAnyAuthority
// - permitAll, denyAll
// - isAnonymous, isRememberMe, isAuthenticated, isFullyAuthenticated
// - principal, authentication
// - hasPermission
@RestController
@RequestMapping("/v1/test/security")
public class SecurityController {

    @PreAuthorize("permitAll()")
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public boolean login() {
        return true;
    }
}
