package net.jmp.spring.boot.react.learning.ecommerce;

/*
 * (#)ProductDocument.java  0.1.0   12/13/2025
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

import org.springframework.data.annotation.Id;

import java.util.Objects;

/// The product document
public class ProductDocument {
    /// The Mongo identifier
    @Id
    private String id;

    /// The product
    private Product product;

    /// The default constructor
    public ProductDocument() {
        super();
    }

    /// Get the Mongo identifier.
    ///
    /// @return  java.lang.String
    public String getId() {
        return this.id;
    }

    /// Set the Mongo identifier.
    ///
    /// @param  id  java.lang.String
    public void setId(final String id) {
        this.id = id;
    }

    /// Get the product.
    ///
    /// @return net.jmp.spring.boot.react.learning.ecommerce.Product
    public Product getProduct() {
        return this.product;
    }

    /// Set the product.
    ///
    /// @param  product net.jmp.spring.boot.react.learning.ecommerce.Product
    public void setProduct(final Product product) {
        this.product = product;
    }

    /// The equals method
    ///
    /// @param  o  java.lang.Object
    /// @return    boolean
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ProductDocument that = (ProductDocument) o;

        return Objects.equals(this.id, that.id) && Objects.equals(this.product, that.product);
    }

    /// The hash code method
    ///
    /// @return  int
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.product);
    }

    /// The to-string method
    ///
    /// @return  java.lang.String
    @Override
    public String toString() {
        return "ProductDocument{" +
                "id='" +this.id + '\'' +
                ", product=" + this.product +
                '}';
    }
}
