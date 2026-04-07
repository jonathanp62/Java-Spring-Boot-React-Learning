package net.jmp.spring.boot.react.learning.configuration;

/*
 * (#)CorsConfigurer.java   0.5.0   04/07/2026
 * (#)CorsConfigurer.java   0.1.0   12/26/2025
 *
 * @author    Jonathan Parker
 * @version   0.5.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2025, 2026 Jonathan M. Parker
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

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/// The cross-origin resource sharing configuration
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class CorsConfigurer implements WebMvcConfigurer {
    /// The cross-origin resource sharing properties
    private final CorsProperties corsProperties;

    /// The constructor
    ///
    /// @param  corsProperties  net.jmp.spring.boot.react.learning.configuration.CorsProperties
    public CorsConfigurer(final CorsProperties corsProperties) {
        super();

        this.corsProperties = corsProperties;
    }

    /// The add CORS mappings method
    ///
    /// @param  registry  org.springframework.web.servlet.config.annotation.CorsRegistry
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all endpoints
                .allowedOrigins(this.corsProperties.allowedOrigins().toArray(new String[0]))
                .allowedMethods(this.corsProperties.allowedMethods().toArray(new String[0]))
                .allowedHeaders(this.corsProperties.allowedHeaders().toArray(new String[0]))
                .allowCredentials(this.corsProperties.allowCredentials())
                .maxAge(this.corsProperties.maxAge() != 0 ? this.corsProperties.maxAge() : 3600);   // Cache preflight response for 1 hour (Chrome caps at 2 hours)
    }
}
