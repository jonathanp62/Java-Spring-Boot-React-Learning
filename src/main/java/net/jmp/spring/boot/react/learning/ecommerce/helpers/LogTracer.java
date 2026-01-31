package net.jmp.spring.boot.react.learning.ecommerce.helpers;

/*
 * (#)LogTracer.java    0.2.0   01/31/2026
 *
 * @author    Jonathan Parker
 * @version   0.2.0
 * @since     0.2.0
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

import java.util.function.Supplier;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;

/// The log tracer
public final class LogTracer {
    /// The logger
    private final Logger logger;

    /// The constructor
    ///
    /// @param   logger org.slf4j.Logger
    public LogTracer(final Logger logger) {
        super();

        this.logger = logger;
    }

    /// The traced method
    ///
    /// @param  <T>      The return type
    /// @param  supplier java.util.function.Supplier
    /// @return          T
    public <T> T traced(final Supplier<T> supplier) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final T result = supplier.get();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The traced method
    ///
    /// @param  <T>      The return type
    /// @param  supplier java.util.function.Supplier
    /// @param  args     java.lang.Object[]
    /// @return          T
    public <T> T tracedWith(final Supplier<T> supplier, final Object... args) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(args));
        }

        final T result = supplier.get();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }
}
