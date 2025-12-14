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

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

/// The product document
@Document(collection = "e_commerce")
public class ProductDocument {
    /// The Mongo identifier
    @Id
    private String documentId;

    /// The products
    private List<Product> products;

    /// The default constructor
    public ProductDocument() {
        super();
    }

    /// Get the Mongo identifier.
    ///
    /// @return  java.lang.String
    public String getDocumentId() {
        return this.documentId;
    }

    /// Set the Mongo identifier.
    ///
    /// @param  documentId  java.lang.String
    public void setId(final String documentId) {
        this.documentId = documentId;
    }

    /// Get the products.
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.Product>
    public List<Product> getProducts() {
        return this.products;
    }

    /// Set the products.
    ///
    /// @param  products java.util.List<net.jmp.spring.boot.react.learning.ecommerce.Product>
    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    /// The equals method
    ///
    /// @param  o  java.lang.Object
    /// @return    boolean
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ProductDocument that = (ProductDocument) o;

        return Objects.equals(this.documentId, that.documentId) && Objects.equals(this.products, that.products);
    }

    /// The hash code method
    ///
    /// @return  int
    @Override
    public int hashCode() {
        return Objects.hash(this.documentId, this.products);
    }

    /// The to-string method
    ///
    /// @return  java.lang.String
    @Override
    public String toString() {
        return "ProductDocument{" +
                "documentId='" +this.documentId + '\'' +
                ", products=" + this.products +
                '}';
    }
}
