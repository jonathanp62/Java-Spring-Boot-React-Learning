package net.jmp.spring.boot.react.learning.ecommerce.documents;

/*
 * (#)OrderDocument.java    0.1.0   12/13/2025
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

import net.jmp.spring.boot.react.learning.ecommerce.Product;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

import java.util.List;
import java.util.Objects;

/// The order document that is stored in the database.
@Document(collection = "e_commerce")
public class OrderDocument {
    /// The Mongo identifier
    @Id
    private String documentId;

    /// The order identifier
    private String orderId;

    /// The order date
    private Instant orderDate;

    /// The first name
    private String firstName;

    /// The last name
    private String lastName;

    /// The address
    private String address;

    /// The city
    private String city;

    /// The state
    private String state;

    /// The zip code
    private String zipCode;

    /// The country
    private String country;

    /// The phone number
    private String phone;

    /// The email address
    private String email;

    /// The tax rate
    private double taxRate;

    /// The products
    private List<Product> products;

    /// The default constructor
    public OrderDocument() {
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
    public void setDocumentId(final String documentId) {
        this.documentId = documentId;
    }

    ///
    /// Get the order date.
    ///
    /// @return java.time.Instant
    public Instant getOrderDate() {
        return this.orderDate;
    }

    /// Set the order date.
    ///
    /// @param  orderDate   java.time.Instant
    public void setOrderDate(final Instant orderDate) {
        this.orderDate = orderDate;
    }

    /// Get the products.
    ///
    /// @return java.util.List<net.jmp.spring.boot.react.learning.ecommerce.Product>
    public List<Product> getProducts() {
        return this.products;
    }

    /// Get the order identifier.
    ///
    /// @return java.lang.String
    public String getOrderId() {
        return this.orderId;
    }

    /// Get the first name.
    ///
    /// @return java.lang.String
    public String getFirstName() {
        return this.firstName;
    }

    /// Get the last name.
    ///
    /// @return java.lang.String
    public String getLastName() {
        return this.lastName;
    }

    /// Get the address.
    ///
    /// @return java.lang.String
    public String getAddress() {
        return this.address;
    }

    /// Get the city.
    ///
    /// @return java.lang.String
    public String getCity() {
        return this.city;
    }

    /// Get the state.
    ///
    /// @return java.lang.String
    public String getState() {
        return this.state;
    }

    /// Get the zip code.
    ///
    /// @return java.lang.String
    public String getZipCode() {
        return this.zipCode;
    }

    /// Get the country.
    ///
    /// @return java.lang.String
    public String getCountry() {
        return this.country;
    }

    /// Get the phone number.
    ///
    /// @return java.lang.String
    public String getPhone() {
        return this.phone;
    }

    /// Get the email address.
    ///
    /// @return java.lang.String
    public String getEmail() {
        return this.email;
    }

    /// Set the order identifier.
    ///
    /// @param  orderId  java.lang.String
    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    /// Set the first name.
    ///
    /// @param  firstName  java.lang.String
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /// Set the last name.
    ///
    /// @param  lastName  java.lang.String
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /// Set the address.
    ///
    /// @param  address  java.lang.String
    public void setAddress(final String address) {
        this.address = address;
    }

    /// Set the city.
    ///
    /// @param  city  java.lang.String
    public void setCity(final String city) {
        this.city = city;
    }

    /// Set the state.
    ///
    /// @param  state  java.lang.String
    public void setState(final String state) {
        this.state = state;
    }

    /// Set the zip code.
    ///
    /// @param  zipCode  java.lang.String
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    /// Set the country.
    ///
    /// @param  country  java.lang.String
    public void setCountry(final String country) {
        this.country = country;
    }

    /// Set the phone number.
    ///
    /// @param  phone  java.lang.String
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /// Set the email address.
    ///
    /// @param  email  java.lang.String
    public void setEmail(final String email) {
        this.email = email;
    }

    /// Set the products.
    ///
    /// @param  products java.util.List<net.jmp.spring.boot.react.learning.ecommerce.Product>
    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    /// Get the tax rate.
    ///
    /// @return double
    public double getTaxRate() {
        return this.taxRate;
    }

    /// Set the tax rate.
    ///
    /// @param  taxRate  double
    public void setTaxRate(final double taxRate) {
        this.taxRate = taxRate;
    }

    /// The equals method
    ///
    /// @param  o  java.lang.Object
    /// @return    boolean
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        final OrderDocument that = (OrderDocument) o;

        return Objects.equals(this.documentId, that.documentId) &&
                Objects.equals(this.orderId, that.orderId) &&
                Objects.equals(this.orderDate, that.orderDate) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.city, that.city) &&
                Objects.equals(this.state, that.state) &&
                Objects.equals(this.zipCode, that.zipCode) &&
                Objects.equals(this.country, that.country) &&
                Objects.equals(this.phone, that.phone) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.taxRate, that.taxRate) &&
                Objects.equals(this.products, that.products);
    }

    /// The hash code method
    ///
    /// @return  int
    @Override
    public int hashCode() {
        return Objects.hash(
                this.documentId,
                this.orderId,
                this.orderDate,
                this.firstName,
                this.lastName,
                this.address,
                this.city,
                this.state,
                this.zipCode,
                this.country,
                this.phone,
                this.email,
                this.taxRate,
                this.products
        );
    }

    /// The to-string method
    ///
    /// @return  java.lang.String
    @Override
    public String toString() {
        return "OrderDocument{" +
                "documentId='" + this.documentId + '\'' +
                ", orderId='" + this.orderId + '\'' +
                ", orderDate='" + this.orderDate + '\'' +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", address='" + this.address + '\'' +
                ", city='" + this.city + '\'' +
                ", state='" + this.state + '\'' +
                ", zipCode='" + this.zipCode + '\'' +
                ", country='" + this.country + '\'' +
                ", phone='" + this.phone + '\'' +
                ", email='" + this.email + '\'' +
                ", taxRate=" + this.taxRate +
                ", products=" + this.products +
                '}';
    }
}
