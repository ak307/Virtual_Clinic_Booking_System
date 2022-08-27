package com.vclinic.virtual_clinic_booking_system.security.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
@Order(3)
public class UserWebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/login", "/css/**", "/imgs/**", "/confirmEmailText").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/confirmedToken").permitAll()
                .antMatchers("/home").permitAll()// permit home page
                .antMatchers("/ourDoctor").permitAll() // permit our doctor page
                .antMatchers("/contactUs").permitAll() // permit contact us page
                .antMatchers("/contactUs/**").permitAll() // permit sending message to us in contact us page

//                .antMatchers("/admin/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true).permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }
}
