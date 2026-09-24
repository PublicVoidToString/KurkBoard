package com.kurkboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        var apiMatcher =
                PathPatternRequestMatcher.pathPattern("/api/**");

        var adminMatcher =
                PathPatternRequestMatcher.pathPattern("/admin/**");

        var loginEntryPoint =
                new LoginUrlAuthenticationEntryPoint("/admin/login");

        var basicEntryPoint =
                new BasicAuthenticationEntryPoint();

        basicEntryPoint.setRealmName("KurkBoard API");

        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/admin/login",
                                "/admin/403",
                                "/error",
                                "/admin/components"
                        )
                        .permitAll()

                        .requestMatchers("/api/admin/**")
                        .hasRole("SUPER_ADMIN")

                        .requestMatchers("/api/**")
                        .hasAnyRole("ADMIN", "SUPER_ADMIN")

                        .requestMatchers("/admin/**")
                        .hasAnyRole("ADMIN", "SUPER_ADMIN")

                        .anyRequest()
                        .permitAll()
                )

                .formLogin(form -> form
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin", true)
                        .permitAll()
                )

                .httpBasic(httpBasic -> {
                })

                .exceptionHandling(exception -> exception
                        .defaultAuthenticationEntryPointFor(
                                basicEntryPoint,
                                apiMatcher
                        )
                        .defaultAuthenticationEntryPointFor(
                                loginEntryPoint,
                                adminMatcher
                        )
                        .accessDeniedPage("/admin/403")
                )

                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}