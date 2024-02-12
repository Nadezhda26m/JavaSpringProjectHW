package com.kirin.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Настройки доступа SpringSecurity и создание пользователей
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // ver springsecurity5

    /**
     * Конфигурация защиты Spring Security.<br>
     * На страницу '/** /add' может попасть только пользователь с ролью ADMIN.<br>
     * На страницу '/products/**' - WORKER или ADMIN.<br>
     * На страницу '/products' - все аутентифицированные пользователи.<br>
     * Применяется своя форма для регистрации, доступная по адресу '/login'.<br>
     * URL-адрес для проверки учетных данных в SpringSecurity - '/process-login'.<br>
     * После успешной аутентификации возврат к запрашиваемой ранее странице.<br>
     * При ошибке аутентификации переход на страницу '/login?error'.<br>
     * Выход из системы доступен по адресу '/home-logout', после чего пользователь
     * возвращается на домашнюю страницу.<br>
     * При отказе доступа пользователь перенаправляется на страницу '/errors/403'.
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception исключение безопасности.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**/add").hasRole("ADMIN")
                .antMatchers("/products").authenticated()
                .antMatchers("/products/**").hasAnyRole("WORKER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/process-login")
                // .defaultSuccessUrl("/products")
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutUrl("/home-logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/errors/403");
    }

    /**
     * Бин UserDetailsService с добавленными пользователями.
     *
     * @return объект userDetailsService.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails worker = User.withDefaultPasswordEncoder()
                .username("cook")
                .password("password")
                .roles("WORKER")
                .build();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(user, worker);

        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                // .roles("ADMIN", "WORKER", "USER")
                .build());

        return manager;
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //     auth
    //             .inMemoryAuthentication()
    //             .withUser("user")
    //             .password(encoder.encode("password"))
    //             .roles("USER").and()
    //             .passwordEncoder(encoder);
    //     auth.userDetailsService(userDetailsService());
    // }
}

