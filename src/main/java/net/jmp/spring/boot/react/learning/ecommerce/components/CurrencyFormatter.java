package net.jmp.spring.boot.react.learning.ecommerce.components;

/*
 * (#)CurrencyFormatter.java    0.4.0   03/02/2026
 *
 * @author    Jonathan Parker
 * @version   0.4.0
 * @since     0.4.0
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

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.DecimalFormat;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/// The currency formatter class. It is used in Thymeleaf templates.
@Component("currencyFormatter")
public class CurrencyFormatter {
    /// The log tracer
    private final LogTracer logTracer;

    /// The default constructor
    public CurrencyFormatter() {
        super();

        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// The format method
    ///
    /// @param  amount  double
    /// @return         java.lang.String
    public String format(final double amount) {
        return this.logTracer.tracedWith(() -> {
            final double amountRounded = BigDecimal.valueOf(amount)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            final DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");

            return decimalFormat.format(amountRounded);
        }, amount);
    }
}
