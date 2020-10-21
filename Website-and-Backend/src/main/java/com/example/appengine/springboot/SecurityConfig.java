package com.example.appengine.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  // Authentication : User --> Roles
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.inMemoryAuthentication().withUser("<>").password("{noop}<>")
        .roles("USER").and().withUser("<>").password("{noop}<>")
        .roles("USER", "ADMIN");

  }

  // Authorization : Role -> Access
//  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/").permitAll();
//    ;
    http
//        .and()
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/Advertisement/getByProductTags", "/Advertisement/getById", "/Advertisement/tagRegex", "/score/**").hasRole("USER")
        .antMatchers("/Advertisement/getAll").hasRole("ADMIN")
        .and().csrf().disable().headers().frameOptions().disable();







//    http.authorizeRequests()
//        .antMatchers("/faq");
        // …are accessible to all users (authenticated or not).
//        .permitAll();
        //These paths…
//        .antMatchers("/**")
//        .hasRole("ADMIN")
//        .antMatchers("/Advertisement/getByProductTags", "/Advertisement/getById", "/Advertisement/tagRegex", "/score/**")
//    .hasRole("USER");


//    http
//        .anonymous().principal("guest").authorities("GUEST_ROLE")
//        .and()
//        .authorizeRequests()
//.antMatchers("/score/**").permitAll()
////        .antMatchers("/**").hasRole("ADMIN")
//.antMatchers("/Advertisement/getAll").hasRole("USER")
////    .antMatchers("scor")
//        .and().formLogin()
//        .loginPage("/login1")
//        .successForwardUrl("/shared/poo!").failureForwardUrl("/shared/pee")
//        .and().logout().logoutSuccessUrl("/mylogout").permitAll()
//    .and().csrf().disable()
//        ;

//    http.authorizeRequests()
//        .antMatchers("/faq")
//     // …are accessible to all users (authenticated or not).
//     .permitAll()
//     //These paths…
//     .antMatchers("/**")
//     // …are only available to users with ADMIN role.
//     .hasRole("ADMIN")
//     // All remaining paths…
//     .anyRequest()
//     // ...require user to at least be authenticated
//     .authenticated()
//     .and()
//     // If user isn't authorised to access a path...
//     .exceptionHandling()
//     // ...redirect them to /403
//     .accessDeniedPage("/403");



     //    http
//        .authorizeRequests()
//        .antMatchers("/**").authenticated()
//        .antMatchers("/Advertisement/getAll").permitAll();
  }

}


