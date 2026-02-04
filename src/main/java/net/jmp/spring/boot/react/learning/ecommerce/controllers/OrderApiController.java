package net.jmp.spring.boot.react.learning.ecommerce.controllers;

/*
 * (#)OrderApiController.java   0.2.0   02/02/2026
 * (#)OrderApiController.java   0.1.0   12/10/2025
 *
 * @author    Jonathan Parker
 * @version   0.2.0
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

import java.util.List;

import net.jmp.spring.boot.react.learning.ecommerce.Order;

import net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;
import net.jmp.spring.boot.react.learning.ecommerce.helpers.OptionalToResponseEntityMapper;
import net.jmp.spring.boot.react.learning.ecommerce.helpers.UserRoleChecker;

import net.jmp.spring.boot.react.learning.ecommerce.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

/// The order API controller
@RestController
@RequestMapping("/react/learning/api/e-commerce")
public class OrderApiController {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The order service
    private final OrderService orderService;

    /// The log tracer
    private final LogTracer logTracer;

    /// The user role checker
    private final UserRoleChecker userRoleChecker;

    /// The constructor
    ///
    /// @param   orderService   net.jmp.spring.boot.react.learning.ecommerce.services.OrderService
    public OrderApiController(final OrderService orderService) {
        super();

        this.orderService = orderService;
        this.logTracer = new LogTracer(this.logger);
        this.userRoleChecker = new UserRoleChecker();
    }

    /// The OK method
    ///
    /// @param  authentication      org.springframework.security.core.Authentication
    /// @return                     org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok(final Authentication authentication) {
        return this.logTracer.tracedWith(() -> {
            this.logger.info("User: {}", authentication.getName());
            this.logger.info("Roles: {}", authentication.getAuthorities());

            return new ResponseEntity<>("OK", HttpStatus.OK);

        }, authentication);
    }

    /// The get all orders method
    ///
    /// @return org.springframework.http.ResponseEntity<java.util.List<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>>
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDocument>> orders() {
        return this.logTracer.traced(() -> {
            final List<OrderDocument> orders = this.orderService.getOrders();

            return new ResponseEntity<>(orders, HttpStatus.OK);
        });
    }

    /// The get order by order ID method
    ///
    /// @return org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDocument> orderById(final @PathVariable String orderId) {
        return this.logTracer.tracedWith(() ->
            OptionalToResponseEntityMapper.map(this.orderService.getOrderById(orderId)), orderId);
    }

    /// The save order method
    ///
    /// @param  order           java.util.List<net.jmp.spring.boot.react.learning.ecommerce.Order>
    /// @param  authentication  org.springframework.security.core.Authentication
    /// @return                 org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>
    @PostMapping("/order")
    public ResponseEntity<OrderDocument> save(final @RequestBody Order order, final Authentication authentication) {
        return this.logTracer.tracedWith(() ->
            this.userRoleChecker.ifReadWrite(authentication, () -> {
                final OrderDocument document = this.createOrderDocument(order);
                final OrderDocument saved = this.orderService.saveOrder(document);

                this.logger.info("User {} saved order document: {}", authentication.getName(), saved);

                return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }), order, authentication);
    }

    /// The create order document method
    ///
    /// @param  order   java.util.List<net.jmp.spring.boot.react.learning.ecommerce.Order>
    /// @return         net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    private OrderDocument createOrderDocument(final Order order) {
        return this.logTracer.tracedWith(() -> {
            final OrderDocument orderDocument = new OrderDocument();

            orderDocument.setOrderId(order.orderId());
            orderDocument.setOrderDate(order.orderDate());
            orderDocument.setFirstName(order.firstName());
            orderDocument.setLastName(order.lastName());
            orderDocument.setAddress(order.address());
            orderDocument.setCity(order.city());
            orderDocument.setState(order.state());
            orderDocument.setZipCode(order.zipCode());
            orderDocument.setCountry(order.country());
            orderDocument.setPhone(order.phone());
            orderDocument.setEmail(order.email());
            orderDocument.setTaxRate(order.taxRate() != null ? order.taxRate() : 0.0);
            orderDocument.setProducts(order.products());

            return orderDocument;
        }, order);
    }
}
