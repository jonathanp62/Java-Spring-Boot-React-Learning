package net.jmp.spring.boot.react.learning.ecommerce.helpers;

/*
 * (#)UserRoleChecker.java  0.2.0   02/03/2026
 *
 * @author    Jonathan Parker
 * @version   0.2.0
 * @since     0.2.0
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

import java.util.List;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/// The user role checker
public final class UserRoleChecker {
    /// The logger
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The log tracer
    final LogTracer logTracer;

    /// The default constructor
    public UserRoleChecker() {
        super();

        this.logTracer = new LogTracer(this.logger);
    }

    /// The method that runs the allowed supplier
    /// when the user has the READWRITE role.
    ///
    /// @param   <T>            The return type of the allowed supplier
    /// @param   authentication org.springframework.security.core.Authentication
    /// @param   allowed        java.util.function.Supplier<org.springframework.http.ResponseEntity<T>>
    /// @return                 org.springframework.http.ResponseEntity<T>
    public <T> ResponseEntity<T> ifReadWrite(final Authentication authentication, final Supplier<ResponseEntity<T>> allowed) {
        return this.logTracer.tracedWith(() -> {
            final List<String> userRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

            if (userRoles.contains("ROLE_READWRITE")) {
                return allowed.get();
            } else {
                this.logger.warn("User {} does not have the READWRITE role", authentication.getName());

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        }, authentication);
    }
}
