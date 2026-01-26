package net.jmp.spring.boot.react.learning.configuration;

/*
 * (#)SecurityConfigurer.java   0.1.0   12/27/2025
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2025 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

/// The security configuration
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(AuthProperties.class)
public class SecurityConfigurer {
    /// The authentication properties
    private final AuthProperties authProperties;

    /// The constructor
    ///
    /// @param  authProperties  net.jmp.spring.boot.react.learning.configuration.AuthProperties
    public SecurityConfigurer(final AuthProperties authProperties) {
        super();

        this.authProperties = authProperties;
    }

    /// Return a security filter chain
    ///
    /// @param  http    org.springframework.security.config.annotation.web.builders.HttpSecurity
    /// @return         org.springframework.security.web.SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) {
        http
                // This line tells Spring Security to look for a
                // CorsConfigurationSource or use the MVC CORS configuration
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/react/learning/api/e-commerce/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /// Return a user details service
    ///
    /// @param  passwordEncoder org.springframework.security.crypto.password.PasswordEncoder
    /// @return                 org.springframework.security.core.userdetails.UserDetailsService
    @Bean
    public UserDetailsService userDetailsService(final PasswordEncoder passwordEncoder) {
        final InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        if (this.authProperties.users() != null) {
            this.authProperties.users().values().forEach(userProperties -> {
                final String[] roles = userProperties.roles() != null ? userProperties.roles().toArray(new String[0]) : new String[0];

                final UserDetails userDetails = User.withUsername(userProperties.username())
                        .password(passwordEncoder.encode(userProperties.password()))
                        .roles(roles)
                        .build();

                manager.createUser(userDetails);
            });
        }

        return manager;
    }

    /// Return a password encoder
    ///
    /// @return org.springframework.security.crypto.password.PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
