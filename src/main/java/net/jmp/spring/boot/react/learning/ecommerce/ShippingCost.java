package net.jmp.spring.boot.react.learning.ecommerce;

/*
 * (#)ShippingCost.java 0.4.0   02/26/2026
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

/// The shipping cost that is returned by the server.
///
/// @param  request                     net.jmp.spring.boot.react.learning.ecommerce.ShippingCostRequest
/// @param  status                      java.lang.String
/// @param  message                     java.lang.String
/// @param  surcharge                   double
/// @param  shipping                    double
/// @param  totalShippingCost           double
/// @param  totalShippingCostRounded    double
public record ShippingCost(
        ShippingCostRequest request,
        String status,
        String message,
        double surcharge,
        double shipping,
        double totalShippingCost,
        double totalShippingCostRounded
) {
}
