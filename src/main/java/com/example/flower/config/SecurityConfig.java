package com.example.flower.config;

import com.example.flower.service.UserService;
import com.example.flower.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter; // 自动注入 JwtAuthenticationFilter

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置自定义用户详情服务
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String openid) throws UsernameNotFoundException {
                com.example.flower.entity.User user = userService.findByOpenid(openid);
                if (user == null) {
                    throw new UsernameNotFoundException("用户不存在");
                }
                return User.builder()
                        .username(user.getOpenid())
                        .password(user.getPassword() != null ? user.getPassword() : "")
                        .roles(user.getRole())
                        .build();
            }
        }).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 无状态
                .and()
                .authorizeRequests()
                // 允许 Swagger 相关路径免认证访问
                .antMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",  // 允许 OpenAPI 的文档路径
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/swagger-ui.html").permitAll()
                .antMatchers("/api/login", "/api/register", "/api-docs/**").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN") // 只有 ADMIN 角色可以访问
                .anyRequest().authenticated(); // 其他所有请求需要认证

        // 通过自动注入的 jwtAuthenticationFilter 添加 JWT 过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // 配置 AuthenticationManager Bean
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
