package net.jmp.spring.boot.react.learning.ecommerce.controllers;

/*
 * (#)OrderController.java  0.1.0   12/10/2025
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2025 Jonathan M. Parker
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
import java.util.Optional;

import net.jmp.spring.boot.react.learning.ecommerce.Order;

import net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument;

import net.jmp.spring.boot.react.learning.ecommerce.repositories.OrderDocumentRepository;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.web.bind.annotation.*;

/// The order controller
@RestController
@RequestMapping("/react/learning/api/e-commerce")
public class OrderController {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The order document repository
    private final OrderDocumentRepository orderDocumentRepository;

    /// The constructor
    ///
    /// @param   orderDocumentRepository    net.jmp.spring.boot.react.learning.ecommerce.repositories.OrderDocumentRepository
    public OrderController(final OrderDocumentRepository orderDocumentRepository) {
        super();

        this.orderDocumentRepository = orderDocumentRepository;
    }

    /// The OK method
    ///
    /// @param  authentication      org.springframework.security.core.Authentication
    /// @return                     org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/ok")
    public ResponseEntity<String> ok(final Authentication authentication) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(authentication));
        }

        this.logger.info("User: {}", authentication.getName());
        this.logger.info("Roles: {}", authentication.getAuthorities());

        final ResponseEntity<String> result = new ResponseEntity<>("OK", HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The get all orders method
    ///
    /// @return org.springframework.http.ResponseEntity<java.util.List<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>>
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDocument>> orders() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<OrderDocument> orders = this.orderDocumentRepository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));
        final ResponseEntity<List<OrderDocument>> result = new ResponseEntity<>(orders, HttpStatus.OK);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The get order by order ID method
    ///
    /// @return org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDocument> orderById(final @PathVariable String orderId) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final Optional<OrderDocument> order = this.orderDocumentRepository.findByOrderId(orderId);

        final ResponseEntity<OrderDocument> result = order
                .map(found -> new ResponseEntity<>(found, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The save order method
    ///
    /// @param  order           java.util.List<net.jmp.spring.boot.react.learning.ecommerce.Order>
    /// @param  authentication  org.springframework.security.core.Authentication
    /// @return                 org.springframework.http.ResponseEntity<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>
    @PostMapping("/order")
    public ResponseEntity<OrderDocument> save(final @RequestBody Order order, final Authentication authentication) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(order, authentication));
        }

        ResponseEntity<OrderDocument> result;

        final List<String> userRoles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        if (userRoles.contains("ROLE_READWRITE")) {
            final OrderDocument document = this.createOrderDocument(order);
            final OrderDocument saved = this.orderDocumentRepository.save(document);

            this.logger.info("User {} saved order document: {}", authentication.getName(), saved);

            result = new ResponseEntity<>(saved, HttpStatus.CREATED);
        } else {
            this.logger.warn("User {} does not have the READWRITE role", authentication.getName());

            result = new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result));
        }

        return result;
    }

    /// The create order document method
    ///
    /// @param  order   java.util.List<net.jmp.spring.boot.react.learning.ecommerce.Order>
    /// @return         net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    private OrderDocument createOrderDocument(final Order order) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(order));
        }

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

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(orderDocument));
        }

        return orderDocument;
    }
}
