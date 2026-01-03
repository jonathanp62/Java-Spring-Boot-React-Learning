package net.jmp.spring.boot.react.learning.ecommerce.components;

/*
 * (#)NameFormatter.java    0.1.0   01/03/2026
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2026 Jonathan M. Parker
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

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/// The name formatter class. It is used in Thymeleaf templates.
@Component("nameFormatter")
public class NameFormatter {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor
    public NameFormatter() {
        super();
    }

    /// Formats the name
    ///
    /// @param  firstName   java.lang.String
    /// @param  lastName    java.lang.String
    /// @return             java.lang.String
    public String format(final String firstName, final String lastName) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(firstName, lastName));
        }

        final String name = firstName + " " + lastName;

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(name));
        }

        return name;
    }
}
