package com.project.gogiJangin.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/login.do").permitAll()
                        .requestMatchers(
                                "/admin/css/**",
                                "/admin/js/**",
                                "/admin/images/**",
                                "/customer/font/**",
                                "/customer/css/**",
                                "/customer/js/**",
                                "/customer/images/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").authenticated()
                        .anyRequest().permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/admin/login.do")
                        .loginProcessingUrl("/admin/login.do")
                        .defaultSuccessUrl("/admin/popup/list.do")
                        .failureUrl("/admin/login.do?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/admin/logout.do")
                        .logoutSuccessUrl("/admin/login.do")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
