package net.jmp.spring.boot.react.learning.ecommerce.beans;

/*
 * (#)ShippingCostCalculatorBean.java   0.4.0   02/27/2026
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

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.jmp.spring.boot.react.learning.ecommerce.ShippingCost;
import net.jmp.spring.boot.react.learning.ecommerce.ShippingCostRequest;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

/// The shipping cost calculator bean
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShippingCostCalculatorBean {
    /// The 'to' zip code
    private final String toZipCode;

    /// The sub-total
    private final double subTotal;

    /// The number of items
    private final int items;

    /// The constructor
    ///
    /// @param  toZipCode   java.lang.String
    /// @param  subTotal    double
    /// @param  items       int
    public ShippingCostCalculatorBean(final String toZipCode, final double subTotal, final int items) {
        super();

        this.toZipCode = toZipCode;
        this.subTotal = subTotal;
        this.items = items;
    }

    /// The calculate method
    ///
    /// @return net.jmp.spring.boot.react.learning.ecommerce.ShippingCost
    public ShippingCost calculate() {
        final ShippingCostRequest request = new ShippingCostRequest(this.toZipCode, this.subTotal, this.items);

        double cost = 0.0;
        double surcharge = this.calculateSurcharge();
        double travel = this.calculateTravel();

        cost += surcharge;
        cost += travel;

        return new ShippingCost(request, cost);
    }

    /// The calculate surcharge method
    ///
    /// @return double
    private double calculateSurcharge() {
        double surcharge;

        if (this.subTotal < 100.0) {
            surcharge = this.subTotal * 0.1;
        } else if (this.subTotal < 500.0) {
            surcharge = this.subTotal * 0.04;
        } else if (this.subTotal < 1000.0) {
            surcharge = this.subTotal * 0.016;
        } else {
            surcharge = this.subTotal * 0.0096;
        }

        return BigDecimal.valueOf(surcharge)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /// The calculate travel based on distance method
    ///
    /// @return double
    private double calculateTravel() {
        return BigDecimal.valueOf(0.0)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
