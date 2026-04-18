package net.jmp.spring.boot.react.learning.ecommerce.fakestore;

/*
 * (#)Rating.java   0.5.0   04/18/2026
 *
 * @author    Jonathan Parker
 * @version   0.5.0
 * @since     0.5.0
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

import java.util.Objects;

/// The rating element of a product in the fake store
public class Rating {
    /// The rate
    double rate;

    /// The count
    int count;

    /// The default constructor
    public Rating() {
        super();
    }

    /// Get the rate
    ///
    /// @return double
    public double getRate() {
        return this.rate;
    }

    /// Set the rate
    ///
    /// @param  rate    double
    public void setRate(final double rate) {
        this.rate = rate;
    }

    /// Get the count
    ///
    /// @return int
    public int getCount() {
        return this.count;
    }

    /// Set the count
    ///
    /// @param  count   int
    public void setCount(final int count) {
        this.count = count;
    }

    /// The equals method
    ///
    /// @param  o   java.lang.Object
    /// @return     boolean
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rating that)) return false;

        return Double.compare(this.rate, that.rate) == 0 && this.count == that.count;
    }

    /// The hash code method
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(this.rate, this.count);
    }

    /// The to-string method
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "Rating{" +
                "rate=" + this.rate +
                ", count=" + this.count +
                '}';
    }
}
