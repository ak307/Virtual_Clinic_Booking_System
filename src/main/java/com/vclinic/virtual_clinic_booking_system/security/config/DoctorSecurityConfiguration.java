package com.vclinic.virtual_clinic_booking_system.security.config;//package com.vclinic.virtual_clinic_booking_system.security.config;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(2)
@EnableWebSecurity
@AllArgsConstructor
public class DoctorSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/doctor/**")
                .authorizeRequests()
                .antMatchers("/doctor/login").permitAll()
                .antMatchers("/doctor/dashboard").hasAnyAuthority("DOCTOR")
                .antMatchers("/doctor/dashboard/**").hasAnyAuthority("DOCTOR")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/doctor/login").permitAll()
                .defaultSuccessUrl("/doctor/dashboard", true)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/doctor/logout"))
                .logoutSuccessUrl("/doctor/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }




}
