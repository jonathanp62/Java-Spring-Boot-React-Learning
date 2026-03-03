package net.jmp.spring.boot.react.learning.ecommerce.components;

/*
 * (#)OrderCostCalculator.java  0.4.0   03/03/2026
 * (#)OrderCostCalculator.java  0.2.0   02/02/2026
 * (#)OrderCostCalculator.java  0.1.0   01/03/2026
 *
 * @author    Jonathan Parker
 * @version   0.4.0
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

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.NumberFormat;

import java.util.Locale;

import net.jmp.spring.boot.react.learning.ecommerce.Product;
import net.jmp.spring.boot.react.learning.ecommerce.ShippingCost;

import net.jmp.spring.boot.react.learning.ecommerce.beans.ShippingCostCalculatorBean;

import net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import net.jmp.spring.boot.react.learning.ecommerce.services.DistanceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

/// The order cost calculator class. It is used in Thymeleaf templates.
@Component("orderCostCalculator")
public class OrderCostCalculator {
    /// The log tracer
    private final LogTracer logTracer;

    /// The application context
    private final ApplicationContext applicationContext;

    /// The distance service
    private final DistanceService distanceService;

    /// The constructor
    ///
    /// @param  applicationContext  org.springframework.context.ApplicationContext
    /// @param  distanceService     net.jmp.spring.boot.react.learning.ecommerce.services.DistanceService
    public OrderCostCalculator(final ApplicationContext applicationContext, final DistanceService distanceService) {
        super();

        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
        this.applicationContext = applicationContext;
        this.distanceService = distanceService;
    }

    /// The calculate total cost method
    ///
    /// @param  orderDocument  net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    /// @return                double
    public double calculateTotalCost(final OrderDocument orderDocument) {
        return this.logTracer.tracedWith(() -> {
            final double subtotal = orderDocument.getProducts() != null
                    ? orderDocument.getProducts().stream().mapToDouble(Product::price).sum()
                    : 0.0;

            double unroundedTotal = subtotal * (1.0 + orderDocument.getTaxRate());  // Add the sales tax

            final int items = orderDocument.getProducts() != null
                    ? orderDocument.getProducts().size()
                    : 0;

            final String toZipCode = orderDocument.getZipCode();

            final ShippingCost shippingCost = this.getShippingCost(toZipCode, subtotal, items);

            unroundedTotal += shippingCost.totalCost();  // Add the shipping cost

            return BigDecimal.valueOf(unroundedTotal)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }, orderDocument);
    }

    /// The calculate total cost as money method
    ///
    /// @param  orderDocument  net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    /// @return                java.lang.String
    public String calculateTotalCostAsMoney(final OrderDocument orderDocument) {
        return this.logTracer.tracedWith(() -> {
            final double total = this.calculateTotalCost(orderDocument);

            return NumberFormat.getCurrencyInstance(Locale.US).format(total);
        }, orderDocument);
    }

    /// The calculate sub-total as money method
    ///
    /// @param  orderDocument  net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    /// @return                java.lang.String
    public String calculateSubTotalAsMoney(final OrderDocument orderDocument) {
        return this.logTracer.tracedWith(() -> {
            final double subtotal = orderDocument.getProducts() != null
                    ? orderDocument.getProducts().stream().mapToDouble(Product::price).sum()
                    : 0.0;

            return NumberFormat.getCurrencyInstance(Locale.US).format(subtotal);
        }, orderDocument);
    }

    /// The calculate tax as money method
    ///
    /// @param  orderDocument  net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    /// @return                java.lang.String
    public String calculateTaxAsMoney(final OrderDocument orderDocument) {
        return this.logTracer.tracedWith(() -> {
            final double subtotal = orderDocument.getProducts() != null
                    ? orderDocument.getProducts().stream().mapToDouble(Product::price).sum()
                    : 0.0;

            final double unroundedTax = subtotal * orderDocument.getTaxRate();

            final double tax = BigDecimal.valueOf(unroundedTax)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            return NumberFormat.getCurrencyInstance(Locale.US).format(tax);
        }, orderDocument);
    }

    /// The calculate shipping as money method
    ///
    /// @param  orderDocument  net.jmp.spring.boot.react.learning.ecommerce.documents.OrderDocument
    /// @return                java.lang.String
    public String calculateShippingAsMoney(final OrderDocument orderDocument) {
        return this.logTracer.tracedWith(() -> {
            final double subtotal = orderDocument.getProducts() != null
                    ? orderDocument.getProducts().stream().mapToDouble(Product::price).sum()
                    : 0.0;

            final int items = orderDocument.getProducts() != null
                    ? orderDocument.getProducts().size()
                    : 0;

            final String toZipCode = orderDocument.getZipCode();

            final ShippingCost shippingCost = this.getShippingCost(toZipCode, subtotal, items);

            return shippingCost.totalCostRoundedAsMoney();
        }, orderDocument);
    }

    /// Fetch the shipping cost
    ///
    /// @param  toZipCode  java.lang.String
    /// @param  subTotal   double
    /// @param  items      int
    /// @return            net.jmp.spring.boot.react.learning.ecommerce.ShippingCost
    private ShippingCost getShippingCost(final String toZipCode, double subTotal, int items) {
        return this.logTracer.tracedWith(() -> {
            String zipCode;

            /* Some zip codes are specified as 9-digit zip codes */

            if (toZipCode.length() > 5) {
                zipCode = toZipCode.substring(0, 5);
            } else {
                zipCode = toZipCode;
            }

            final ShippingCostCalculatorBean calculatorBean = this.applicationContext.getBean(
                    ShippingCostCalculatorBean.class,
                    this.distanceService,
                    zipCode,
                    subTotal,
                    items
            );

            final ShippingCost shippingCost = calculatorBean.calculate();

            if (!shippingCost.status().equals("OK")) {
                final Logger logger = this.logTracer.getLogger();

                logger.error("Shipping cost calculation failed: {}", shippingCost.message());
            }

            return shippingCost;
        }, toZipCode, subTotal, items);
    }
}
