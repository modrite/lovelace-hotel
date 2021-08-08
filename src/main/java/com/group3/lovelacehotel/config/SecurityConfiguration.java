package com.group3.lovelacehotel.config;

import com.group3.lovelacehotel.service.AdminService;
import com.group3.lovelacehotel.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
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
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setUserDetailsService(adminService);
        auth.setPasswordEncoder(bCryptPasswordEncoder);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/registration**",
                "/admin-registration**",
                "/index**",
                "/rooms**",
                "/about**",
                "/admin-login**",
                "/contact**",
                "/events**",
                "/js/**",
                "/fonts/**",
                "/css/**",
                "/scss/**",
                "/images/**",
                "/").permitAll()
                .antMatchers( "/reservation").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers("/add-room**", "/edit-room**", "/allReservations**").hasAnyRole( "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/admin-login")
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


