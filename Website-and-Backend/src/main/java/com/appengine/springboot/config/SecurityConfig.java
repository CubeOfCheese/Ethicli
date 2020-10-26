package com.appengine.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // Authentication : User --> Roles
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("<username>")
        .password("{noop}<password>")
        .roles("USER")
        .and()
        .withUser("<admin-username>")
        .password("{noop}<admin-password>")
        .roles("USER", "ADMIN");
  }

  // Authorization : Role -> Access
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(
            "/Advertisement/getByProductTags",
            "/Advertisement/getById",
            "/Advertisement/tagRegex",
            "/feedback",
            "/score/**")
        .hasRole("USER")
        .antMatchers(
            "/Advertisement/getAll",
            "/Advertisement/add",
            "/Advertisement/addAll",
            "/Advertisement/update",
            "/Advertisement/delete")
        .hasRole("ADMIN")
        .and()
        .csrf()
        .disable()
        .headers()
        .frameOptions()
        .disable();
  }
}