package com.group3.lovelacehotel.config;

import com.group3.lovelacehotel.service.AdminService;
import com.group3.lovelacehotel.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final AdminService adminService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(UserService userService, AdminService adminService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.adminService = adminService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Bean
    public DaoAuthenticationProvider authenticationProviderUser() {
        DaoAuthenticationProvider authUser = new DaoAuthenticationProvider();
        authUser.setUserDetailsService(userService);
        authUser.setPasswordEncoder(bCryptPasswordEncoder);
        return authUser;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProviderAdmin() {
        DaoAuthenticationProvider authAdmin = new DaoAuthenticationProvider();
        authAdmin.setUserDetailsService(adminService);
        authAdmin.setPasswordEncoder(bCryptPasswordEncoder);
        return authAdmin;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderUser());
        auth.authenticationProvider(authenticationProviderAdmin());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                        "/registration**",
                        "/reservation**",
                        "/admin-registration**",
                        "/index**",
                        "/about**",
                        "/admin-login**",
                        "/contact**",
                        "/events**",
                        "/js/**",
                        "/fonts/**",
                        "/css/**",
                        "/scss/**",
                        "/images/**").permitAll()
                .antMatchers("/reservation").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/add-room**", "/edit-room**", "/allReservations**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

}


