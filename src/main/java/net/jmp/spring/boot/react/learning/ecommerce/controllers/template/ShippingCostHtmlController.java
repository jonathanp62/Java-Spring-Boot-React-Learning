package net.jmp.spring.boot.react.learning.ecommerce.controllers.template;

/*
 * (#)ShippingCostController.java   0.4.0   02/28/2026
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

import net.jmp.spring.boot.react.learning.ecommerce.services.DistanceService;

import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/// The shipping cost HTML controller class.
@Controller
public class ShippingCostHtmlController {
    /// The log tracer
    private final LogTracer logTracer;

    /// The application context
    private final ApplicationContext applicationContext;

    /// The distance service
    private final DistanceService distanceService;

    /// The constructor
    ///
    /// @param  applicationContext    org.springframework.context.ApplicationContext
    /// @param  distanceService       net.jmp.spring.boot.react.learning.ecommerce.services.DistanceService
    public ShippingCostHtmlController(final ApplicationContext applicationContext, final DistanceService distanceService) {
        super();

        this.logTracer = new LogTracer(LoggerFactory.getLogger(this.getClass()));
        this.applicationContext = applicationContext;
        this.distanceService = distanceService;
    }

    /// Maps GET requests for the e-commerce shipping cost path to the "e-commerce/shipping-cost" view.
    ///
    /// @return java.lang.String
    @GetMapping("/e-commerce/shipping-cost/calculator")
    public String calculateForm() {
        return this.logTracer.traced(() -> "e-commerce/shipping-cost-calculator");
    }

    /// Maps POST requests for the e-commerce shipping cost results path to the "e-commerce/shipping-cost=result" view.
    ///
    /// @param  toZipCode   java.lang.String
    /// @param  subTotal    double
    /// @param  items       int
    /// @param  model       org.springframework.ui.Model
    /// @return             java.lang.String
    @PostMapping("/e-commerce/shipping-cost/calculated-results")
    public String showResult(
            final @RequestParam String toZipCode,
            final @RequestParam double subTotal,
            final @RequestParam int items,
            final Model model
    ) {
        final ShippingCostCalculatorBean calculatorBean = this.applicationContext.getBean(
                ShippingCostCalculatorBean.class,
                this.distanceService,
                toZipCode,
                subTotal,
                items
        );

        final ShippingCost shippingCost = calculatorBean.calculate();

        model.addAttribute("shippingCost", shippingCost);
        model.addAttribute("toZipCode", toZipCode);
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("items", items);

        return this.logTracer.tracedWith(() -> "e-commerce/shipping-cost-results", toZipCode, subTotal, items, model);
    }
}
