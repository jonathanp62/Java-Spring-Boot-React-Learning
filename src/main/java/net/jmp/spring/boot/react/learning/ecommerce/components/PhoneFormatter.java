package net.jmp.spring.boot.react.learning.ecommerce.components;

/*
 * (#)PhoneFormatter.java   0.2.0   02/02/2026
 * (#)PhoneFormatter.java   0.1.0   01/05/2026
 *
 * @author    Jonathan Parker
 * @version   0.2.0
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

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/// The phone number formatter class. It is used in Thymeleaf templates.
@Component("phoneFormatter")
public class PhoneFormatter {
    /// The log tracer
    private final LogTracer logTracer;

    /// The default constructor
    public PhoneFormatter() {
        super();

        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// Formats the phone number
    ///
    /// @param  phoneNumber   java.lang.String
    /// @return               java.lang.String
    public String format(final String phoneNumber) {
        return this.logTracer.tracedWith(() -> {
            String clean = phoneNumber.replaceAll("[^\\d]", "");

            // Handle numbers with country code '+1' or '1' by keeping only last 10 digits

            if (clean.length() > 10) {
                clean = clean.substring(clean.length() - 10);
            }

            // Ensure we have exactly 10 digits before formatting

            String result;

            if (clean.length() == 10) {
                result = clean.replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
            } else {
                result = phoneNumber;
            }

            return result;

        }, phoneNumber);
    }
}
