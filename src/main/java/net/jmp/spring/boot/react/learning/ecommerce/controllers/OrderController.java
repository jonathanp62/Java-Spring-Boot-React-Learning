package net.jmp.spring.boot.react.learning.ecommerce.controllers;

/*
 * (#)OrderController.java  0.1.0   01/03/2026
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

import net.jmp.spring.boot.react.learning.ecommerce.services.OrderService;

import static net.jmp.util.logging.LoggerUtils.entryWith;
import static net.jmp.util.logging.LoggerUtils.exitWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

/// The order controller class.
@Controller
public class OrderController {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The order service
    private final OrderService orderService;

    /// The constructor
    ///
    /// @param  orderService    net.jmp.spring.boot.react.learning.ecommerce.services.OrderService
    public OrderController(final OrderService orderService) {
        super();

        this.orderService = orderService;
    }

    /// Maps GET requests for the e-commerce orders path to the "e-commerce/orders" view.
    ///
    /// @return java.lang.String
    @GetMapping("/e-commerce/orders/")
    public String orders(final Model model) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(model));
        }

        model.addAttribute("ordersList", this.orderService.getOrders());

        final String template = "e-commerce/orders";

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(template));
        }

        return template;
    }
}
