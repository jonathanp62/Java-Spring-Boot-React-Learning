package net.jmp.spring.boot.react.learning.ecommerce.controllers.template;

/*
 * (#)GenerateErrorController.java  0.3.0   02/19/2026
 *
 * @author    Jonathan Parker
 * @version   0.3.0
 * @since     0.3.0
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

/// The generate error HTML controller class.
@Controller
public class GenerateErrorHtmlController {
    /// The log tracer
    private final LogTracer logTracer;

    /// The default constructor
    public GenerateErrorHtmlController() {
        super();

        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
    }

    /// Maps GET requests for the e-commerce generate error path to the "e-commerce/generate-error" view.
    ///
    /// @param  model   org.springframework.ui.Model
    /// @return         java.lang.String
    @GetMapping("/e-commerce/generate-error")
    public String generateError(final Model model) {
        return this.logTracer.traced(() -> {
            final Logger logger = this.logTracer.getLogger();

            try {
                throw new Exception("This is the error generated");
            } catch (final Exception e) {
                logger.error("Generated error caught", e);

                model.addAttribute("error", e.getMessage());
            }

            return "e-commerce/generate-error";
        });
    }
}
