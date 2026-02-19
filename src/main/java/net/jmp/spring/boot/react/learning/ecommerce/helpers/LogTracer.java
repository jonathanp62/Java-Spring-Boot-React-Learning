package net.jmp.spring.boot.react.learning.ecommerce.helpers;

/*
 * (#)LogTracer.java    0.3.0   02/19/2026
 * (#)LogTracer.java    0.2.0   01/31/2026
 *
 * @author    Jonathan Parker
 * @version   0.3.0
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

import java.util.Objects;

import java.util.function.Supplier;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;

/// The log tracer
public final class LogTracer {
    /// The stack walker
    private static final StackWalker STACK_WALKER =
            StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    /// The caller method
    private static Caller caller() {
        return STACK_WALKER.walk(stream ->
                stream
                        // Drop frames that belong to LogTracer itself
                        .dropWhile(f -> f.getDeclaringClass() == LogTracer.class)
                        // First frame outside LogTracer is the "real" caller
                        .findFirst()
                        .map(f -> new Caller(
                                f.getDeclaringClass().getName(),
                                f.getMethodName(),
                                f.getLineNumber()
                        ))
                        .orElse(new Caller("unknown", "unknown", -1))
        );
    }

    /// The caller record
    private record Caller(String className, String methodName, int lineNumber) {}

    /// The logger
    private final Logger logger;

    /// The default constructor
    private LogTracer() {
        throw new UnsupportedOperationException("A LogTracer instance always requires a logger");
    }

    /// The constructor
    ///
    /// @param   logger org.slf4j.Logger
    public LogTracer(final Logger logger) {
        super();

        this.logger = Objects.requireNonNull(logger, "logger");
    }

    /// Get the logger
    ///
    /// @return org.slf4j.Logger
    public Logger getLogger() {
        return this.logger;
    }

    /// The traced method
    ///
    /// @param  <T>      The return type
    /// @param  supplier java.util.function.Supplier
    /// @return          T
    public <T> T traced(final Supplier<T> supplier) {
        T result;

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());

            result = this.getResult(caller(), supplier);

            this.logger.trace(exitWith(result));
        } else {
            result = supplier.get();
        }

        return result;
    }

    /// The traced method when nothing is returned
    ///
    /// @param  runnable java.lang.Runnable
    public void traced(final Runnable runnable) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());

            this.getResult(caller(), () -> {
                runnable.run();

                return null;
            });

            this.logger.trace(exitWith(null));
        } else {
            runnable.run();
        }
    }

    /// The traced method when the supplier has arguments
    ///
    /// @param  <T>      The return type
    /// @param  supplier java.util.function.Supplier
    /// @param  args     java.lang.Object[]
    /// @return          T
    public <T> T tracedWith(final Supplier<T> supplier, final Object... args) {
        T result;

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(args));

            result = this.getResult(caller(), supplier);

            this.logger.trace(exitWith(result));
        } else {
            result = supplier.get();
        }

        return result;
    }

    /// The traced method when the runnable has arguments
    ///
    /// @param  runnable java.lang.Runnable
    /// @param  args     java.lang.Object[]
    public void tracedWith(final Runnable runnable, final Object... args) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(args));

            this.getResult(caller(), () -> {
                runnable.run();

                return null;
            });

            this.logger.trace(exitWith(null));
        } else {
            runnable.run();
        }
    }

    /// The method that calls the supplier and
    /// returns the result when tracing is enabled
    ///
    /// @param  <T>      The return type
    /// @param  caller   The caller
    /// @param  supplier java.util.function.Supplier
    /// @return          T
    private <T> T getResult(final Caller caller, final Supplier<T> supplier) {
        this.logger.trace("caller={}.{}:{}", caller.className, caller.methodName, caller.lineNumber);

        return supplier.get();
    }
}
