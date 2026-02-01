package net.jmp.spring.boot.react.learning.ecommerce.controllers;

/*
 * (#)OrderController.java  0.2.0   02/01/2026
 * (#)OrderController.java  0.1.0   01/03/2026
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

import java.util.Optional;

import net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument;

import net.jmp.spring.boot.react.learning.ecommerce.exceptions.OrderNotFoundException;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import net.jmp.spring.boot.react.learning.ecommerce.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/// The order controller class.
@Controller
public class OrderController {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The order service
    private final OrderService orderService;

    /// The log tracer
    private final LogTracer logTracer;

    /// The constructor
    ///
    /// @param  orderService    net.jmp.spring.boot.react.learning.ecommerce.services.OrderService
    public OrderController(final OrderService orderService) {
        super();

        this.orderService = orderService;
        this.logTracer = new LogTracer(this.logger);
    }

    /// Maps GET requests for the e-commerce orders path to the "e-commerce/orders" view.
    ///
    /// @return java.lang.String
    @GetMapping("/e-commerce/orders/")
    public String orders(final Model model) {
        return this.logTracer.tracedWith(() -> {
            model.addAttribute("ordersList", this.orderService.getOrders());

            return "e-commerce/orders";

        }, model);
    }

    /// Maps GET requests for the e-commerce order detail path to the "e-commerce/order-detail" view.
    ///
    /// @param  orderId    java.lang.String
    /// @param  model      org.springframework.ui.Model
    /// @return            java.lang.String
    @GetMapping("/e-commerce/order-detail/")
    public String orderDetail(final @RequestParam String orderId, final Model model) {
        return this.logTracer.tracedWith(() -> {
            final Optional<OrderDocument> order = this.orderService.getOrderById(orderId);

            /*
             * The exception is here just to provide a model for how to
             * throw exceptions in a controller that can be viewed in the
             * Thymeleaf error pages.
             */

            if (order.isEmpty()) {
                throw new OrderNotFoundException("Order " + orderId + " not found");
            }

            model.addAttribute("order", order.get());
            model.addAttribute("orderFound", true);

            return  "e-commerce/order-detail";

        }, orderId, model);
    }
}
