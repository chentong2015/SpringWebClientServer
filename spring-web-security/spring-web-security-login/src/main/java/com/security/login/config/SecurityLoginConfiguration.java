package com.security.login.config;

import com.security.login.handler.MyUrlAuthSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

// TODO. Login三种登录方式: 可以同时提供不同的选项
// .httpBasic()   使用常规带有用户身份的Http请求
// .fromLogin()   使用Spring Security提供的Login页面表单
// .oauth2Login() 使用第三方身份进行认证和登录
@Configuration
@EnableWebSecurity
public class SecurityLoginConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 每个Matchers匹配都需要设置对应的授权方式
                .antMatchers("/login").authenticated()
                .antMatchers("/home").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .anyRequest().authenticated()
                // 提供用于授权登陆的方式 + 访问的页面 + 登陆成功后的页面
                .and().formLogin()
                .loginProcessingUrl("/login")
                // 登陆成功之后需要做redirecting页面跳转
                .defaultSuccessUrl("/main.html", true)
                .successHandler(myAuthenticationSuccessHandler());
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyUrlAuthSuccessHandler();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("user1Pass"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
