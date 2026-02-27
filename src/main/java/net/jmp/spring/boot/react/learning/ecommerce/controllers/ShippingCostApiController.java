package net.jmp.spring.boot.react.learning.ecommerce.controllers;

/*
 * (#)ShippingCostApiController.java    0.4.0   02/26/2026
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

import net.jmp.spring.boot.react.learning.ecommerce.ShippingCost;

import net.jmp.spring.boot.react.learning.ecommerce.beans.ShippingCostCalculatorBean;

import net.jmp.spring.boot.react.learning.ecommerce.helpers.LogTracer;

import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/// The shipping cost API controller
@RestController
@RequestMapping("/react/learning/api/e-commerce/shipping-cost")
public class ShippingCostApiController {
    /// The log tracer
    private final LogTracer logTracer;

    /// The application context
    private final ApplicationContext applicationContext;

    /// The default constructor
    public ShippingCostApiController(ApplicationContext applicationContext) {
        super();

        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
        this.applicationContext = applicationContext;
    }

    /// The calculate method
    ///
    /// @return org.springframework.http.ResponseEntity<java.lang.String>
    @GetMapping("/calculate")
    public ResponseEntity<ShippingCost> calculate(final @RequestParam String toZipCode,
                                            final @RequestParam double subTotal,
                                            final @RequestParam int items) {
        return this.logTracer.tracedWith(() -> {
            final ShippingCostCalculatorBean calculatorBean = this.applicationContext.getBean(
                    ShippingCostCalculatorBean.class,
                    toZipCode,
                    subTotal,
                    items
            );

            final ShippingCost shippingCost = calculatorBean.calculate();

            return new ResponseEntity<>(shippingCost, HttpStatus.OK);
        }, toZipCode, subTotal, items);
    }
}
