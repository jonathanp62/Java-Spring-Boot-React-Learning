package net.jmp.spring.boot.react.learning.ecommerce.services;

/*
 * (#)OrderService.java 0.1.0   01/02/2026
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

import java.util.List;
import java.util.Optional;

import net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument;

import net.jmp.spring.boot.react.learning.ecommerce.repositories.OrderDocumentRepository;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/// The order service
@Service
public class OrderService {
    /// The logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /// The order document repository
    private final OrderDocumentRepository orderDocumentRepository;

    /// The constructor
    ///
    /// @param   orderDocumentRepository    net.jmp.spring.boot.react.learning.ecommerce.repositories.OrderDocumentRepository
    public OrderService(final OrderDocumentRepository orderDocumentRepository) {
        super();

        this.orderDocumentRepository = orderDocumentRepository;
    }

    /// The get orders method
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>
    public List<OrderDocument> getOrders() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<OrderDocument> orders = this.orderDocumentRepository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(orders));
        }

        return orders;
    }

    /// The get order by id method
    ///
    /// @param   orderId    java.lang.String
    /// @return             java.util.Optional<net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument>
    public Optional<OrderDocument> getOrderById(final String orderId) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(orderId));
        }

        final Optional<OrderDocument> order = this.orderDocumentRepository.findByOrderId(orderId);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(order));
        }

        return order;
    }

    /// The save order method
    ///
    /// @param   orderDocument    net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    /// @return                   net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    public OrderDocument saveOrder(final OrderDocument orderDocument) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(orderDocument));
        }

        final OrderDocument saved = this.orderDocumentRepository.save(orderDocument);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(saved));
        }

        return saved;
    }
}
