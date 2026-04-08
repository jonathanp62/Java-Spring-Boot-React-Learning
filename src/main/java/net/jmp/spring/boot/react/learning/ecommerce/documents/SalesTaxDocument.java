package net.jmp.spring.boot.react.learning.ecommerce.documents;

/*
 * (#)SalesTaxDocument.java 0.5.0   04/08/2025
 * (#)SalesTaxDocument.java 0.1.0   12/20/2025
 *
 * @author    Jonathan Parker
 * @version   0.5.0
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

import org.jspecify.annotations.Nullable;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

/// The sales tax document that is stored in the database.
@Document(collection = "sales_tax")
public class SalesTaxDocument {
    /// The Mongo identifier
    @Nullable
    @Id
    private String documentId;

    /// The state name
    @Nullable
    private String state;

    /// The state abbreviation
    @Nullable
    private String abbreviation;

    /// The sales tax rate
    @Field("tax")
    private double rate;

    /// The default constructor
    public SalesTaxDocument() {
        super();
    }

    ///
    /// Get the Mongo document identifier.
    ///
    /// @return java.lang.String
    public @Nullable String getDocumentId() {
        return this.documentId;
    }

    ///
    /// Set the Mongo document identifier.
    ///
    /// @param  documentId  java.lang.String
    public void setDocumentId(final String documentId) {
        this.documentId = documentId;
    }

    ///
    /// Get the state.
    ///
    /// @return java.lang.String
    public @Nullable String getState() {
        return this.state;
    }

    ///
    /// Set the state.
    ///
    /// @param  state  java.lang.String
    public void setState(final String state) {
        this.state = state;
    }

    ///
    /// Get the abbreviation.
    ///
    /// @return java.lang.String
    public @Nullable String getAbbreviation() {
        return this.abbreviation;
    }

    ///
    /// Set the abbreviation.
    ///
    /// @param  abbreviation  java.lang.String
    public void setAbbreviation(final String abbreviation) {
        this.abbreviation = abbreviation;
    }

    ///
    /// Get the tax rate.
    ///
    /// @return double
    public double getRate() {
        return this.rate;
    }

    ///
    /// Set the tax rate.
    ///
    /// @param  rate  double
    public void setRate(final double rate) {
        this.rate = rate;
    }

    ///
    /// The equals method.
    ///
    /// @param  o  java.lang.Object
    /// @return    boolean
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SalesTaxDocument that)) return false;

        return Double.compare(this.rate, that.rate) == 0
                && Objects.equals(this.documentId, that.documentId)
                && Objects.equals(this.state, that.state)
                && Objects.equals(this.abbreviation, that.abbreviation);
    }

    ///
    /// The hash code method.
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(this.documentId, this.state, this.abbreviation, this.rate);
    }

    ///
    /// The to string method.
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "SalesTaxDocument{" +
                "documentId='" + this.documentId + '\'' +
                ", state='" + this.state + '\'' +
                ", abbreviation='" + this.abbreviation + '\'' +
                ", rate=" + this.rate +
                '}';
    }
}
