package net.jmp.spring.boot.react.learning.ecommerce.fakestore;

/*
 * (#)Product.java  0.5.0   04/18/2026
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

import org.jspecify.annotations.Nullable;

import java.util.Objects;

/// The root product element in the fake store
public class Product {
    /// The identifier
    private int id;

    /// The title
    @Nullable
    private String title;

    /// The price
    private double price;

    /// The description
    @Nullable
    private String description;

    /// The category
    @Nullable
    private String category;

    /// The image URL
    @Nullable
    private String image;

    /// The rating
    @Nullable
    private Rating rating;

    /// The default constructor
    public Product() {
        super();
    }

    /// Get the identifier
    ///
    /// @return int
    public int getId() {
        return this.id;
    }

    /// Set the identifier
    ///
    /// @param  id    int
    public void setId(final int id) {
        this.id = id;
    }

    /// Get the title
    ///
    /// @return java.lang.String
    public @Nullable String getTitle() {
        return this.title;
    }

    /// Set the title
    ///
    /// @param  title   java.lang.String
    public void setTitle(final String title) {
        this.title = title;
    }

    /// Get the price
    ///
    /// @return double
    public double getPrice() {
        return this.price;
    }

    /// Set the price
    ///
    /// @param  price   double
    public void setPrice(final double price) {
        this.price = price;
    }

    /// Get the description
    ///
    /// @return java.lang.String
    public @Nullable String getDescription() {
        return this.description;
    }

    /// Set the description
    ///
    /// @param  description   java.lang.String
    public void setDescription(final String description) {
        this.description = description;
    }

    /// Get the category
    ///
    /// @return java.lang.String
    public @Nullable String getCategory() {
        return this.category;
    }

    /// Set the category
    ///
    /// @param  category  java.lang.String
    public void setCategory(final String category) {
        this.category = category;
    }

    /// Get the image URL
    ///
    /// @return java.lang.String
    public @Nullable String getImage() {
        return this.image;
    }

    /// Set the image URL
    ///
    /// @param  image   java.lang.String
    public void setImage(final String image) {
        this.image = image;
    }

    /// Get the rating
    ///
    /// @return net.jmp.spring.boot.react.learning.ecommerce.fakestore.Rating
    public @Nullable Rating getRating() {
        return this.rating;
    }

    /// Set the rating
    ///
    /// @param  rating  net.jmp.spring.boot.react.learning.ecommerce.fakestore.Rating
    public void setRating(final Rating rating) {
        this.rating = rating;
    }

    /// The equals method
    ///
    /// @param  o   java.lang.Object
    /// @return     boolean
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product that)) return false;

        return this.id == that.id &&
                Double.compare(this.price, that.price) == 0 &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.category, that.category) &&
                Objects.equals(this.image, that.image) &&
                Objects.equals(this.rating, that.rating);
    }

    /// The hash code method
    ///
    /// @return int
    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.title,
                this.price,
                this.description,
                this.category,
                this.image,
                this.rating
        );
    }

    /// The to-string method
    ///
    /// @return java.lang.String
    @Override
    public String toString() {
        return "Product{" +
                "id=" + this.id +
                ", title='" + this.title + '\'' +
                ", price=" + this.price +
                ", description='" + this.description + '\'' +
                ", category='" + this.category + '\'' +
                ", image='" + this.image + '\'' +
                ", rating=" + this.rating +
                '}';
    }
}
